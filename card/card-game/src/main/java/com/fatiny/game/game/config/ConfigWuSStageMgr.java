package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigWuSStage;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigWuSStageMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigWuSStage.json";
	
	private static Map<Integer, ConfigWuSStage> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigWuSStage> list = JSON.parseArray(content, ConfigWuSStage.class);
		
		Map<Integer, ConfigWuSStage> temps = Maps.newHashMap();
		for(ConfigWuSStage config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigWuSStage getConfig(int id) {
		ConfigWuSStage data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigWuSStage! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static int DailyCount = 1;			//每日战斗次数
	
	public static void analyse(){
	}
	
	public static void complete(){
		DailyCount = ConfigFunctionMgr.getLimitCount(ConfigFunctionMgr.CategoryWusStage, ConfigFunctionMgr.LimitFreeCount);
	}
	
	/////////UserDefine End/////////////
	
}
