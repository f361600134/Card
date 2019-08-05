package com.fatiny.core.akka.remote;

/**
 * TypedActor消息
 * 
 */
public class ActorMessage {
	
	public static final Byte HEARTBEAT = 0xF;
	
	private int id; // 消息ID
	private int actorId; // actor ID
	private Object message;
	
	ActorMessage() {
		
	}
	
	ActorMessage(int actorId, Object message) {
		this.actorId = actorId;
		this.message = message;
	}
	
	public int getId() {
		return id;
	}

	public int getActorId() {
		return actorId;
	}

	public Object getMessage() {
		return message;
	}
	
	public boolean isHeartbeat() {
		return id == 0 && message.equals(HEARTBEAT);
	}
	
	public void injectId(int msgId) {
		this.id = msgId;
	}
	
	public ActorMessage response(Object respMsg) {
		this.message = respMsg;
		return this;
	}
	
	
	public static ActorMessage heartbeat() {
		ActorMessage actorMsg = new ActorMessage();
		actorMsg.message = HEARTBEAT;
		return actorMsg;
	}
	
	public static ActorMessage create(int actorId, Object message) {
		return new ActorMessage(actorId, message);
	}
	
}
