package com.fatiny.core.akka.remote;

import com.fatiny.core.util.GameLog;

import akka.actor.ActorRef;
import akka.actor.TypedActor.MethodCall;
import akka.actor.UntypedActor;

/**
 * 远程actor消息处理
 * 
 * @author huachp
 */
public class RemoteActor extends UntypedActor {
	
	private TypedActorMethods actorMethods;
	
	public RemoteActor(TypedActorMethods actorMethods) {
		this.actorMethods = actorMethods;
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof ActorMsgContext) {
			ActorMsgContext msgCtx = (ActorMsgContext) message;
			ActorMessage actorMsg = msgCtx.getActorMsg();
			RemoteMethodCall methodCall = (RemoteMethodCall) actorMsg.getMessage(); 
			checkTypedActor(msgCtx, methodCall); // 检测typedActor是否合法
			Object rs = actorMethods.call(methodCall); // typedActor方法调用
			msgCtx.writeAndFlush(rs);
		} else if (message instanceof MethodCall) { // Akka自带网络层的调用, 从远端传过来的对象是MethodCall, 为了兼容Akka的网络层不作删除
			MethodCall methodCall = (MethodCall) message;
			Object rs = methodCall.apply(actorMethods.typedActorObj());
			getSender().tell(rs, ActorRef.noSender());
		} else {
			GameLog.error("收到不合法的消息:{}", message);
		}
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
