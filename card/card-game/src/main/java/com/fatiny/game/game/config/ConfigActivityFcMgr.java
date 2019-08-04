package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigActivityFc;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigActivityFcMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityFcMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigActivityFc.json";
	
	private static Map<Integer, ConfigActivityFc> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigActivityFc> list = JSON.parseArray(content, ConfigActivityFc.class);
		
		Map<Integer, ConfigActivityFc> temps = Maps.newHashMap();
		for(ConfigActivityFc config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigActivityFc getConfig(int id) {
		ConfigActivityFc data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigActivityFc! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static Map<Integer, ConfigActivityFc> getMap(){
		return maps;
	}
	
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
