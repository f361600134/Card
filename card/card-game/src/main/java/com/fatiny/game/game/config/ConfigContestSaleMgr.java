package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigContestSale;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigContestSaleMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigContestSale.json";
	
	private static Map<Integer, ConfigContestSale> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigContestSale> list = JSON.parseArray(content, ConfigContestSale.class);
		
		Map<Integer, ConfigContestSale> temps = Maps.newHashMap();
		for(ConfigContestSale config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigContestSale getConfig(int id) {
		ConfigContestSale data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigContestSale! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static int maxBuyCount = 10;
	public static int getNeedIngot(int nextCount) {
		ConfigContestSale config = null;
		if (nextCount > maxBuyCount) {
			config = maps.get(maxBuyCount);
		}else {
			config = maps.get(nextCount);
		}
		return config.getDiamondConsume();
	} 
	
	public static void analyse(){
	}
	
	public static void complete(){
		for (ConfigContestSale config : maps.values()) {
			maxBuyCount = Math.max(config.getID(), maxBuyCount);
		}
	}
	
	/////////UserDefine End/////////////
	
}
