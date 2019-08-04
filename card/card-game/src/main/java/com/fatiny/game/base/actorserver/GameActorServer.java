package com.fatiny.game.base.actorserver;

import com.fatiny.core.akka.remote.RemoteActorServer;
import com.fatiny.game.actor.remote.IGameActor;

public class GameActorServer {
	
	static final String SYSTEM_NAME = "GAME-SERVER";
	
	static GameActorServer instance = new GameActorServer();
	
	public static GameActorServer instance() {
		return instance;
	}
	
	private RemoteActorServer actorServer;
	private boolean running;
	
	private GameActorServer() {
		
	}
	
	public void bootstrap() {
		if (running) return;
		GameActor gameActor = new GameActor();
		actorServer = RemoteActorServer.create(SYSTEM_NAME, IGameActor.class, gameActor);
		actorServer.bootstrap();
		running = true; // 切换运行状态
	}

	public RemoteActorServer getServerInfo() {
		return actorServer;
	}
	
	public boolean isRunning() {
		return running;
	}
	
}
