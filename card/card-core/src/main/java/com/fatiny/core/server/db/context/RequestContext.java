package com.fatiny.core.server.db.context;

import com.fatiny.core.server.db.message.DbServerMsg;

import io.netty.channel.Channel;

/**
 * 数据服异步消息上下文
 * 
 */
public class RequestContext {
	
	private long id;
	private Channel channel;
	private DbServerMsg message;
	
	public RequestContext(DbServerMsg message, Channel ch) {
		this.id = message.getId();
		this.message = message;
		this.channel = ch;
	}
	
	public long getId() {
		return id;
	}

	public DbServerMsg getMessage() {
		return message;
	}

	public Channel getChannel() {
		return channel;
	}
	

}
