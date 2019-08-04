package com.fatiny.game.base;

/**
 * 启动初始化监听器
 * 
 * @author huachp
 */
public interface InitializeListener {
	
	int GAME = 1;  // 游戏通用初始化加载
	int LOGIN = 2; 
	int GAME_SERVER_ONLY = 3;  // 游戏服初始化加载
	int FIGHT_SERVER_ONLY = 4; // 战斗服初始化加载
	
	/**
	 * 初始化逻辑
	 * 
	 * @throws Exception
	 */
	void toInit() throws Exception;
	
	
	int module();
	
}
