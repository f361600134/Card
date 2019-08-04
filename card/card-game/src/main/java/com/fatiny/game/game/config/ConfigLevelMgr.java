package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigLevel;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigLevelMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigLevel.json";
	
	private static Map<Integer, ConfigLevel> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigLevel> list = JSON.parseArray(content, ConfigLevel.class);
		
		Map<Integer, ConfigLevel> temps = Maps.newHashMap();
		for(ConfigLevel config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigLevel getConfig(int id) {
		ConfigLevel data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigLevel! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static final int NormalStageExp = 1;	//普通关卡经验
	public static final int EliteStageExp = 2;	//精英关卡经验
	public static final int SuperStageExp = 3;	//炼狱关卡经验
	
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
