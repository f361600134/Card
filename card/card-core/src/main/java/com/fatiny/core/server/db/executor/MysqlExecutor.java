package com.fatiny.core.server.db.executor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import com.fatiny.core.server.db.context.RequestContext;
import com.fatiny.core.server.db.manager.DbManager;
import com.fatiny.core.server.db.message.BatchLoadMsg;
import com.fatiny.core.server.db.message.Command;
import com.fatiny.core.server.db.message.DbServerMsg;
import com.fatiny.core.server.db.message.DbServerRespMsg;
import com.fatiny.core.util.IdHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * mysql业务执行者
 * 
 * insert、update、delete暂没做批处理
 * 
 * point:	
 * 	1. DbServerMsg请求对应的mysql业务, 无论处理成功、失败都必须有响应
 * 	2. mysql读和写分离在两个线程组执行(优先保证mysql读的效率, 不受mysql写的影响)
 * 	3. 线程分配通过DbServerMsg的getId()绑定, 同一个id的请求保证执行顺序
 * 	4. 由于读和写是分离的, 同一个id的请求读写不同步
 */
@Slf4j
public class MysqlExecutor {
	
	/** mysql读工作线程 */
	private List<ReadWorker> readWorkers;
	/** msyql写工作线程 */
	private List<WriteWorker> writeWorkers;
	/** 执行者数量 */
	private int workerCount;
	
	private AtomicInteger allocator = new AtomicInteger();
	
	
	public MysqlExecutor(int workerCount) {
		this.workerCount = workerCount;
		initWorkers(workerCount);
	}
	
	
	public void initWorkers(int workerCount) {
		readWorkers = new ArrayList<>(workerCount);
		writeWorkers = new ArrayList<>(workerCount);
		
		for (int i = 0; i < workerCount; i++) {
			ReadWorker readThread = new ReadWorker();
			readThread.setName("MYSQL_READ_THREAD-" + (i + 1));
			readThread.start();
			readWorkers.add(readThread);
			
			WriteWorker writeThread = new WriteWorker();
			writeThread.setName("MYSQL_WRITE_THREAD-" + (i + 1));
			writeThread.start();
			writeWorkers.add(writeThread);
		}
	}
	
	
	
	class ReadWorker extends Thread {
		
		// 最大容量Integer.MAX_VALUE;
		BlockingQueue<RequestContext> workQueue = new LinkedBlockingQueue<>();
		
		ReadWorker() {
			
		}
		
		ReadWorker(BlockingQueue<RequestContext> workQueue) {
			this.workQueue = workQueue;
		}
		
		@Override
		public void run() {
			for (;;) {
				RequestContext ctx = null;
				try {
					ctx = workQueue.take();
					DbServerMsg requestMsg = ctx.getMessage();
					Command cmd = requestMsg.getCmd();
					if (cmd.isReadType()) {
						List dataList = processLoad(requestMsg, cmd);
						loadSuccess(ctx, requestMsg, dataList);
						log.debug("成功加载数据:{}", dataList);
					} else {
						log.error("ReadWorker收到不正确的操作类型:{}, ", cmd);
					}
					
				} catch (Exception e) {
					log.error("ReadWorker加载数据过程出现异常", e);
					// 失败返回
					StringBuilder builder = new StringBuilder(); 
					builder.append("load data failure, cause:").append(e.getMessage());
					responseFail(ctx, builder.toString());
				}
				
			}
		}

		private void loadSuccess(RequestContext ctx, DbServerMsg serverMsg, List dataList) {
			if (serverMsg instanceof BatchLoadMsg) {
				ctx.getChannel().writeAndFlush(serverMsg); // 直接返回
			} else {
				DbServerRespMsg response = DbServerRespMsg.createLoadResp(serverMsg, dataList);
				ctx.getChannel().writeAndFlush(response);
			}
		}
		

		private List processLoad(DbServerMsg reqMsg, Command cmd) throws SQLException {
			long id = reqMsg.getId();
			List dataList = null;
			if (id == -1) {  // -1, 全数据库查询
				dataList = queryAll(reqMsg, cmd);
			} else if (id > 0) {
				int serverId = IdHelper.areaDbId(id);
				DataSource ds = DbManager.getDataSource(serverId);
				dataList = cmd.execLoad(ds, reqMsg);
			} else {
				log.error("查询数据库ID有误:{}", id);
			}
			return dataList;
		}

		
		private List queryAll(DbServerMsg reqMsg, Command cmd) throws SQLException {
			Collection<DataSource> dataSources = DbManager.allDataSource();
			List dataList = null;
			for (DataSource ds : dataSources) {
				List queryData = cmd.execLoad(ds, reqMsg);
				if (dataList == null) {
					dataList = queryData;
				} else {
					dataList.addAll(queryData);
				}
			}
			return dataList;
		}
		
		
	}
	
	
	class WriteWorker extends Thread {
		
		BlockingQueue<RequestContext> workQueue = new LinkedBlockingQueue<>();
		
		WriteWorker() {
			
		}
		
		WriteWorker(BlockingQueue<RequestContext> workQueue) {
			this.workQueue = workQueue;
		}
		
		@Override
		public void run() {
			for (;;) {
				RequestContext ctx = null;
				try {
					ctx = workQueue.take();
					DbServerMsg requestMsg = ctx.getMessage();
					long id = requestMsg.getId();
					Command cmd = requestMsg.getCmd();
					if (cmd.isWriteType()) {
						int serverId = IdHelper.areaDbId(id);
						DataSource ds = DbManager.getDataSource(serverId);
						cmd.execSave(ds, requestMsg);
						
						DbServerRespMsg response = DbServerRespMsg.createSaveResp(requestMsg);
						ctx.getChannel().writeAndFlush(response);
						
						log.debug("成功保存数据:{}", requestMsg);
					} else {
						log.error("WriteWorker收到不正确的操作类型:{}, ", cmd);
					}
					
				} catch (Exception e) {
					log.error("WriteWorker保存数据过程出现异常", e);
					// 失败返回
					StringBuilder builder = new StringBuilder(); 
					builder.append("save data failure, cause:").append(e.getMessage());
					responseFail(ctx, builder.toString());
				}
				
			}
		}
		
	}


	public int getWorkerCount() {
		return workerCount;
	}
	
	
	private void responseFail(RequestContext ctx, String failMsg) {
		DbServerMsg reqMsg = ctx.getMessage();
		DbServerRespMsg failure = DbServerRespMsg.createFailResp(reqMsg, failMsg);
		ctx.getChannel().writeAndFlush(failure);
	}
	
	
	public void submit(RequestContext ctx) {
		try {
			DbServerMsg message = ctx.getMessage();
			int idx = allocate(message);
			if (message.getCmd().isWriteType()) {
				WriteWorker writeWorker = writeWorkers.get(idx);
				writeWorker.workQueue.put(ctx); // 队列满载时阻塞
			} else {
				ReadWorker readWorker = readWorkers.get(idx);
				readWorker.workQueue.put(ctx);	// 队列满载时阻塞
			}
		} catch (Exception e) {
			log.error("mysqlExecutor异步过程出现异常", e);
			// 失败返回
			StringBuilder builder = new StringBuilder(); 
			builder.append("save data failure, cause:").append(e.getMessage());
			responseFail(ctx, builder.toString());
		}
		
	}
	
	
	private int allocate(DbServerMsg message) {
		Command cmd = message.getCmd();
		long id = message.getId();
		int index = 0;
		if (id <= 0) {
			if (cmd.isWriteType()) {
				throw new IllegalArgumentException(message + "不合法的消息id");
			}
			index = Math.abs(allocator.incrementAndGet()) % workerCount;
		} else {
			index = (int) (id % workerCount);
		}
		return index;
	}
	
}
