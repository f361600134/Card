package com.fatiny.game.game.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigActivitySevenDay;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

@AnnotationConfig
public class ConfigActivitySevenDayMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigActivitySevenDay.json";
	
	private static Map<Integer, ConfigActivitySevenDay> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigActivitySevenDay> list = JSON.parseArray(content, ConfigActivitySevenDay.class);
		
		Map<Integer, ConfigActivitySevenDay> temps = Maps.newHashMap();
		for(ConfigActivitySevenDay config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigActivitySevenDay getConfig(int id) {
		ConfigActivitySevenDay data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigActivitySevenDay! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static int TYPE_LOGIN = 1;//登录
	public static int TYPE_RECHARGE = 2;//充值
	
	public static Multimap<Integer, ConfigActivitySevenDay> conditionMap;
	public static Map<Integer, ConfigActivitySevenDay> getMaps() {
		return maps;
	}
	public static Collection<ConfigActivitySevenDay> getConditionById(int type){
		return conditionMap.get(type);
	}
	
	public static void analyse(){
		Multimap<Integer, ConfigActivitySevenDay> tempMap = ArrayListMultimap.create();
		for (ConfigActivitySevenDay sevenDay : maps.values()) {
			tempMap.put(sevenDay.getType(), sevenDay);
		}
		conditionMap = tempMap;
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
