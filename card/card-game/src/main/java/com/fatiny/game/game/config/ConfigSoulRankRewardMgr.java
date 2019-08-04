package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigSoulRankReward;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigSoulRankRewardMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigSoulRankReward.json";
	
	private static Map<Integer, ConfigSoulRankReward> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigSoulRankReward> list = JSON.parseArray(content, ConfigSoulRankReward.class);
		
		Map<Integer, ConfigSoulRankReward> temps = Maps.newHashMap();
		for(ConfigSoulRankReward config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigSoulRankReward getConfig(int id) {
		ConfigSoulRankReward data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigSoulRankReward! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	private static TreeMap<Integer, TreeMap<Integer, ConfigSoulRankReward>> rankRewardMap;
	
	public static void analyse(){
		TreeMap<Integer, TreeMap<Integer, ConfigSoulRankReward>> tempRankRewardMap = Maps.newTreeMap();
		for(ConfigSoulRankReward config : maps.values())
		{
			TreeMap<Integer, ConfigSoulRankReward> rewardMap = tempRankRewardMap.get(config.getLevel());
			if(rewardMap==null)
			{
				rewardMap = Maps.newTreeMap();
				tempRankRewardMap.put(config.getLevel(), rewardMap);
			}
			rewardMap.put(config.getRank(), config);
		}
		rankRewardMap = tempRankRewardMap;
	}
	
	public static void complete(){
	}
	
	public static ConfigSoulRankReward getReward(int bossLevel, int rank)
	{
		Integer levelKey = rankRewardMap.ceilingKey(bossLevel);
		if(levelKey==null)
			return null;
		TreeMap<Integer, ConfigSoulRankReward> rankRewards = rankRewardMap.get(levelKey);
		Integer rankKey = rankRewards.floorKey(rank);
		if(rankKey==null)
			return null;
		ConfigSoulRankReward config = rankRewards.get(rankKey);
		return config;
	}
	
	public static int getLevelKey(int bossLevel)
	{
		Integer levelKey = rankRewardMap.ceilingKey(bossLevel);
		if(levelKey==null)
			return 0;
		return levelKey;
	}
	
	/////////UserDefine End/////////////
	
}
