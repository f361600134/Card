package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigCheckIn;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigCheckInMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigCheckIn.json";
	
	private static Map<Integer, ConfigCheckIn> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigCheckIn> list = JSON.parseArray(content, ConfigCheckIn.class);
		
		Map<Integer, ConfigCheckIn> temps = Maps.newHashMap();
		for(ConfigCheckIn config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigCheckIn getConfig(int id) {
		ConfigCheckIn data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigCheckIn! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static int MaxDays = 1;
	
	public static Map<Integer, ConfigCheckIn> getMaps(){
		return maps;
	}
	
	public static void analyse(){
		for(ConfigCheckIn config : maps.values())
		{
			if(config.getID()>MaxDays)
				MaxDays = config.getID();
		}
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
