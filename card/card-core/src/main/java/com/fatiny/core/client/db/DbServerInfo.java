package com.fatiny.core.client.db;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;

/**
 * 数据服信息
 * 
 * @author huachp
 */
public class DbServerInfo {

	public static final int TIMEOUT = -2;
	public static final int DISCONNECT = -1;
	public static final int CONNECTED = 0;
	
	
	/** 数据服标识 */
	private int serverId;
	/** 数据服地址 */
	private String ip;
	/** 数据服端口 */
	private int port;
	/** 连接状态 */
	private volatile int state = DISCONNECT;
	/** 消息处理器 */
	private volatile DbServerMsgHandler msgHandler;
	/** 服务器连接 */
	private Channel channel;
	/** Netty连接管理 */
	private Bootstrap bootstrap;
	
	
	public DbServerInfo(int serverId, String ip, int port) {
		this.serverId = serverId;
		this.ip = ip;
		this.port = port;
	}
	
	public void init(Bootstrap bootstrap, DbServerMsgHandler msgHandler) {
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
	
	public boolean isActive() {
		return channel != null && channel.isActive();
	}
	
	public String getServerAddr() {
		return ip + ":" + port;
	}
	
	public Channel getChannel() {
		return channel;
	}
	
	public DbServerMsgHandler getMsgHandler() {
		return msgHandler;
	}
	
	// old channel must be closed
	public void channelOpen(Channel ch) {
		this.channel = ch;
		connect();
	}
	
	Bootstrap getBootstrap() {
		return bootstrap;
	}
	

	@Override
	public String toString() {
		return "DbServerInfo [serverId=" + serverId + ", ip=" + ip + ", port=" + port + ", state=" + state + "]";
	}
	
}
