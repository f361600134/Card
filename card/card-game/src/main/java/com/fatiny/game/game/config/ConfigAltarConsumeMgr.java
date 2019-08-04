package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigAltarConsume;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigAltarConsumeMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigAltarConsume.json";
	
	private static Map<Integer, ConfigAltarConsume> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigAltarConsume> list = JSON.parseArray(content, ConfigAltarConsume.class);
		
		Map<Integer, ConfigAltarConsume> temps = Maps.newHashMap();
		for(ConfigAltarConsume config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigAltarConsume getConfig(int id) {
		ConfigAltarConsume data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigAltarConsume! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	private static int MaxBuyCount = 1;
	
	public static void analyse(){
		for(ConfigAltarConsume config : maps.values())
		{
			if(config.getID()>MaxBuyCount)
				MaxBuyCount = config.getID();
		}
	}
	
	public static void complete(){
	}
	
	public static ConfigAltarConsume getExistConfig(int id){
		if(id<1)
			id = 1;
		else if(id>MaxBuyCount)
			id = MaxBuyCount;
		return getConfig(id);
	}
	
	/////////UserDefine End/////////////
	
}
