package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigActivityGrowthPlan;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigActivityGrowthPlanMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityGrowthPlanMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigActivityGrowthPlan.json";
	
	private static Map<Integer, ConfigActivityGrowthPlan> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigActivityGrowthPlan> list = JSON.parseArray(content, ConfigActivityGrowthPlan.class);
		
		Map<Integer, ConfigActivityGrowthPlan> temps = Maps.newHashMap();
		for(ConfigActivityGrowthPlan config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigActivityGrowthPlan getConfig(int id) {
		ConfigActivityGrowthPlan data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigActivityGrowthPlan! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static Map<Integer, ConfigActivityGrowthPlan> getMap() {
		return maps;
	}
	
	public static int needVip = 2;
	
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
