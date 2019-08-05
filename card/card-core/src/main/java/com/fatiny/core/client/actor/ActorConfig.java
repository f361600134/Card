package com.fatiny.core.client.actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * Actor配置
 * 
 */
public class ActorConfig {
	
	private String actorName;
	private Map<Integer, Properties> configs = new HashMap<>(); 
	
	public ActorConfig(String actorName) {
		this.actorName = actorName;
	}
	
	public String getActorName() {
		return actorName;
	}

	public String getIp(int serverId) {
		Properties config = configs.get(serverId);
		return config.getProperty("ip");
	}
	
	public int getPort(int serverId) {
		Properties config = configs.get(serverId);
		return Integer.parseInt(config.getProperty("port"));
	}
	
	public int getIoThreads(int serverId) {
		Properties config = configs.get(serverId);
		return Integer.parseInt(config.getProperty("port", "4")); // 默认4线程
	}
	
	public List<Integer> allServerIds() {
		return new ArrayList<>(configs.keySet());
	}
	
	public void put(Integer key, Properties config) {
		this.configs.put(key, config);
	}
	
}
