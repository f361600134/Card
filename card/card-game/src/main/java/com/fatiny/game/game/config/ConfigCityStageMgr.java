package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigCityStage;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigCityStageMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigCityStage.json";
	
	private static Map<Integer, ConfigCityStage> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigCityStage> list = JSON.parseArray(content, ConfigCityStage.class);
		
		Map<Integer, ConfigCityStage> temps = Maps.newHashMap();
		for(ConfigCityStage config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigCityStage getConfig(int id) {
		ConfigCityStage data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigCityStage! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static final int Init_Sta = 300;		//初始耐力
	
	//Id:rate
	private static Map<Integer, Integer> idRatesMap;
	
	public static void analyse(){
		Map<Integer, Integer> tempIdRateMap = Maps.newHashMap();
		
		for(ConfigCityStage config : maps.values())
		{
			tempIdRateMap.put(config.getID(), config.getRate());
		}
		
		idRatesMap = tempIdRateMap;
	}
	
	public static void complete(){
	}
	
	public static Map<Integer, Integer> getIdRatesMap(){
		return idRatesMap;
	}
	
	/////////UserDefine End/////////////
	
}
