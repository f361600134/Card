package com.fatiny.core.akka.remote.netty3;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.TypedActor;
import akka.actor.TypedProps;

/**
 * actor客户端, 依赖Akka的网络层
 */
public class AkkaClient {
	
	private String systemName;
	private ActorSystem actorSystem;	
	
	
	public static AkkaClient create(String systemName) {
		AkkaClient client = new AkkaClient();
		client.systemName = systemName;
		return client;
	}
	
	
	public void bootstrap(String akkaConfig) {
		actorSystem = ActorSystem.create(systemName, ConfigFactory.load(akkaConfig));
	}

	
	public <T> T getRemoteActor(String actorName, String remoteAddr, 
			Class<T> interfaceCls, Class<? extends T> implementationCls) {
		
		Props props = Props.create(MessageActor.class, remoteAddr);
		ActorRef actorRef = actorSystem.actorOf(props, actorName);
		
		TypedProps<? extends T> typeProps = new TypedProps<>(interfaceCls, implementationCls);
		return TypedActor.get(actorSystem).typedActorOf(typeProps, actorRef);
	}
	
	
	public <T> T getRemoteActor(String remoteAddr, 
			Class<T> interfaceCls, Class<? extends T> implementationCls) {
		
		Props props = Props.create(MessageActor.class, remoteAddr);
		ActorRef actorRef = actorSystem.actorOf(props);
		
		TypedProps<? extends T> typeProps = new TypedProps<>(interfaceCls, implementationCls);
		return TypedActor.get(actorSystem).typedActorOf(typeProps, actorRef);
	}
	
	
	public String getSystemName() {
		return systemName;
	}

	public ActorSystem getActorSystem() {
		return actorSystem;
	}
	
	
}
