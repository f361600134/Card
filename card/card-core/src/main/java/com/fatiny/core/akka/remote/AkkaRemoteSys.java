package com.fatiny.core.akka.remote;

import java.util.concurrent.atomic.AtomicInteger;

import com.fatiny.core.client.actor.ActorInvoker;
import com.fatiny.core.client.actor.ActorRouteClient;
import com.fatiny.core.client.actor.CorrespondActor;
import com.fatiny.core.client.actor.TypedActorInfo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.TypedActor;
import akka.actor.TypedProps;

/**
 * 远程Actor系统
 * 
 * @author huachp
 */
public class AkkaRemoteSys {
	
	private AtomicInteger generator = new AtomicInteger();
	private String systemName;
	private ActorSystem system;
	private ActorRouteClient client;
	
	public static AkkaRemoteSys create(String systemName, ActorRouteClient client) {
		AkkaRemoteSys remoteSys = new AkkaRemoteSys();
		remoteSys.systemName = systemName;
		remoteSys.client = client;
		remoteSys.init();
		return remoteSys;
	}
	
	public static AkkaRemoteSys create(ActorSystem actorSys, ActorRouteClient client) {
		AkkaRemoteSys remoteSys = new AkkaRemoteSys();
		remoteSys.system = actorSys;
		remoteSys.systemName = actorSys.name();
		remoteSys.client = client;
		remoteSys.client.init();
		return remoteSys;
	}
		
	
	private void init() {
		system = ActorSystem.create(systemName); // 初始化ActorSystem
		client.init();
	}
	
	
	/**
	 * TypedActor远程调用方式
	 * 
	 * @param interfaceCls
	 * @param implementationCls
	 * @return T  TypedActor对象
	 */
	public <T> T typedActor(Class<T> interfaceCls, Class<? extends T> implementationCls) {
		TypedActorInfo actorInfo = TypedActorInfo.create(interfaceCls, implementationCls);
		int actorId = Math.abs(generator.incrementAndGet());
		Props props = Props.create(CorrespondActor.class, actorId, client, actorInfo);
		ActorRef actorRef = system.actorOf(props);
		
		TypedProps<? extends T> typeProps = new TypedProps<>(interfaceCls, implementationCls);
		return TypedActor.get(system).typedActorOf(typeProps, actorRef);
	}
	
	/**
	 * 异步调用方式, 并发性能较好
	 * 
	 * @param interfaceCls
	 * @param typedActorCls
	 * @return {@link ActorInvoker}
	 */
	public ActorInvoker remoteInvoker(Class<?> interfaceCls, Class<?> typedActorCls) {
		TypedActorInfo actorInfo = TypedActorInfo.create(interfaceCls, typedActorCls);
		int actorId = Math.abs(generator.incrementAndGet());
		Props props = Props.create(CorrespondActor.class, actorId, client, actorInfo);
		ActorRef actorRef = system.actorOf(props);
		return new ActorInvoker(actorId, actorRef, actorInfo);
	}
	
	
	public void stopRemoteActor(Object typedActor) {
		TypedActor.get(system).poisonPill(typedActor); // 异步停止TypedActor
	}
	
}
