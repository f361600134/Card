package com.fatiny.core.client.db;

import java.net.SocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.fatiny.core.server.db.message.DbServerMsg;

import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据服检测线程
 */
@Slf4j
public class DbServerDetectionHander {
	
	static final long HEART_BEAT_INTERVAL = 3500L; // 心跳间隔时间
	static final long CONTINUOUS_TIMEOUTS = 3; // 连续心跳次数
	static final long TIMEOUT_MILLIS = 3000L; // 超时时间
	
	/** 数据服客户端 */
	private DbServerClient client;
	/** 数据服心跳检测线程 */
	private ScheduledExecutorService heartbeatWorker;
	/** 心跳任务 */
	private Runnable heartbeatTask;
	/** 数据服心跳信息 */
	private Map<Integer, HeartbeatInfo> heartbeatInfos = new HashMap<>();
	
	
	DbServerDetectionHander(DbServerClient client) {
		this.client = client;
		initHeartbeatInfo(client);
	}
	
	
	void initService() {
		heartbeatTask = new HeartbeatTask();
		DefaultThreadFactory tf = new DefaultThreadFactory("DB_SERVER_DETECTION", true);	// 守护线程
		heartbeatWorker = Executors.newScheduledThreadPool(1, tf);
		heartbeatWorker.scheduleAtFixedRate(heartbeatTask, 5000, HEART_BEAT_INTERVAL, TimeUnit.MILLISECONDS);
	}
	
	
	void initHeartbeatInfo(DbServerClient client) {
		List<DbServerInfo> serverInfos = client.getServerList();
		for (DbServerInfo serverInfo : serverInfos) {
			Integer serverId = serverInfo.getServerId();
			String addr = serverInfo.getServerAddr();
			HeartbeatInfo heartbeatInfo = new HeartbeatInfo(serverId, addr);
			heartbeatInfos.put(serverId, heartbeatInfo);
		}
	}
	
	
	HeartbeatInfo getHeartbeatInfo(DbServerInfo serverInfo) {
		int dbServerId = serverInfo.getServerId();
		HeartbeatInfo heartbeatInfo = heartbeatInfos.get(dbServerId);
		return heartbeatInfo;
	}
	
	
	public void response(SocketAddress addr, Byte heartbeat) {
		if (!heartbeat.equals(DbServerMsg.HEARTBEAT)) return;
		String addrStr = addr.toString();
		for (HeartbeatInfo heartbeatInfo : heartbeatInfos.values()) {
			if (addrStr.contains(heartbeatInfo.getServerAddr())) {
				heartbeatWorker.execute(new ResponseTask(heartbeatInfo));
			}
		}
	}
	
	
	class ResponseTask implements Runnable {
		
		HeartbeatInfo checkInfo = null;
		
		ResponseTask(HeartbeatInfo heartbeat) {
			this.checkInfo = heartbeat;
		}
		
		@Override
		public void run() {
			checkInfo.clearTime();
		}
		
	}
	
	
	class HeartbeatTask implements Runnable {
		
		@Override
		public void run() {
			try {
				List<DbServerInfo> serverInfos = client.getServerList();
				for (DbServerInfo serverInfo : serverInfos) {
					if (!serverInfo.isConnect()) {
						client.reconnect(serverInfo);
						continue;
					}
					HeartbeatInfo heartbeat = getHeartbeatInfo(serverInfo);
					heartbeat.checktTimeOut();
					if (heartbeat.getTimeoutCount() >= CONTINUOUS_TIMEOUTS) {
						serverInfo.disconnect();
						heartbeat.clearTime();
						continue;
					}
					heartbeat.recordTimestamp();
					serverInfo.getChannel().writeAndFlush(DbServerMsg.HEARTBEAT);
				}
			} catch (Exception e) {
				log.error("数据服心跳检测异常", e);
			}
		}
		
	}
	
	
	class HeartbeatInfo {

		/** 数据服ID */
		private int dbServerId;
		/** 请求时间戳 */
		private long reqTimestamp;
		/** 连续超时次数 */
		private int timeoutCount;
		/** 数据服地址 */
		private String serverAddr;
		
		HeartbeatInfo(int dbServerId, String serverAddr) {
			this.dbServerId = dbServerId;
			this.serverAddr = serverAddr;
			this.reqTimestamp = Long.MAX_VALUE;
		}

		int getDbServerId() {
			return dbServerId;
		}

		long getReqTimestamp() {
			return reqTimestamp;
		}

		int getTimeoutCount() {
			return timeoutCount;
		}
		
		public String getServerAddr() {
			return serverAddr;
		}

		void recordTimestamp() {
			reqTimestamp = System.currentTimeMillis();
		}
		
		void clearTime() {
			reqTimestamp = Long.MAX_VALUE;
			timeoutCount = 0;
		}
		
		void checktTimeOut() {
			long cur = System.currentTimeMillis();
			if (cur - reqTimestamp > TIMEOUT_MILLIS) {
				timeoutCount ++;
			}
		}
		
	}
	
}
