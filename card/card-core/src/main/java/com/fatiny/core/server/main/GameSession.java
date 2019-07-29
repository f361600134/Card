package com.fatiny.core.server.main;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

import io.netty.channel.Channel;
import io.netty.channel.socket.DatagramChannel;

/**
 * 玩家会话信息
 * 
 * @author huachp
 */
public class GameSession {
	
	/** session id 生产者 */
	private static AtomicInteger generator = new AtomicInteger(1);
	
	/** 玩家唯一标识 */
	private long playerId;
	/** session id */
	private int id;
	/** socket通道 */
	private Channel channel;
	/** 访问IP */
	private String ip;
	/** 访问端口 */
	private int port;
	/** 用户信息对象 */
	private Object userData ;
	
	
	public GameSession() {
		
	}
	
	public GameSession(Channel ch) {
		this.channel = ch;
		String address = ch.remoteAddress().toString();
		String[] ipAndPort = address.split(":");
		this.ip = ipAndPort[0];
		this.port = Integer.parseInt(ipAndPort[1]);
	}

	public long getPlayerId() {
		return playerId;
	}

	public int getId() {
		return id;
	}

	public Channel getChannel() {
		return channel;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public <T> T getUserData() {
		return (T) userData;
	}
	
	public void initUserData(Object userData) {
		this.userData = userData;
	}
	
	public void send(Object message) {
		if (isConnect()) {
			channel.writeAndFlush(message);
		}
	}
	
	public void sendToBuf(Object message) {
		if (isConnect()) {
			channel.write(message);
		}
	}
	
	public void releaseBuf() {
		if (isConnect()) {
			channel.flush();
		}
	}
	
	public boolean isConnect() {
		return channel.isActive();
	}
	
	public void disConnect() {
		channel.close();
	}
	
	public void init(long playerId, Object userData) {
		this.playerId = playerId;
		this.userData = userData;
	}
	
	public String getLocalIp() {
		if (channel != null) {
			InetSocketAddress localAddr = (InetSocketAddress) channel.localAddress();
			return localAddr.getHostName();
		}
		return null;
	}
	
	public boolean isTcpSession() {
		return channel != null && !(channel instanceof DatagramChannel);
	}
	
	
	public void destroy() {
		userData = null;  // 清理用户数据
	}
	
	
	public static GameSession create(Channel ch) {
		GameSession session = new GameSession(ch);
		session.id = Math.abs(generator.getAndIncrement());	// 初始化session id
		return session;
	}
	
	
}
