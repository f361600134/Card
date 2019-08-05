package com.fatiny.game.game.module.common;

import com.fatiny.core.client.db.DbServerClient;
import com.fatiny.core.data.DataContext;
import com.fatiny.core.data.IData;
import com.fatiny.core.data.SyncData;
import com.fatiny.game.base.GameScheduler;

/**
 * 游戏上下文
 */
public class GameContext {
	
	static final GameContext CONTEXT = new GameContext();
	
	public static GameContext instance() {
		return CONTEXT;
	}
	
	/** 游戏数据接口 */
//	private IData gameData;
//	/** 远程登录ActorSystem */
//	private AkkaRemoteSys loginRemote;
//	/** 远程战斗ActorSystem */
//	private AkkaRemoteSys fightRemote;
	/** 定时器 */
	private GameScheduler scheduler;
	
	GameContext() {
		
	}
	
	public GameContext depend(DbServerClient client) {
		SyncData syncData = new SyncData();
		syncData.injectDbServerConnector(client);
//		this.gameData = syncData;
		client.init();
		DataContext.instance().init(syncData);
		return this;
	}
	
//	public GameContext loginRemoteDepend(AkkaRemoteSys loginRemoteSys) {
//		this.loginRemote = loginRemoteSys;
//		return this;
//	}
//	
//	public GameContext fightRemoteDepend(AkkaRemoteSys fightRemoteSys) {
//		this.fightRemote = fightRemoteSys;
//		return this;
//	}
	
	public GameContext depend(GameScheduler schd) {
		this.scheduler = schd;
		return this;
	}
	
	public void defaultSchd() {
		scheduler = GameScheduler.create();
	}
	
	public IData gameData() {
		return DataContext.instance().gameData();
	}
	
//	public IData gameData() {
//		return gameData;
//	}

//	public ILoginActor getLoginActor() {
//		return loginRemote.typedActor(ILoginActor.class, LoginActor.class);
//	}
//	
//	public ActorInvoker loginActorInvoker() {
//		return loginRemote.remoteInvoker(ILoginActor.class, LoginActor.class);
//	}
//	
//	public void stopLoginActor(ILoginActor loginActor) {
//		loginRemote.stopRemoteActor(loginActor);
//	}
	
//	public IFightRemoteActor getFightActor() {
//		return fightRemote.typedActor(IFightRemoteActor.class, FightRemoteActor.class);
//	}
//	
//	public ActorInvoker fightActorInvoker() {
//		return fightRemote.remoteInvoker(IFightRemoteActor.class, FightRemoteActor.class);
//	}
//	
//	public void stopFightActor(IFightRemoteActor fightActor) {
//		fightRemote.stopRemoteActor(fightActor);
//	}
	
	public GameScheduler getSched() {
		return scheduler;
	}
	
}
