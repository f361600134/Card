package com.fatiny.core.akka.remote;

import akka.actor.ActorRef;

/**
 * actor分配器
 * 
 */
public class ServerRouteActors {
	
	private ActorRef[] actorRefs;
	
	public ServerRouteActors(int count) {
		if (count <= 0) {
			throw new IllegalArgumentException("初始化Actor数量<=0");
		}
		actorRefs = new ActorRef[count];
	}

	void addActor(int index, ActorRef actorRef) {
		actorRefs[index] = actorRef;
	}
	
	public ActorRef chooseActor(int actorId) {
		if (actorId < 0) {
			throw new IllegalArgumentException("不合法的actor id->" + actorId);
		}
		int index = actorId % actorRefs.length;
		return actorRefs[index];
	}
	
	public void submit(ActorMsgContext messageCtx) {
		int actorId = messageCtx.getActorId();
		ActorRef actorRef = chooseActor(actorId);
		actorRef.tell(messageCtx, ActorRef.noSender());
	}
	
}
