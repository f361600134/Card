package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigTreasureShopRefresh;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigTreasureShopRefreshMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigTreasureShopRefresh.json";
	
	private static Map<Integer, ConfigTreasureShopRefresh> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigTreasureShopRefresh> list = JSON.parseArray(content, ConfigTreasureShopRefresh.class);
		
		Map<Integer, ConfigTreasureShopRefresh> temps = Maps.newHashMap();
		for(ConfigTreasureShopRefresh config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigTreasureShopRefresh getConfig(int id) {
		ConfigTreasureShopRefresh data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigTreasureShopRefresh! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static int DailyFreeRefreshCount = 1;	//每日免费刷新次数
	public static final int RefreshCDTime = 5400;	//1.5 hour免费刷新次数恢复时长
	private static int MaxBuyCount = 1;
	
	public static void analyse(){
		for(ConfigTreasureShopRefresh config : maps.values())
		{
			if(config.getID()>MaxBuyCount)
				MaxBuyCount = config.getID();
		}
	}
	
	public static void complete(){
		DailyFreeRefreshCount = ConfigFunctionMgr.getLimitCount(ConfigFunctionMgr.CategoryShopTreasure, ConfigFunctionMgr.LimitRefreshCount);
	}
	
	public static ConfigTreasureShopRefresh getExistConfig(int id){
		if(id<1)
			id = 1;
		else if(id>MaxBuyCount)
			id = MaxBuyCount;
		return getConfig(id);
	}
	
	/////////UserDefine End/////////////
	
}
