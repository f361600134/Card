package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigActivityGroupBuy;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigActivityGroupBuyMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityGroupBuyMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigActivityGroupBuy.json";
	
	private static Map<Integer, ConfigActivityGroupBuy> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigActivityGroupBuy> list = JSON.parseArray(content, ConfigActivityGroupBuy.class);
		
		Map<Integer, ConfigActivityGroupBuy> temps = Maps.newHashMap();
		for(ConfigActivityGroupBuy config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigActivityGroupBuy getConfig(int id) {
		ConfigActivityGroupBuy data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigActivityGroupBuy! fuck him! id={}", id);
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
	
	/////////UserDefine End/////////////
	
}
