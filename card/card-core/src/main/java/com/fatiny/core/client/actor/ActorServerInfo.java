package com.fatiny.core.client.actor;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;

/**
 * actor服务器信息
 * 
 */
public class ActorServerInfo {

	public static final int TIMEOUT = -2;
	public static final int DISCONNECT = -1;
	public static final int CONNECTED = 0;
	
	
	/** 远程Actor服务标识 */
	private int serverId;
	/** 远程Actor服务地址 */
	private String ip;
	/** 远程Actor服务端口 */
	private int port;
	/** 连接状态 */
	private volatile int state = DISCONNECT;
	/** 对端服务是否是有状态的 */
	private boolean stateful;
	/** 服务器连接 */
	private Channel channel;
	/** 消息处理器 */
	private volatile ActorMsgHandler msgHandler;
	/** Netty连接管理 */
	private Bootstrap bootstrap;
	
	
	public ActorServerInfo(int serverId, String ip, int port) {
		this.serverId = serverId;
		this.ip = ip;
		this.port = port;
	}
	
	public void init(Bootstrap bootstrap, ActorMsgHandler msgHandler) {
		this.bootstrap = bootstrap;
		this.msgHandler = msgHandler;
	}
	

	public int getServerId() {
		return serverId;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public int getState() {
		return state;
	}
	
	public void connect() {
		this.state = CONNECTED;
	}
	
	public void timeout() {
		this.state = TIMEOUT;
	}
	
	public void disconnect() {
		channel.disconnect(); // 断开连接
		this.state = DISCONNECT;
	}
	
	public boolean isConnect() {
		return state == CONNECTED;
	}
	
	public String getServerAddr() {
		return ip + ":" + port;
	}
	
	public Channel getChannel() {
		return channel;
	}
	
	public boolean isActive() {
		return channel != null && channel.isActive();
	}
	
	public void serverStateful(boolean stateful) {
		this.stateful = stateful;
	}
	
	public boolean isStateful() {
		return stateful;
	}


	public ActorMsgHandler getMsgHandler() {
		return msgHandler;
	}
	
	public void channelOpen(Channel ch) {
		this.channel = ch;
		connect();
	}
	
	
	Bootstrap getBootstrap() {
		return bootstrap;
	}


	@Override
	public String toString() {
		return "ActorServerInfo [serverId=" + serverId + ", ip=" + ip + ", port=" + port + ", state=" + state + "]";
	}
	
}
