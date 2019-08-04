package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigRank;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigRankMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigRank.json";
	
	private static Map<Integer, ConfigRank> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigRank> list = JSON.parseArray(content, ConfigRank.class);
		
		Map<Integer, ConfigRank> temps = Maps.newHashMap();
		for(ConfigRank config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigRank getConfig(int id) {
		ConfigRank data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigRank! fuck him! id={}", id);
		}
		return data;
	}
	
	
	
	public static Map<Integer, ConfigRank> getMaps() {
		return maps;
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
