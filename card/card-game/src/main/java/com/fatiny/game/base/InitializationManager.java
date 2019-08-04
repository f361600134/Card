package com.fatiny.game.base;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;

import lombok.extern.slf4j.Slf4j;


/**
 * 初始化管理
 */
@Slf4j
public class InitializationManager {
	
	/**
	 * 初始化
	 * 
	 * @param module 对应模块
	 * @see InitializeListener.LOGIN
	 * @see InitializeListener.GAME
	 */
	static void init(int module) {
		RuntimeClassManager clsManager = RuntimeClassManager.instance();
		Collection<Class<?>> initializeListeners = clsManager.getClassByType(InitializeListener.class);
		log.debug("游戏模块初始化");
		for(Class<?> clazz : initializeListeners) {
			String clsName = clazz.getSimpleName();
			try {
				InitializeListener initializeListener = (InitializeListener) clazz.newInstance();
				if (initializeListener.module() != module) {
					continue;
				}
				Field[] fields = clazz.getDeclaredFields();
				for (Field element : fields) {
					if (isSingleton(element, clazz)) {
						element.setAccessible(true);
						element.set(initializeListener, initializeListener);
					}
				}
				log.debug("  ->{}", clsName);
				long initTime = System.currentTimeMillis() ;
				initializeListener.toInit();
				log.debug("初始化:{}耗时{}ms", clsName, (System.currentTimeMillis() - initTime));
			} catch (Exception e) {
				log.error("初始化信息异常:{}", clsName, e);
			}
		}
		
		LogoutManager.initEvents(); // 初始化下线事件
	}
	
	
	static boolean isSingleton(Field element, Class<?> cls) {
		if (element.getType() != cls) {
			return false;
		}
		if (!Modifier.isStatic(element.getModifiers())) {
			return false;
		}
		if (Modifier.isFinal(element.getModifiers())) {
			return false;
		}
		return true;
	}
	
	
}
