package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigQueue;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigQueueMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigQueue.json";
	
	private static Map<Integer, ConfigQueue> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigQueue> list = JSON.parseArray(content, ConfigQueue.class);
		
		Map<Integer, ConfigQueue> temps = Maps.newHashMap();
		for(ConfigQueue config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigQueue getConfig(int id) {
		ConfigQueue data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigQueue! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////

	public static void analyse(){
	}
	
	public static void complete(){
	}

	public static ConfigQueue getFirstUnlockQueue(int level)
	{
		for(ConfigQueue queue : maps.values())
		{
			if(queue.getLockLevel()<=level)
				return queue;
		}
		return null;
	}
	
	public static boolean isQueueUnLock(int configId, int level)
	{
		ConfigQueue queue = getConfig(configId);
		if(queue==null)
		{
			LOGGER.error("could not found ConfigQueue. configId={}", configId);
			return false;
		}
		return queue.getLockLevel()<=level;
	}
	/////////UserDefine End/////////////
	
}
