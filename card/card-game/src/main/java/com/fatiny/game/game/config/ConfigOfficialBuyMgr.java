package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigOfficialBuy;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigOfficialBuyMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigOfficialBuy.json";
	
	private static Map<Integer, ConfigOfficialBuy> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigOfficialBuy> list = JSON.parseArray(content, ConfigOfficialBuy.class);
		
		Map<Integer, ConfigOfficialBuy> temps = Maps.newHashMap();
		for(ConfigOfficialBuy config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigOfficialBuy getConfig(int id) {
		ConfigOfficialBuy data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigOfficialBuy! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static int maxBuyCount = 10; //最大购买次数
	
	public static int getNeedIngot(int nextCount) {
		ConfigOfficialBuy config = null;
		if (nextCount > maxBuyCount) {
			config = maps.get(maxBuyCount);
		}else {
			config = maps.get(nextCount);
		}
		return config.getDiamondConsume();
	}  
	
	public static void analyse(){
		for (ConfigOfficialBuy config : maps.values()) {
			maxBuyCount = Math.max(config.getID(), maxBuyCount);
		}
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
