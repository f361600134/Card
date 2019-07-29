package com.fatiny.core.client.actor;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import io.netty.util.concurrent.FastThreadLocal;

/**
 * 异步请求Actor调用者
 * 
 * @author huachp
 */
public class ActorInvoker {
	
	static final FastThreadLocal<TypedActorMsg> PENDING_MSG = new FastThreadLocal<>();
	
	static final CallbackTask EMPTY_CALLBACK = new CallbackTask() {
		@Override
		public void callback(Object returnObj) {
			
		}
	};
	
	private int actorId;
	private ActorRef actorRef;
	private TypedActorInfo actorInfo;
	
	public ActorInvoker(int actorId, ActorRef actorRef, TypedActorInfo actorInfo) {
		this.actorId = actorId;
		this.actorRef = actorRef;
		this.actorInfo = actorInfo;
	}
	
	public int getActorId() {
		return actorId;
	}

	public ActorInvoker invoke(String methodName, Object... params) {
		TypedActorMsg msg = TypedActorMsg.create(methodName, params);
		actorInfo.checkMethodInfo(methodName, params);
		PENDING_MSG.set(msg);
		return this;
	}
	
	public void remoteCall(CallbackTask callbackTask) {
		TypedActorMsg msg = PENDING_MSG.get();
		PENDING_MSG.remove();
		msg.lazyCall(callbackTask);
		actorRef.tell(msg, ActorRef.noSender());
	}
	
	public void remoteCall() {
		TypedActorMsg msg = PENDING_MSG.get();
		PENDING_MSG.remove();
		msg.lazyCall(EMPTY_CALLBACK);
		actorRef.tell(msg, ActorRef.noSender());
	}
	
	public void stopMyself() {
		actorRef.tell(PoisonPill.getInstance(), ActorRef.noSender());
	}
	
}
