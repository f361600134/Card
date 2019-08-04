package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigBuyPower;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigBuyPowerMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigBuyPowerMgr.class); 
	
	private static String filename = Config.JsonConfigPath+"ConfigBuyPower.json";
	
	private static Map<Integer, ConfigBuyPower> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigBuyPower> list = JSON.parseArray(content, ConfigBuyPower.class);
		
		Map<Integer, ConfigBuyPower> temps = Maps.newHashMap();
		for(ConfigBuyPower config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigBuyPower getConfig(int id) {
		ConfigBuyPower data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigBuyPower! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static final int powerBuyCount = 1; //初始体力购买次数,策划说不改
	
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
