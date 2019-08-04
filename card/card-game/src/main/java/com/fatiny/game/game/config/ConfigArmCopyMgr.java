package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigArmCopy;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


@AnnotationConfig
public class ConfigArmCopyMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigArmCopy.json";
	
	private static Map<Integer, ConfigArmCopy> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigArmCopy> list = JSON.parseArray(content, ConfigArmCopy.class);
		
		Map<Integer, ConfigArmCopy> temps = Maps.newHashMap();
		for(ConfigArmCopy config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigArmCopy getConfig(int id) {
		ConfigArmCopy data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigArmCopy! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static int DailyCount = 6;			//每日战斗次数
	public static int MaxStageId = 0;			//最大副本Id
	
	private static Map<Integer, List<Integer>> openArmCopyMaps;			//开放等级:副本id列表
	
	public static void analyse(){
		Map<Integer, ConfigArmCopy> treeMaps = Maps.newTreeMap();
		treeMaps.putAll(maps);
		
		Map<Integer, List<Integer>> tempOpenArmCopyMaps = Maps.newHashMap();
		for(ConfigArmCopy config : treeMaps.values())
		{
			List<Integer> list = tempOpenArmCopyMaps.get(config.getOpenLevel());
			if(list==null)
			{
				list = Lists.newArrayList();
				tempOpenArmCopyMaps.put(config.getOpenLevel(), list);
			}
			list.add(config.getID());
			
			if(config.getID()>MaxStageId)
				MaxStageId = config.getID();
		}
		openArmCopyMaps = tempOpenArmCopyMaps;
	}
	
	public static void complete(){
		DailyCount = ConfigFunctionMgr.getLimitCount(ConfigFunctionMgr.CategoryArmCopy, ConfigFunctionMgr.LimitFreeCount);
	}
	
	/**
	 * 通过玩家等级获取受影响开放的副本列表，按难度升序
	 * @param level
	 * @return
	 */
	public static List<Integer> getOpenStageIdByPlayerLevel(int playerLevel){
		return openArmCopyMaps.get(playerLevel);
	}
	
	/////////UserDefine End/////////////
	
}
