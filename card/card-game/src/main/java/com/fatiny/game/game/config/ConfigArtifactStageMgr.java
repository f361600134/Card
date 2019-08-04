package com.fatiny.game.game.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigArtifactStage;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

@AnnotationConfig
public class ConfigArtifactStageMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigArtifactStage.json";
	
	private static Map<Integer, ConfigArtifactStage> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigArtifactStage> list = JSON.parseArray(content, ConfigArtifactStage.class);
		
		Map<Integer, ConfigArtifactStage> temps = Maps.newHashMap();
		for(ConfigArtifactStage config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigArtifactStage getConfig(int id) {
		ConfigArtifactStage data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigArtifactStage! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	
	public static Map<Integer, ConfigArtifactStage> getMaps() {
		return maps;
	}
	
	/////////UserDefine Begin///////////
	
	public static int LOCK = 0;			//未通关
	public static int OPENDED = 1;		//已开启未通关
	public static int PASSED = 2;		//已通关
	
	
	public static short DailyCount = 6;		//每日战斗次数
	public static short BuyCount = 2;		//每日购买次数
	
	public static int InitStage = 1001;	//初始副本
	public static int EndStage = 1037;	//结束副本
	
	/**
	 * key: 难度
	 * value: 副本ids
	 */
	private static Multimap<Integer, Integer> levelStageMaps;
	
	public static void analyse(){
		Multimap<Integer, Integer>  tempLevelMap= ArrayListMultimap.create(); 
		for (ConfigArtifactStage stage : maps.values()) {
			tempLevelMap.put(stage.getLevel(), stage.getID());
			InitStage = Math.min(InitStage, stage.getID());
			EndStage = Math.max(EndStage, stage.getID());
		}
		levelStageMaps = tempLevelMap;
	}
	
	public static void complete(){
		DailyCount = (short)ConfigFunctionMgr.getLimitCount(ConfigFunctionMgr.CategoryArtifactStage, ConfigFunctionMgr.LimitFreeCount);
		BuyCount = (short)ConfigFunctionMgr.getLimitCount(ConfigFunctionMgr.CategoryArtifactStage, ConfigFunctionMgr.LimitBuyCount);
	}
	
	
	/**
	 * 通过玩家等级获取副本
	 * @return 
	 */
	public static List<Integer> getConfigIdByLevel(int level) {
		Collection<Integer> result = levelStageMaps.get(level);
		return (List<Integer>) result;
	}
	
	/////////UserDefine End/////////////
	
}
