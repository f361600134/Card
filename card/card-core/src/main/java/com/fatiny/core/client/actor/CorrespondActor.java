package com.fatiny.core.client.actor;

import java.lang.reflect.Method;

import com.fatiny.core.akka.remote.RemoteMethodCall;
import com.fatiny.core.util.GameLog;

import akka.actor.ActorRef;
import akka.actor.TypedActor.MethodCall;
import akka.actor.UntypedActor;

/**
 * 通信Actor 
 * 
 * @author huachp
 */
public class CorrespondActor extends UntypedActor {
	
	private int actorId;
	private ActorRouteClient routeClient;
	private TypedActorInfo actorObjInfo;
	
	public CorrespondActor(int actorId, ActorRouteClient routeClient
			, TypedActorInfo actorObjInfo) {
		this.actorId = actorId;
		this.routeClient = routeClient;
		this.actorObjInfo = actorObjInfo;
	}

	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof MethodCall) {
			MethodCall methodCall = (MethodCall) message;
			Method method = methodCall.method();
			Object[] params = methodCall.parameters();
			
			String clsName = actorObjInfo.getActorClsName();
			RemoteMethodCall remoteMc = RemoteMethodCall.create(
					method.getName(), clsName, params);
			boolean isOneWay = methodCall.isOneWay();
			ActorRef sender = getSender();
			ActorCallback callback = ActorCallback.create(sender, isOneWay);
			routeClient.request(actorId, remoteMc, callback);
			
		} else if(message instanceof TypedActorMsg) {
			TypedActorMsg msg = (TypedActorMsg) message;
			String methodName = msg.getMethodName();
			Object[] params = msg.getParams();
			
			String clsName = actorObjInfo.getActorClsName();
			RemoteMethodCall remoteMc = RemoteMethodCall.create(
					methodName, clsName, params);
			
			CallbackTask task = msg.getCallback();
			ActorCallback callback = ActorCallback.create(getSelf(), task);
			routeClient.request(actorId, remoteMc, callback);
			
		} else if(message instanceof CallbackTask) {	
			CallbackTask callbackMsg = (CallbackTask) message;
			callbackMsg.execute();
		} else {
			GameLog.error("通信Actor收到不合法的消息:{}", message);
		}
	}
	
}
