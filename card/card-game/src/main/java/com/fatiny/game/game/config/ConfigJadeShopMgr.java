package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigJadeShop;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigJadeShopMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigJadeShop.json";
	
	private static Map<Integer, ConfigJadeShop> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigJadeShop> list = JSON.parseArray(content, ConfigJadeShop.class);
		
		Map<Integer, ConfigJadeShop> temps = Maps.newHashMap();
		for(ConfigJadeShop config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigJadeShop getConfig(int id) {
		ConfigJadeShop data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigJadeShop! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static Map<Integer, ConfigJadeShop> getMaps(){
		return maps;
	}
	
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
