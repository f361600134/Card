package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigCitySearch;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigCitySearchMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigCitySearch.json";
	
	private static Map<Integer, ConfigCitySearch> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigCitySearch> list = JSON.parseArray(content, ConfigCitySearch.class);
		
		Map<Integer, ConfigCitySearch> temps = Maps.newHashMap();
		for(ConfigCitySearch config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigCitySearch getConfig(int id) {
		ConfigCitySearch data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigCitySearch! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	private static ConfigCitySearch last;
	
	public static void analyse(){
		for(ConfigCitySearch config : maps.values())
		{
			if(last==null || config.getID() > last.getID())
				last = config;
		}
	}
	
	public static void complete(){
	}
	
	public static ConfigCitySearch getConfigForCount(int searchCount) {
		ConfigCitySearch data = maps.get(searchCount);
		if(data==null)
			return last;
		return data;
	}
	
	/////////UserDefine End/////////////
	
}
