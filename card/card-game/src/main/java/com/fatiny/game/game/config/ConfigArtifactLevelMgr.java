package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigArtifactLevel;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigArtifactLevelMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);
	private static String filename = Config.JsonConfigPath+"ConfigArtifactLevel.json";
	
	private static Map<Integer, ConfigArtifactLevel> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigArtifactLevel> list = JSON.parseArray(content, ConfigArtifactLevel.class);
		
		Map<Integer, ConfigArtifactLevel> temps = Maps.newHashMap();
		for(ConfigArtifactLevel config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigArtifactLevel getConfig(int id) {
		ConfigArtifactLevel data = maps.get(id);
		if(data==null) 
		{
			LOGGER.error("shit! ce hua config error in ConfigArtifactLevel! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	/**
	 * 通过武将配置id, 等级获取配置
	 * maps.key =  configId * 1000 + level
	 * @param configId 
	 * @param level
	 * @return
	 */
	public static ConfigArtifactLevel getArtifaceLevel(int configId, int level) {
		int key = configId * 1000 + level;
		ConfigArtifactLevel config = maps.get(key);
		if (config == null) {
			LOGGER.error("shit! ce hua config error in ConfigArtifactLevel! fuck him! id={}", key);
		}
		return config;
	}
	
	
	/////////UserDefine End/////////////
	
}
