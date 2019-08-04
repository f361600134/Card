package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigAltarNormal;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigAltarNormalMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigAltarNormal.json";
	
	private static Map<Integer, ConfigAltarNormal> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigAltarNormal> list = JSON.parseArray(content, ConfigAltarNormal.class);
		
		Map<Integer, ConfigAltarNormal> temps = Maps.newHashMap();
		for(ConfigAltarNormal config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigAltarNormal getConfig(int id) {
		ConfigAltarNormal data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigAltarNormal! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static final int Altar_Gold_Id = 1;
	public static final int Altar_Equip_Id = 2;
	public static final int Altar_Ghost_Id = 3;
	public static final int Altar_Gem_Id = 4;
	
	public static int DailyFreeCountGold = 1;		//金币祭祀每日免费次数
	public static int DailyFreeCountEquip = 1;	//装备祭祀每日免费次数
	public static int DailyFreeCountGhost = 1;	//星魂祭祀每日免费次数
	public static int DailyFreeCountGem = 1;		//宝石祭祀每日免费次数
	
	public static void analyse(){
	}
	
	public static void complete(){
		DailyFreeCountGold = ConfigFunctionMgr.getLimitCount(ConfigFunctionMgr.CategoryAltarGold, ConfigFunctionMgr.LimitFreeCount);
		DailyFreeCountEquip = ConfigFunctionMgr.getLimitCount(ConfigFunctionMgr.CategoryAltarEquip, ConfigFunctionMgr.LimitFreeCount);
		DailyFreeCountGhost = ConfigFunctionMgr.getLimitCount(ConfigFunctionMgr.CategoryAltarGhost, ConfigFunctionMgr.LimitFreeCount);
		DailyFreeCountGem = ConfigFunctionMgr.getLimitCount(ConfigFunctionMgr.CategoryAltarGem, ConfigFunctionMgr.LimitFreeCount);
	}
	
	/////////UserDefine End/////////////
	
}
