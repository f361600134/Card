package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigSoulBoss;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigSoulBossMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigSoulBoss.json";
	
	private static Map<Integer, ConfigSoulBoss> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigSoulBoss> list = JSON.parseArray(content, ConfigSoulBoss.class);
		
		Map<Integer, ConfigSoulBoss> temps = Maps.newHashMap();
		for(ConfigSoulBoss config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigSoulBoss getConfig(int id) {
		ConfigSoulBoss data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigSoulBoss! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static int DailyCount = 1;			//每日战斗次数
	
	//所有的boss,按此顺序放出
	private static List<Integer> bossIds;
	
	public static void analyse(){
		List<Integer> tempBossIds = Lists.newArrayList();
		
		Map<Integer, ConfigSoulBoss> temps = Maps.newTreeMap();
		temps.putAll(maps);
		for(ConfigSoulBoss config : temps.values())
		{
			if(!tempBossIds.contains(config.getBoss()))
				tempBossIds.add(config.getBoss());
		}
		
		bossIds = tempBossIds;
	}
	
	public static void complete(){
		DailyCount = ConfigFunctionMgr.getLimitCount(ConfigFunctionMgr.CategorySoulStage, ConfigFunctionMgr.LimitFreeCount);
	}
	
	/**
	 * 所有的boss,按此顺序放出
	 */
	public static List<Integer> getBossIds(){
		return bossIds;
	}
	/**
	 * 获取试炼ID
	 */
	public static int getSoulId(int bossId, int level) {
		return bossId*1000+level;
	}
	/**
	 * 指定的boss等级是否存在
	 */
	public static boolean existLevel(int bossId, int level) {
		int id = getSoulId(bossId, level);
		return exist(id);
	}
	
	/////////UserDefine End/////////////
	
}
