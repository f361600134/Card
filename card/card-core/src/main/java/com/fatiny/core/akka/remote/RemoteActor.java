package com.fatiny.core.akka.remote;

import com.fatiny.core.util.GameLog;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.TypedActor.MethodCall;

/**
 * 远程actor消息处理
 */
public class RemoteActor extends AbstractActor {
	
	private TypedActorMethods actorMethods;
	
	public RemoteActor(TypedActorMethods actorMethods) {
		this.actorMethods = actorMethods;
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(ActorMsgContext.class, msgCtx ->{
					ActorMessage actorMsg = msgCtx.getActorMsg();
					RemoteMethodCall methodCall = (RemoteMethodCall) actorMsg.getMessage(); 
					checkTypedActor(msgCtx, methodCall); // 检测typedActor是否合法
					Object rs = actorMethods.call(methodCall); // typedActor方法调用
					msgCtx.writeAndFlush(rs);
				})
				.match(MethodCall.class, methodCall -> {
					Object rs = methodCall.apply(actorMethods.typedActorObj());
					getSender().tell(rs, ActorRef.noSender());
				})
				.matchAny(t->GameLog.error("收到不合法的消息:{}", t))
				.build();
	}

	
	private void checkTypedActor(ActorMsgContext msgCtx, RemoteMethodCall methodCall) {
		if (!methodCall.isStub(actorMethods.typedActorCls())) { 
			String type0 = actorMethods.typedActorCls().getName();
			String type1 = methodCall.getClassName();
			RemoteFailure f = RemoteFailure.illegal(type0, type1);
			msgCtx.writeAndFlush(f);
			throw new IllegalStateException(f.getDetail());
		}
	}
	
}
