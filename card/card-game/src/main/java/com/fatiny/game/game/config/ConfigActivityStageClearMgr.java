package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigActivityStageClear;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigActivityStageClearMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigActivityStageClear.json";
	
	private static Map<Integer, ConfigActivityStageClear> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigActivityStageClear> list = JSON.parseArray(content, ConfigActivityStageClear.class);
		
		Map<Integer, ConfigActivityStageClear> temps = Maps.newHashMap();
		for(ConfigActivityStageClear config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigActivityStageClear getConfig(int id) {
		ConfigActivityStageClear data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigActivityStageClear! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static int TYPE_ELITE = 1;
	public static int TYPE_WUS = 2;
	
	public static Map<Integer, ConfigActivityStageClear> getMap(){
		return maps;
	}
	
	public static void analyse(){
		for (ConfigActivityStageClear clear : maps.values()) {
			clear.setType(clear.getCondition().get(0).get(0));
		}
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
