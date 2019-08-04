package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigBulidingUpgrade;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigBulidingUpgradeMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigBulidingUpgrade.json";
	
	private static Map<Integer, ConfigBulidingUpgrade> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigBulidingUpgrade> list = JSON.parseArray(content, ConfigBulidingUpgrade.class);
		
		Map<Integer, ConfigBulidingUpgrade> temps = Maps.newHashMap();
		for(ConfigBulidingUpgrade config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigBulidingUpgrade getConfig(int id) {
		ConfigBulidingUpgrade data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigBulidingUpgrade! fuck him! id={}", id);
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
	
	/////////UserDefine End/////////////
	
}
