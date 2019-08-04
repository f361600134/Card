package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigAltarGoldRate;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigAltarGoldRateMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigAltarGoldRate.json";
	
	private static Map<Integer, ConfigAltarGoldRate> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigAltarGoldRate> list = JSON.parseArray(content, ConfigAltarGoldRate.class);
		
		Map<Integer, ConfigAltarGoldRate> temps = Maps.newHashMap();
		for(ConfigAltarGoldRate config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigAltarGoldRate getConfig(int id) {
		ConfigAltarGoldRate data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigAltarGoldRate! fuck him! id={}", id);
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
