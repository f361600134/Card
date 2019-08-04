package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigArtifactBuyCount;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigArtifactBuyCountMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigArtifactBuyCount.json";
	
	private static Map<Integer, ConfigArtifactBuyCount> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigArtifactBuyCount> list = JSON.parseArray(content, ConfigArtifactBuyCount.class);
		
		Map<Integer, ConfigArtifactBuyCount> temps = Maps.newHashMap();
		for(ConfigArtifactBuyCount config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigArtifactBuyCount getConfig(int id) {
		ConfigArtifactBuyCount data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigArtifactBuyCount! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static int maxCount = 0;
	public static void analyse(){
		for (ConfigArtifactBuyCount data : maps.values()) 
			maxCount = Math.max(maxCount, data.getID());
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
