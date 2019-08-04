package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigTreasureShop;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;
@AnnotationConfig
public class ConfigTreasureShopMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigTreasureShop.json";
	
	private static Map<Integer, ConfigTreasureShop> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigTreasureShop> list = JSON.parseArray(content, ConfigTreasureShop.class);
		
		Map<Integer, ConfigTreasureShop> temps = Maps.newHashMap();
		for(ConfigTreasureShop config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigTreasureShop getConfig(int id) {
		ConfigTreasureShop data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigTreasureShop! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static Map<Integer, ConfigTreasureShop> getMaps(){
		return maps;
	}
	
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
