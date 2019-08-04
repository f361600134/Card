package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigBuyEliteCount;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigBuyEliteCountMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigBuyEliteCount.json";
	
	private static Map<Integer, ConfigBuyEliteCount> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigBuyEliteCount> list = JSON.parseArray(content, ConfigBuyEliteCount.class);
		
		Map<Integer, ConfigBuyEliteCount> temps = Maps.newHashMap();
		for(ConfigBuyEliteCount config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigBuyEliteCount getConfig(int id) {
		ConfigBuyEliteCount data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigBuyEliteCount! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static int MaxBuyCount = 1;		//解析最大购买次数12
	public static int TotalBuyCount = 3;	//普通用户每日购买次数
	
	public static void analyse(){
		for(ConfigBuyEliteCount config : maps.values())
		{
			if(config.getID()>MaxBuyCount)
				MaxBuyCount = config.getID();
		}
	}
	
	public static void complete(){
	}
	
	public static ConfigBuyEliteCount getExistConfig(int id){
		if(id<1)
			id = 1;
		else if(id>MaxBuyCount)
			id = MaxBuyCount;
		return getConfig(id);
	}
	
	/////////UserDefine End/////////////
	
}
