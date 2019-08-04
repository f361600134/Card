package com.fatiny.core.data;

import com.fatiny.core.client.db.DbServerClient;

/**
 * 数据持久上下文
 * 
 * @auth Jeremy
 * @date 2019年8月4日下午4:06:28
 */
public class DataContext {

	static final DataContext CONTEXT = new DataContext();

	public static DataContext instance() {
		return CONTEXT;
	}

	/** 游戏数据接口 */
	private IData gameData;

	DataContext() {

	}

	public DataContext depend(DbServerClient client) {
		SyncData syncData = new SyncData();
		syncData.injectDbServerConnector(client);
		this.gameData = syncData;
		client.init();
		return this;
	}

	public IData gameData() {
		return gameData;
	}

}
