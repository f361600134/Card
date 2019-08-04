package com.fatiny.game.actor.local;

import com.fatiny.core.server.main.GameSession;
import com.fatiny.core.server.main.dispatcher.Dispatcher;
import com.fatiny.game.base.Packet;
import com.fatiny.game.game.module.player.domain.PlayerContext;


/**
 * IPlayerActor
 */
public interface IPlayerActor {
	
	/**
	 * 初始化上下文
	 * 
	 * @param playerCtx
	 */
	boolean initContext(PlayerContext playerCtx);
	
	/**
	 * 协议分发处理
	 * 
	 * @param dispatcher
	 * @param session
	 */
	void process(GameSession session, Dispatcher dispatcher, Packet packet);
	
	/**
	 * 定时保存在线玩家数据
	 */
	void autoSave();
	
	/**
	 * 下线处理
	 * 
	 * @return {@link Boolean}
	 */
	boolean logout();
	
	
	/**
	 * 每日刷新
	 * 
	 * @return {@link Boolean}
	 */
	void refreshDaily();
	
	
	/**
	 * 每分钟刷新
	 * 
	 * @return {@link Boolean}
	 */
	void refreshMinute();
	
	
	/**
	 * 登录
	 * 
	 * @param session
	 * @param isCreate
	 */
	boolean login(GameSession session, boolean isCreate);
	
	
	/**
	 * 玩家获得奖励
	 * 
	 * @param materials
	 */
	//void reward(List<Material> materials, String source);
	
	/**
	 * 加资源
	 * @param resourceType
	 * @param count
	 * @param source
	 */
	void addResource(int resourceType, int count, String source);
	
	
	/**
	 * 添加充值币
	 * 
	 * @param count 充值能源
	 */
	void rechargeUpdate(int count);
	
	
	/**
	 * 发事件
	 * 
	 * @param events
	 */
	void postEvent(Object... events);
	
	
	boolean joinLegion(long legionId, boolean autoJoin);
	
	void removeFriend(PlayerContext otherCtx);
	
	boolean agreeAddFriend(PlayerContext otherCtx);
	
	short getRelationById(long relationId);
}
