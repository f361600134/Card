package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigSuit;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.CollectionUtilitys;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigSuitMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigSuit.json";
	
	private static Map<Integer, ConfigSuit> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigSuit> list = JSON.parseArray(content, ConfigSuit.class);
		
		Map<Integer, ConfigSuit> temps = Maps.newHashMap();
		for(ConfigSuit config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigSuit getConfig(int id) {
		ConfigSuit data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigSuit! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static int RequireMinSuits = 10;		//最少需要的装备数
	//equipId-->List<suitId>
	private static Map<Integer, List<Integer>> EquipEffectSuitMap = null;
	
	public static void analyse(){
		Map<Integer, List<Integer>> tempEquipSuitMap = Maps.newHashMap();
		
		for(ConfigSuit config : maps.values())
		{
			if(RequireMinSuits > config.getNum())
				RequireMinSuits = config.getNum();
			
			for(Integer equipConfigId : config.getNeedEquips())
			{
				List<Integer> suitIds = tempEquipSuitMap.get(equipConfigId);
				if(suitIds==null)
				{
					suitIds = Lists.newArrayList();
					tempEquipSuitMap.put(equipConfigId, suitIds);
				}
				suitIds.add(config.getID());
			}
		}
		EquipEffectSuitMap = tempEquipSuitMap;
	}
	
	public static void complete(){
	}
	
	public static void getRewards(List<Integer> equips, Map<Integer, Integer> attrs){
		if(equips.size()<RequireMinSuits)
			return;
		
		Map<Integer, Integer> suitCount = Maps.newHashMap();
		for(Integer equipId : equips)
		{
			List<Integer> suits = EquipEffectSuitMap.get(equipId);
			if(suits==null || suits.isEmpty())
				continue;
			for(Integer suitId : suits)
			{
				int count = suitCount.getOrDefault(suitId, 0);
				count++;
				suitCount.put(suitId, count);
			}
		}
		for(Entry<Integer, Integer> entry : suitCount.entrySet())
		{
			rewardAttr(entry.getKey(), entry.getValue(), attrs);
		}
	}
	
	private static void rewardAttr(int configId, int count, Map<Integer, Integer> attrs) {
		if(count<2)
			return;
		ConfigSuit suit = getConfig(configId);
		if(count>=6)
			CollectionUtilitys.mergeToMap(suit.getEquips6Map(), attrs);
		else if(count>=4)
			CollectionUtilitys.mergeToMap(suit.getEquips4Map(), attrs);
		else
			CollectionUtilitys.mergeToMap(suit.getEquips2Map(), attrs);
	}
	
	
	/////////UserDefine End/////////////
	
}
