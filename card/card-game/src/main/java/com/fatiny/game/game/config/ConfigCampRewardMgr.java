package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigCampReward;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigCampRewardMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigCampReward.json";
	
	private static Map<Integer, ConfigCampReward> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigCampReward> list = JSON.parseArray(content, ConfigCampReward.class);
		
		Map<Integer, ConfigCampReward> temps = Maps.newHashMap();
		for(ConfigCampReward config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigCampReward getConfig(int id) {
		ConfigCampReward data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigCampReward! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	private static ConfigCampReward campReward = null;
	
	public static void analyse(){
		for(ConfigCampReward reward : maps.values())
		{
			campReward = reward;
			break;
		}
	}
	
	public static void complete(){
	}

	public static Map<Integer, Integer> getCampRewards() {
		return campReward==null?Maps.newHashMap():campReward.getCampRewardMap();
	}
	
	/////////UserDefine End/////////////
	
}
