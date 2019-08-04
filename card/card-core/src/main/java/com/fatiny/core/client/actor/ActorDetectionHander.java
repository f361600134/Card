package com.fatiny.core.client.actor;

import java.net.SocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.fatiny.core.akka.remote.ActorMessage;

import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 心跳检测线程
 */
@Slf4j
public class ActorDetectionHander {
	
	static final long HEART_BEAT_INTERVAL = 3500L; // 心跳间隔时间
	static final long CONTINUOUS_TIMEOUTS = 3; // 连续心跳次数
	static final long TIMEOUT_MILLIS = 3000L; // 超时时间
	
	/** 数据服客户端 */
	private ActorRouteClient client;
	/** 数据服心跳检测线程 */
	private ScheduledExecutorService heartbeatWorker;
	/** 心跳任务 */
	private Runnable heartbeatTask;
	/** 数据服心跳信息 */
	private Map<Integer, HeartbeatInfo> heartbeatInfos = new HashMap<>();
	
	
	ActorDetectionHander(ActorRouteClient client) {
		this.client = client;
		initHeartbeatInfo(client);
	}
	
	
	void initService() {
		heartbeatTask = new HeartbeatTask();
		DefaultThreadFactory tf = new DefaultThreadFactory("ACTOR_DETECTION", true);	// 守护线程
		heartbeatWorker = Executors.newScheduledThreadPool(1, tf); // 1条线程
		heartbeatWorker.scheduleAtFixedRate(heartbeatTask, 5000, HEART_BEAT_INTERVAL, TimeUnit.MILLISECONDS);
	}
	
	
	void initHeartbeatInfo(ActorRouteClient client) {
		List<ActorServerInfo> serverInfos = client.getServerList();
		for (ActorServerInfo serverInfo : serverInfos) {
			Integer serverId = serverInfo.getServerId();
			String addr = serverInfo.getServerAddr();
			HeartbeatInfo heartbeatInfo = new HeartbeatInfo(serverId, addr);
			heartbeatInfos.put(serverId, heartbeatInfo);
		}
	}
	
	
	HeartbeatInfo getHeartbeatInfo(ActorServerInfo serverInfo) {
		int serverId = serverInfo.getServerId();
		HeartbeatInfo heartbeatInfo = heartbeatInfos.get(serverId);
		return heartbeatInfo;
	}
	
	
	public void response(SocketAddress addr) {
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
				List<ActorServerInfo> serverInfos = client.getServerList();
				for (ActorServerInfo serverInfo : serverInfos) {
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
					ActorMessage heartbeatMsg = ActorMessage.heartbeat();
					serverInfo.getChannel().writeAndFlush(heartbeatMsg);
				}
			} catch (Exception e) {
				log.error("actor服务心跳检测异常", e);
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
