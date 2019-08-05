package com.fatiny.core.akka.remote.netty3;

import com.fatiny.core.akka.remote.RemoteActor;
import com.fatiny.core.akka.remote.TypedActorMethods;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Actor服务端, 依赖Akka的网络层调用
 */
public class AkkaServer {
	
	/** 默认actor数量1000 */
	static final int ACTOR_COUNT = 1000;
	
	private String systemName;
	private ActorSystem actorSystem;
	
	
	public static AkkaServer create(String systemName) {
		AkkaServer actorServer = new AkkaServer();
		actorServer.systemName = systemName;
		return actorServer;
	}
	
	public void bootstrap(String akkaConfig) {
		actorSystem = ActorSystem.create(systemName, ConfigFactory.load(akkaConfig));
	}
	
	public void startActors(String actorName, Object typeActorImpl) {
		Class<?>[] interfaces = typeActorImpl.getClass().getInterfaces();
		TypedActorMethods actorMethods = TypedActorMethods.create(interfaces[0], typeActorImpl);
		for (int i = 0; i < ACTOR_COUNT; i++) {
			Props props = Props.create(RemoteActor.class, actorMethods);
			actorSystem.actorOf(props, actorName + i);
		}
	}
	
	public String getSystemName() {
		return systemName;
	}

	public ActorSystem getActorSystem() {
		return actorSystem;
	}
	
}
