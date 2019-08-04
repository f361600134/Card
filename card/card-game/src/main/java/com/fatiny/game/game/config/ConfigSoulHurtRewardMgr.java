package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigSoulHurtReward;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigSoulHurtRewardMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigSoulHurtReward.json";
	
	private static Map<Integer, ConfigSoulHurtReward> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigSoulHurtReward> list = JSON.parseArray(content, ConfigSoulHurtReward.class);
		
		Map<Integer, ConfigSoulHurtReward> temps = Maps.newHashMap();
		for(ConfigSoulHurtReward config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigSoulHurtReward getConfig(int id) {
		ConfigSoulHurtReward data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigSoulHurtReward! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	private static TreeMap<Integer, TreeMap<Integer, ConfigSoulHurtReward>> hurtRewardMap;
	
	public static void analyse(){
		TreeMap<Integer, TreeMap<Integer, ConfigSoulHurtReward>> tempHurtRewardMap = Maps.newTreeMap();
		for(ConfigSoulHurtReward config : maps.values())
		{
			TreeMap<Integer, ConfigSoulHurtReward> rewardMap = tempHurtRewardMap.get(config.getLevel());
			if(rewardMap==null)
			{
				rewardMap = Maps.newTreeMap();
				tempHurtRewardMap.put(config.getLevel(), rewardMap);
			}
			rewardMap.put(config.getHurt(), config);
		}
		hurtRewardMap = tempHurtRewardMap;
	}
	
	public static void complete(){
	}
	
	/**
	 * 根据boss等级以及伤害,获取奖励
	 * @param bossLevel
	 * @param hurt
	 * @return  
	 * @return ConfigSoulHurtReward  
	 * @date 2019年6月17日上午6:19:29
	 */
	public static ConfigSoulHurtReward getReward(int bossLevel, int hurt)
	{
		Integer levelKey = hurtRewardMap.ceilingKey(bossLevel);
		if(levelKey==null)
			return null;
		TreeMap<Integer, ConfigSoulHurtReward> hurtRewards = hurtRewardMap.get(levelKey);
		Integer hurtKey = hurtRewards.floorKey(hurt);
		if(hurtKey==null)
			return null;
		ConfigSoulHurtReward config = hurtRewards.get(hurtKey);
		return config;
	}
	
	/**
	 * 获取低于伤害的奖励
	 * @param bossLevel 
	 * @param hurt 
	 * @return  
	 * @return ConfigSoulHurtReward  
	 * @date 2019年6月17日上午6:19:29
	 */
	public static List<ConfigSoulHurtReward> getRewardLowerHurt(int bossLevel, int hurt)
	{
		Integer levelKey = hurtRewardMap.ceilingKey(bossLevel);
		if(levelKey==null)
			return null;
		TreeMap<Integer, ConfigSoulHurtReward> hurtRewards = hurtRewardMap.get(levelKey);
		List<ConfigSoulHurtReward> result = Lists.newArrayList();
		for (ConfigSoulHurtReward reward : hurtRewards.values()) {
			if (hurt >= reward.getHurt()) {
				result.add(reward);
			}
		}
		return result;
	}
	
	public static int getLevelKey(int bossLevel)
	{
		Integer levelKey = hurtRewardMap.ceilingKey(bossLevel);
		if(levelKey==null)
			return 0;
		return levelKey;
	}
	
	/////////UserDefine End/////////////
	
}
