package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigHall;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigHallMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigHall.json";
	
	private static Map<Integer, ConfigHall> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigHall> list = JSON.parseArray(content, ConfigHall.class);
		
		Map<Integer, ConfigHall> temps = Maps.newHashMap();
		for(ConfigHall config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigHall getConfig(int id) {
		ConfigHall data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigHall! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	//初始技能
	private static List<Integer> initSkills = Lists.newArrayList();
	//技能id:ConfigHall
	private static Map<Integer, ConfigHall> skillMaps = Maps.newHashMap();
	//主公殿等级:SkillCount
	private static TreeMap<Integer, Integer> skillCountMaps = Maps.newTreeMap();
	
	public static void analyse(){
		List<Integer> tempInitSkills = Lists.newArrayList();
		Map<Integer, ConfigHall> temps = Maps.newHashMap();
		TreeMap<Integer, Integer> countTemps = Maps.newTreeMap();
		for(ConfigHall config : maps.values())
		{
			if(config.getSkill()>0)
			{
				temps.put(config.getSkill(), config);
				if(config.getLevel()==1) {
					tempInitSkills.add(config.getSkill());
					//initSkills.add(config.getLevel());
				}
			}
			countTemps.put(config.getLevel(), config.getBattleskill());
		}
		initSkills = tempInitSkills;
		skillMaps = temps;
		skillCountMaps = countTemps;
	}
	
	public static void complete(){
	}
	
	public static ConfigHall getConfigBySkillId(int skillId) {
		return skillMaps.get(skillId);
	}
	
	public static int getSkillCount(int hallLevel) {
		Entry<Integer, Integer> entry = skillCountMaps.floorEntry(hallLevel);
		if(entry==null)
			return 0;
		else
			return entry.getValue();
	}
	
	public static List<Integer> getInitSkillIds(){
		return initSkills;
	}
	
	
	/////////UserDefine End/////////////
	
}
