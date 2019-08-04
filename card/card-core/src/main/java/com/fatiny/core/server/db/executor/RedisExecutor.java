package com.fatiny.core.server.db.executor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.fatiny.core.data.BasePo;
import com.fatiny.core.server.db.DbServerConfig;
import com.fatiny.core.server.db.context.RequestContext;
import com.fatiny.core.server.db.message.DbServerMsg;
import com.fatiny.core.server.db.message.DbServerReqMsg;
import com.fatiny.core.server.db.message.DbServerRespMsg;
import com.fatiny.core.util.RedisConnector;

import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * redis业务执行者
 */
@Slf4j
public class RedisExecutor {
	
	static final int BATCH_COUNT = 100; // 单次批处理的任务数
	static final long BATCH_TIME = 100L; // 单次最大批处理的时间
	
	static final long PERIOD = 5000L; // 故障检测时间
	static final int ABNORMAL = 0; // 故障状态 
	static final int NORMAL = 1; // 正常状态
	
	static volatile int status = ABNORMAL; // redis服务状态, 所有线程共享
	
	private List<PipelineWorker> workers;
	private int workerCount;
	private RedisConnector connector;
	
	/** redis故障检测线程 */
	private ScheduledExecutorService detectionWorker;
	/** redis故障检测任务*/
	private DetectionTask detectionTask;
	
	private MysqlExecutor mysqlExecutor;
	
	
	public RedisExecutor(int workerCount) {
		this.workerCount = workerCount;
		initConnector();
		initWorkers();
	}
	
	
	private void initWorkers() {
		if (connector == null) {
			return;
		}
		workers = new ArrayList<>(workerCount);
		for (int i = 0; i < workerCount; i++) {
			PipelineWorker thread = new PipelineWorker();
			thread.setName("REDIS_OPERATION-" + (i + 1));
			thread.start();
			workers.add(thread);
		}
//		initService();
	}

	
	private void initConnector() {
		DbServerConfig dbConfig = DbServerConfig.getInstance();
		Properties p = dbConfig.getJedisPoolCfg();
		String isOpen = p.getProperty("isOpen", "false");
		if (isOpen.trim().equals("true")) {
			connector = new RedisConnector(p);
		}
	}
	
	
	private void initService() {
		detectionTask = new DetectionTask();
		DefaultThreadFactory tf = new DefaultThreadFactory("REDIS_DETECTION-", true);	// 守护线程
		detectionWorker = Executors.newScheduledThreadPool(1, tf);
		detectionWorker.scheduleAtFixedRate(detectionTask, 0, PERIOD, TimeUnit.MILLISECONDS);
	}
	
	
	public int getWorkerCount() {
		return workerCount;
	}
	
	public void hold(MysqlExecutor mysqlExecutor) {
		this.mysqlExecutor = mysqlExecutor;
	}
	
	
	/**
	 * redis管道处理工作者
	 * 
	 * point:
	 * 	1. 使用redis管道批处理
	 * 	2. 数据格式统一: key hash value
	 */
	class PipelineWorker extends Thread {
		
		BlockingQueue<RequestContext> taskQueue = new LinkedBlockingQueue<>();
		LinkedList<RequestContext> pendingQueue = new LinkedList<>();
		
		private Jedis jedis = null;
		private Pipeline pipeline = null;
		
		private long lastSyncTime = System.currentTimeMillis();
		
		PipelineWorker() {
			
		}
		
		
		@Override
		public void run() {
			RequestContext ctx = null;
			for (;;) {
				try {
					if (ctx == null) {
						ctx = taskQueue.poll();
					}
					if (ctx != null) {
						doByPipeline(ctx);
						batchOperation();
						ctx = null;
					} else {
						pipelineSync();
						ctx = taskQueue.take();
					}
					
				} catch (Exception e) {
					log.info("redis pipeline operation occur exception", e);
					ctx = null;
				}
			}
		}
		
		
		
		private void batchOperation() {
			long curTime = System.currentTimeMillis();
			if (curTime - lastSyncTime > BATCH_TIME 
					|| pendingQueue.size() > BATCH_COUNT) {
				pipelineSync();
				lastSyncTime = System.currentTimeMillis();
			}
		}
		
		
		private void pipelineSync() {
			try {
				if (pendingQueue.peek() == null) 
					return;
				
				List<Object> returnList = pipeline.syncAndReturnAll();
				for (int i = 0; i < returnList.size(); i++) {
					Object returnObj = returnList.get(i);
					returnValueAndResp(returnObj);
				}
			} catch (JedisConnectionException e) {
				log.error("redis连接异常(pipelineSync)", e);
				if (status == NORMAL) {
					status = ABNORMAL;
				}
				for (int i = 0; i < pendingQueue.size(); i++) {
					mysqlExecutor.submit(pendingQueue.poll());
				}
			} finally {
				closeConnection();
			}
		}


		private void returnValueAndResp(Object returnObj) {
			RequestContext ctx = pendingQueue.poll();
			try {
				DbServerReqMsg reqMsg = (DbServerReqMsg) ctx.getMessage();
				if (reqMsg.getCmd().isReadType()) {
					Map<String, String> jsonMap = (Map<String, String>) returnObj;
					
					if (jsonMap == null || jsonMap.isEmpty()) {
						mysqlExecutor.submit(ctx);
						return;
					}
					
					List<BasePo> data = new ArrayList<>();
					for (String jsonData : jsonMap.values()) {
						Class cls = reqMsg.getPoCls();
						BasePo po = JSON.parseObject(jsonData, (Class<? extends BasePo>) cls);
						data.add(po);
					}
					
					log.info("从redis加载数据成功:{}", data);
					DbServerRespMsg response = DbServerRespMsg.createLoadResp(reqMsg, data);
					ctx.getChannel().writeAndFlush(response);
					
				} else {
					BasePo po = reqMsg.getPo();
					log.info("redis数据保存结果:{}, 实体:{}", returnObj, po);
					mysqlExecutor.submit(ctx);	// 后续保存到mysql
				}
			} catch (Exception e) {
				log.error("redis响应数据操作发生异常(returnValueAndResp)", e);
				mysqlExecutor.submit(ctx);
			}
			
		} 
		
		
		
		private void doByPipeline(RequestContext ctx) {
			try {
				conncet();	// 获取连接
				DbServerMsg message = ctx.getMessage();
				if (message.getCmd().isReadType()) {
					pipelineLoad((DbServerReqMsg) message);
				} else {
					pipelineSave((DbServerReqMsg) message);
				}
				pendingQueue.add(ctx);
			} catch (JedisConnectionException e) {
				log.error("redis连接异常(doByPipeline)", e);
				if (status == NORMAL) {
					status = ABNORMAL;
				}
				mysqlExecutor.submit(ctx);
				for (int i = 0; i < taskQueue.size(); i++) {
					mysqlExecutor.submit(taskQueue.poll());
				}
			}
		}
		
		
		private void pipelineLoad(DbServerReqMsg reqMsg) {
			Class<?> poCls = reqMsg.getPoCls();
			String cacheKey = cacheKey(reqMsg.getId(), poCls);
			pipeline.hgetAll(cacheKey);
		}
		
		
		private void pipelineSave(DbServerReqMsg reqMsg) {
			Class<?> poCls = reqMsg.getPoCls();
			BasePo po = reqMsg.getPo();
			
			String cacheKey = cacheKey(po.key(), poCls);
			String subKey = subKey(po);
			pipeline.hset(cacheKey, subKey, JSON.toJSONString(po));
		}
		
		
		private String cacheKey(long key, Class<?> cls) {
			String prefix = cls.getSimpleName();
			return prefix + key;
		}
		
		
		private String subKey(BasePo po) {
			String subId = po.cacheId();
			if (StringUtils.isEmpty(subId)) {
				return po.key() + "";
			}
			return subId;
		}
		
		
		private void conncet() {
			if (jedis == null) {
				jedis = connector.getConn();
				pipeline = jedis.pipelined();
			}
		}
		
		
		private void closeConnection() {
			if (jedis != null) {
				connector.closeConn(jedis);
				jedis = null;
				pipeline = null;
			}
		}
		
	}
	
	
	
	/**
	 * redis服务检测任务
	 * <p>
	 * 通过jedis客户端提供的获取连接api->jedisPool.getResource()检测连接是否可用,
	 * 如果连接不可用, jedis客户端会抛出异常
	 */
	class DetectionTask implements Runnable {
		
		@Override
		public void run() {
			Jedis jedis = null;
			try {
				jedis = connector.getConn();
				if (status == ABNORMAL) {
					log.info("检测redis连接恢复可用:NORMAL");
					status = NORMAL;
				}
			} catch (JedisConnectionException e) {
				if (status == NORMAL) {
					log.error("检测到redis连接异常:ABNORMAL", e);
					status = ABNORMAL;
				}
			} finally {
				connector.closeConn(jedis);
			}
		}
		
	}
	
	
	public void submit(RequestContext ctx) {
		try {
			DbServerMsg message = ctx.getMessage();
			// redis连接正常状态下才加入队列
			if (status == NORMAL) { 
				int idx = (int) (message.getId() % workerCount);
				workers.get(idx).taskQueue.put(ctx);
			} else {
				mysqlExecutor.submit(ctx);
			}
		} catch (Exception e) {
			log.error("redisExecutor异步过程出现异常", e);
		}
	}
	
}
