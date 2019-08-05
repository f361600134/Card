package com.fatiny.game.base;

import com.fatiny.core.net.IServerHandler;
import com.fatiny.core.server.main.GameSession;
import com.fatiny.core.server.main.dispatcher.Dispatcher;
import com.fatiny.core.server.main.dispatcher.Dispatcher.Commander;

import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

/**
 * 游戏服分发处理器
 * 
 */
@Slf4j
public class GameServerHandler implements IServerHandler {
	
	private Dispatcher dispatcher;
	
	private DispatchExecutor executor;
	
	private boolean serverRunning; // 服务器状态, true-运行中
	
	public static GameServerHandler create(Dispatcher dispatcher) {
		GameServerHandler handler = new GameServerHandler(dispatcher);
		handler.executor = DispatchExecutor.create();	// 初始化业务线程
		handler.prepareShutdownThread();
		return handler;
	}
	
	public GameServerHandler(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	
	@Override
	public void onConnect(GameSession session) {
		log.info("客户端连接游戏服:{}", session.getChannel().remoteAddress());
	}

	@Override
	public void onReceive(GameSession session, ByteBuf message) {
		Packet packet = null;
		try {
			if (!serverRunning) {
				log.error("服务器不在运行状态, 舍弃消息"); 
				return;
			} 
			
			packet = Packet.decode(message);
			Commander commander = dispatcher.getCommander(packet.cmd());
			if (commander == null) {
				log.info("收到未处理协议, cmd=[{}]",  packet.cmd());
				return;
			}
			
			if (commander.isMustLogin()) {
//				if (session.getPlayerId() <= 0L) { // 未登录
//					log.debug("协议[{}]需要登录成功后才能请求", packet.cmd()); 
//					S2CCommonReply reply = S2CCommonReply.newBuilder().setProtoCode(packet.cmd()).setSuccess(1).build();
//					SimpleProtocol proto = SimpleProtocol.create(ProtocolCode.COMMON_S2C_REPLY, reply);
//					session.send(Packet.encode(proto));
//					return;
//				}
//				PlayerContext playerCtx = session.getUserData();
//				IPlayerActor playerActor = playerCtx.getPlayerActor();
//				playerActor.process(session, dispatcher, packet);
			} else {
				executor.addTask(session, dispatcher, packet);
			}
		} catch (Exception e) {
			if (packet == null) {
				log.error("协议解析失败", e);
			} else {
				log.error("Packet调用过程出错, cmd={}", packet.cmd(), e);
			}
		} 
		
	}

	@Override
	public void onClose(GameSession session) {
//		log.info("客户端连接断开:{}", session.getChannel().remoteAddress());
//		Object userData = session.getUserData();
//		PlayerManager pManager = PlayerManager.instance();
//		if (userData != null && userData instanceof String) {
//			pManager.endLogin((String) userData); // 清除登录标识
//			return;
//		}
//		PlayerContext playerCtx = pManager.getPlayer(session.getPlayerId());
//		if (playerCtx != null) {
//			GameSession playerSession = playerCtx.getSession();
//			if (playerSession == session) {
//				LogoutManager.addLogoutObj(playerCtx);
//			} else {
//				log.error("断开的session:{}和内存中的session:{}不一致", session, playerSession);
//			}
//		}
	}

	@Override
	public void onException(GameSession session, Throwable e) {
		log.error("游戏协议通信过程出错", e);
	}

	@Override
	public void serverStatus(boolean running) {
		this.serverRunning = running;
	}
	
	
	private void prepareShutdownThread() {
		Runtime.getRuntime().addShutdownHook(new ShutdownTask());
	}
	
	
	class ShutdownTask extends Thread {

		@Override
		public void run() {
			try {
//				GameServerHandler.this.serverStatus(false);
//				PlayerManager pManager = PlayerManager.instance();
//				pManager.kickAllPlayers();
//				
//				GameScheduler schd = GameContext.instance().getSched();
//				schd.pauseJob(CommonJobStart.AUTO_SAVE_JOB_NAME);
//				
//				IData dataSync = GameContext.instance().gameData();
//				Response resp0 = dataSync.executeBatchSave();
//				Response resp1 = dataSync.executeBatchSave();
//				
//				if (resp0 != null) 
//					resp0.sync(5000L);
//				if (resp1 != null) 
//					resp1.sync(5000L); 
				
			} catch (Exception e) {
				log.error("关服处理出现异常", e);
			}
		}
		
	}
	
	
}
