package com.fatiny.core.client.actor;

import java.lang.reflect.Method;

import com.fatiny.core.akka.remote.RemoteMethodCall;
import com.fatiny.core.util.GameLog;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.TypedActor.MethodCall;

/**
 * 通信Actor 
 */
public class CorrespondActor extends AbstractActor {
	
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
	public Receive createReceive() {
		return receiveBuilder()
				.match(MethodCall.class, methodCall ->{
					Method method = methodCall.method();
					Object[] params = methodCall.parameters();
					
					String clsName = actorObjInfo.getActorClsName();
					RemoteMethodCall remoteMc = RemoteMethodCall.create(
							method.getName(), clsName, params);
					boolean isOneWay = methodCall.isOneWay();
					ActorRef sender = getSender();
					ActorCallback callback = ActorCallback.create(sender, isOneWay);
					routeClient.request(actorId, remoteMc, callback);
				})
				.match(TypedActorMsg.class, msg -> {
					String methodName = msg.getMethodName();
					Object[] params = msg.getParams();
					
					String clsName = actorObjInfo.getActorClsName();
					RemoteMethodCall remoteMc = RemoteMethodCall.create(
							methodName, clsName, params);
					
					CallbackTask task = msg.getCallback();
					ActorCallback callback = ActorCallback.create(getSelf(), task);
					routeClient.request(actorId, remoteMc, callback);
				})
				.match(CallbackTask.class, callbackMsg -> {
					callbackMsg.execute();
				})
				.matchAny(t->GameLog.error("收到不合法的消息:{}", t))
				.build();
	}
	
}
