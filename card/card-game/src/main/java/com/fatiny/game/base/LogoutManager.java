package com.fatiny.game.base;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.fatiny.game.game.module.player.domain.PlayerContext;

import lombok.extern.slf4j.Slf4j;


/**
 * 下线事件管理
 */
@Slf4j
public class LogoutManager {
	
	static final long INTERVAL = 5 * 60 * 1000L;
	static final long OFFSET = 2 * 60 * 1000L;
	
	static Set<ILogoutListener> logoutListeners = new HashSet<>();
	
	static ConcurrentMap<Long, PlayerContext> logoutObjs = new ConcurrentHashMap<>();
	
	static void initEvents() {
		RuntimeClassManager clsManager = RuntimeClassManager.instance();
		Collection<Class<?>> classes = clsManager.getClassByType(ILogoutListener.class);
		log.info("初始化离线事件数：" + classes.size());
		int count = 0;
		for (Class<?> clazz : classes) {
			String clsName = clazz.getSimpleName();
			try {
				ILogoutListener listenerObj = null;
				Field[] fields = clazz.getDeclaredFields();
				for (Field element : fields) {
					if (InitializationManager.isSingleton(element, clazz)) {
						element.setAccessible(true);
						Object instance = element.get(null);
						if (instance == null) {
							instance = (listenerObj == null ? clazz.newInstance() : listenerObj);
							element.set(instance, instance);
						}
						listenerObj = (ILogoutListener) instance;
						logoutListeners.add(listenerObj);
						count ++;
					}
				}
				log.info("  ->{}", clsName);
			} catch (Exception e) {
				log.error("初始化下线监听器异常:{}", clsName, e);
			}
		}
		log.info("成功加载离线事件数："+ count);
	}
	
	
	static void addLogoutObj(PlayerContext playerContext) {
		playerContext.disconnect();
		logoutObjs.put(playerContext.getId(), playerContext);
	}
	
	
	public static void handleLogout() {
		if (logoutObjs.isEmpty()) {
			return;
		}
		for (PlayerContext playerCtx : logoutObjs.values()) {
			logout(playerCtx);
		}
	}
	
	
	public static void cleanUpCache(PlayerContext playerCtx) {
		if (playerCtx.isOnline()) {
			return;
		}
		log.debug("清理玩家[{}]离线缓存", playerCtx.getId());
		for (ILogoutListener logoutListener : logoutListeners) {
			logoutListener.logout(playerCtx);
		}
	}
	
	
	static void logout(PlayerContext player) {
//		PlayerContext playerCtx = PlayerManager.instance().getPlayer(player.getId());
//		synchronized (playerCtx) {
//			if (playerCtx.isOnline()) {
//				remove(playerCtx);
//				return;
//			}
//			long disConnTime = playerCtx.getOfflineTime();
//			long current = System.currentTimeMillis();
//			if (disConnTime <= 0) {
//				return;
//			}
//			long curInterval = current - disConnTime;
//			if (curInterval < INTERVAL || curInterval >= INTERVAL + OFFSET) {
//				return;
//			}
//			
//			log.debug("玩家[{}]离线, 清理缓存", playerCtx.getId());
//			PhoneMsgManager.instance().doPushOne(playerCtx); // 消息推送...
//			for (ILogoutListener logoutListener : logoutListeners) {
//				logoutListener.logout(playerCtx);
//			}
//			logoutObjs.remove(playerCtx.getId());
//			playerCtx.destroy();
//		}
	}
	
	
	static void remove(PlayerContext playerCtx) {
		logoutObjs.remove(playerCtx.getId());
	}
	
	
}
