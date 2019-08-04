package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigMilitaryShop;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigMilitaryShopMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigMilitaryShop.json";
	
	private static Map<Integer, ConfigMilitaryShop> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigMilitaryShop> list = JSON.parseArray(content, ConfigMilitaryShop.class);
		
		Map<Integer, ConfigMilitaryShop> temps = Maps.newHashMap();
		for(ConfigMilitaryShop config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigMilitaryShop getConfig(int id) {
		ConfigMilitaryShop data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigMilitaryShop! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static Map<Integer, ConfigMilitaryShop> getMaps(){
		return maps;
	}
	
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
