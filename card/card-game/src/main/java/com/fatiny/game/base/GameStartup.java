package com.fatiny.game.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Date;

import com.fatiny.core.bootstrap.IStartup;
import com.fatiny.core.client.actor.ActorGroupConfig;
import com.fatiny.core.client.db.Callback;
import com.fatiny.core.client.db.DbServerClient;
import com.fatiny.core.net.IServerHandler;
import com.fatiny.core.server.main.dispatcher.Dispatcher;
import com.fatiny.game.base.actorserver.GameActorServer;
import com.fatiny.game.base.base.PropertyLoader;
import com.fatiny.game.base.event.EventSubscribe;
import com.fatiny.game.base.event.GameEventBus;
import com.fatiny.game.game.module.common.GameContext;

import lombok.extern.slf4j.Slf4j;

/**
 * 业务逻辑初始化入口
 */
@Slf4j
public class GameStartup implements IStartup {
	
	@Override
	public IServerHandler startup() throws Exception {
		log.info("加载游戏服所需类信息");
		RuntimeClassManager classManager = RuntimeClassManager.instance();
		classManager.loadClasses("com.fatiny.game.game"); // 扫描module包下所有的Java类
		
		log.info("初始化所有游戏服协议信息");
		Collection<Class<?>> classes = classManager.getClassByType(IHandler.class);
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.load(classes);
		
		log.info("初始化所有事件观察者");
		GameEventBus.instance().register(classManager.getClassByAnnotationClass(EventSubscribe.class));

		log.info("初始化所有策划配置");
//		BaseDataManager baseDataManager = BaseDataManager.instance();
//		baseDataManager.loadFiles();
		PropertyLoader.load();
		
		// 读取游戏配置
		log.info("读取游戏配置game.properties");
		//GameConfig.loadDefault();
		
		// 远程Actor配置
		ActorGroupConfig.instance().loadConfig(); 
//		// 创建远程登录ActorSystem
//		AkkaRemoteSys loginRemoteSys = ActorRemoteContext.createLoginRemoteSys();
//		// 创建远程战斗ActorSystem
//		AkkaRemoteSys fightRemoteSys = ActorRemoteContext.createFightRemoteSys();
		
		// 初始化游戏上下文
		DbServerClient dbsClient = DbServerClient.create();
		dbsClient.addCallback(new IdCallback(dbsClient));
		
		GameContext.instance().depend(dbsClient);
//				.loginRemoteDepend(loginRemoteSys)
//				.fightRemoteDepend(fightRemoteSys);
		GameContext.instance().defaultSchd();
		
		InitializationManager.init(InitializeListener.GAME); // 游戏通用模块初始化
		InitializationManager.init(InitializeListener.GAME_SERVER_ONLY); // 游戏模块初始化
		
		//PlayerLog.init(GameConfig.instance().getLogGameId());
		CommonJobStart.prepareToRun(); // 加载通用定时器
		
		GameActorServer.instance().bootstrap();
		GameServerHandler serverHandler = GameServerHandler.create(dispatcher);
		
		return serverHandler;
	}
	
	
	class IdCallback implements Callback {
		
		DbServerClient client;
		
		IdCallback(DbServerClient client) {
			this.client = client;
		}
		
		@Override
		public void onCall() {
//			IdManager idManager = IdManager.instance();
//			idManager.addAlloctor(RoleIdAllocator.create()); // 玩家ID生成器
//			idManager.addAlloctor(LegionIdAllocator.create()); // 公会ID生成器
//			idManager.init(client);
		}
	}


	@Override
	public void startInstruct() {
		//后台指令
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int nullCount = 0;
		while(true){
			try {
				String str = br.readLine();
				if(str == null){
					nullCount ++;
					if(nullCount > 50) 
						break;
					continue;
				}
				System.out.println("输入命令:<stop/test/other...>"); 
				nullCount = 0;
				if(str.trim().equals("stop")){
//					GameContext.instance().getLoginActor().modifyServerStatus(GameConfig.instance().areaId(), ServerStatus.CLOSE.ordinal());
//					System.out.println("=====登录服状态已经修改完毕=====");
//					try {
//						PlayerManager.instance().kickAllPlayers();
//						System.out.println("=====踢所有玩家下线完毕=====");
//						GameScheduler schd = GameContext.instance().getSched();
//						schd.pauseJob(CommonJobStart.AUTO_SAVE_JOB_NAME);
//						IData dataSync = GameContext.instance().gameData();
//						Response resp0 = dataSync.executeBatchSave();
//						Response resp1 = dataSync.executeBatchSave();
//						if (resp0 != null) 
//							resp0.sync(10000L);
//						if (resp1 != null) 
//							resp1.sync(10000L); 
//					} catch (Exception e) {
//						log.error("关服处理出现异常", e);
//					}
//					Thread.sleep(20000);
//					System.out.println("服务器已经安全关闭:" + new Date().toLocaleString());
//					System.exit(0);
					break;
				}else if(str.trim().equals("teststop")){
					try {
//						PlayerManager.instance().kickAllPlayers();
//						System.out.println("=====踢所有玩家下线完毕=====");
//						GameScheduler schd = GameContext.instance().getSched();
//						schd.pauseJob(CommonJobStart.AUTO_SAVE_JOB_NAME);
//						IData dataSync = GameContext.instance().gameData();
//						Response resp0 = dataSync.executeBatchSave();
//						Response resp1 = dataSync.executeBatchSave();
//						if (resp0 != null) 
//							resp0.sync(2000L);
//						if (resp1 != null) 
//							resp1.sync(2000L); 
					} catch (Exception e) {
						log.error("关服处理出现异常", e);
					}
					Thread.sleep(2000);
					System.out.println("服务器已经安全关闭:" + new Date().toLocaleString());
					System.exit(0);
					break;
				}
				else{
//					doSomeThing(str);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("执行命令失败:遇到致命错误");
			}
		}
		
	}
	
	
//	/**
//	 * 后台指令
//	 * @param str
//	 */
//	private static void doSomeThing(String str) {
//		String [] data = str.split("=");
//		if(data[0].equals("test")){
//			System.out.println("服务器正在运行,当前时间:" + new Date().toLocaleString());
//			System.out.println("服务器正在运行,当前时间2:" + new Date(System.currentTimeMillis()).toLocaleString());
//			
//			System.out.println("test1:" + new Date().getTime());
//			System.out.println("test2:" + System.currentTimeMillis());
//			
//			System.out.println("当前默认时区  "+TimeZone.getDefault()); //输出当前默认时区 
//			System.out.println(DateUtil.formatTime(new Date()));
//		}else if(data[0].equals("code"))
//		{
//			/**
//			 * 例子：code=100=1
//			 * data[1]=数量    data[2]=批次
//			 */
//			CodeManager.crateCode(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
//		}else if(data[0].equals("count"))
//		{
//			/**
//			 * 在线人数
//			 */
//			System.out.println(SessionGroup.onlineMembers());
//		}else if(data[0].equals("clear"))
//		{
//			/**
//			 * 清理玩家本地缓存
//			 */
//			PlayerContext player = PlayerManager.instance().getPlayer(Long.parseLong(data[1]));
//			if(player == null)
//			{
//				System.out.println("玩家不存在："+data[0]);
//			}else{
//				if(player.isOnline())
//				{
//					PlayerManager.instance().kickPlayer(player);
//				}
//				
//				for (ILogoutListener logoutListener : LogoutManager.logoutListeners) {
//					logoutListener.logout(player);
//				}
//			}
//		}else if(data[0].equals("reload"))
//		{
//			/**
//			 * 重新加载数据文件
//			 */
//			BaseDataManager.instance().loadFiles();
//		}else if(data[0].equals("settime"))
//		{
//			final TimeZone zone = TimeZone.getTimeZone(data[1]); //获取时区
//			TimeZone.setDefault(zone); //设置时区  
//			System.out.println("设置时区为:"+TimeZone.getDefault()); //输出验证  
//			System.out.println(DateUtil.formatTime(new Date()));
//		}
//		else if(data[0].equals("playersExpReset"))
//		{
//			List<PlayersTest> playerlist = GameContext.instance().gameData().getAllData(-1, PlayersTest.class);
//			
//			for(PlayersTest player : playerlist){
//				
//				try {
//					PlayerContext playerContext = PlayerManager.instance().getPlayer(player.getPlayerId());
//					playerContext.getPlayer().setExp(player.getExp());
//					playerContext.getPlayer().setLevel(player.getLevel());
//					playerContext.getPlayer().update();
//
//					System.out.println("玩家id:" + player.getPlayerId() + "重置完成");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			
//			System.out.println("玩家经验重置完成");
//		}
//		else if(data[0].equals("playersMechaReset")){
//			List<PlayersTest> playerlist = GameContext.instance().gameData().getAllData(-1, PlayersTest.class);
//			
//			for(PlayersTest player : playerlist){
//				try {
//					List<Mecha> dataDb =  GameContext.instance().gameData().getData(Mecha.class, player.getPlayerId());
//					Mecha mecha = dataDb.get(0);
//					mecha.setLevel(player.getLevel());
//					mecha.setExp(player.getExp());
//					mecha.update();
//
//					System.out.println("玩家id:" + player.getPlayerId() + "重置完成");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			
//			System.out.println("玩家Mecha重置完成");
//		}
//		else if(data[0].equals("playersTurrentReset")){
//			List<PlayersTest> playerlist = GameContext.instance().gameData().getAllData(-1, PlayersTest.class);
//			for(PlayersTest player : playerlist){
//				try {
//					List<Turret> dataDb =  GameContext.instance().gameData().getData(Turret.class, player.getPlayerId());
//					Turret turret = dataDb.get(0);
//					turret.setCoreLevel(player.getLevel());
//					turret.setCoreExp(player.getExp());
//					TurretManager.instance().update(turret);
//
//					System.out.println("玩家id:" + player.getPlayerId() + "重置完成");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			
//			System.out.println("玩家Turrent重置完成");
//		}
//		else{
//			System.out.println("不接受此命令: " + str);
//		}
//	}
	

}



