package com.fatiny.game.base;


import com.fatiny.core.akka.remote.AkkaRemoteSys;
import com.fatiny.core.client.actor.ActorRouteClient;

import akka.actor.ActorSystem;

/**
 * 远程Actor帮助类
 */
public class ActorRemoteContext {
	
	static final String LOGIN_ACTOR = "loginActor";
	static final String FIGHT_ACTOR = "fightActor";
	static final String REMOTE_SYSTEM = "REMOTE";
	
	private static ActorSystem actorSystem = ActorSystem.create(REMOTE_SYSTEM); // 初始化ActorSystem
	
	public static ActorSystem system() {
		return actorSystem;
	}
	
	public static AkkaRemoteSys createLoginRemoteSys() {
		ActorRouteClient client = ActorRouteClient.create(LOGIN_ACTOR, false);
		AkkaRemoteSys sys = AkkaRemoteSys.create(actorSystem, client);
		return sys;
	}
	
	public static AkkaRemoteSys createFightRemoteSys() {
		ActorRouteClient client = ActorRouteClient.create(FIGHT_ACTOR, true);
		AkkaRemoteSys sys = AkkaRemoteSys.create(actorSystem, client);
		return sys;
	}
	
}
