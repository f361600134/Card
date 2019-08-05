package com.fatiny.core.akka.remote;

import io.netty.channel.Channel;

/**
 * actor消息上下文
 * 
 */
public class ActorMsgContext {

	private Channel channel;
	private ActorMessage actorMsg;
	
	public ActorMsgContext(Channel channel, ActorMessage msg) {
		this.channel = channel;
		this.actorMsg = msg;
	}
	
	public int getActorId() {
		return actorMsg.getActorId();
	}
	
	public Channel getChannel() {
		return channel;
	}

	public ActorMessage getActorMsg() {
		return actorMsg;
	}
	
	public void writeAndFlush(Object returnVal) {
		actorMsg.response(returnVal);  // 重用原来的对象返回
		channel.writeAndFlush(actorMsg);
	}
	
}
