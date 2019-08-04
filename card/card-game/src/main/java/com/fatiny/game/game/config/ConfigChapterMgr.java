package com.fatiny.game.game.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigChapter;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigChapterMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigChapter.json";
	
	private static Map<Integer, ConfigChapter> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigChapter> list = JSON.parseArray(content, ConfigChapter.class);
		
		Map<Integer, ConfigChapter> temps = Maps.newHashMap();
		for(ConfigChapter config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigChapter getConfig(int id) {
		ConfigChapter data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigChapter! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static final int ChapterType_Normal = 1;
	public static final int ChapterType_Elite = 2;
	public static final int ChapterType_Purgatory = 3;
	
	public static final int DailyCount_Elite = 3;
	public static final int DailyCount_Purgatory = 6;
	
	private static List<ConfigChapter> normalChapters = Lists.newArrayList();
	private static List<ConfigChapter> eliteChapters = Lists.newArrayList();	
	private static List<ConfigChapter> purgatoryChapters = Lists.newArrayList();	
	//副本id-->所属章节id
	private static Map<Integer, Integer> stageChapterMaps = Maps.newHashMap();
	//副本id-->影响到的章节id
	private static Map<Integer, List<Integer>> stageEffectChapterMaps = Maps.newHashMap();
	
	
	public static void analyse(){
		Map<Integer, Integer> temps = Maps.newHashMap();
		Map<Integer, List<Integer>> effectTemps = Maps.newHashMap();
		for(ConfigChapter chapter : maps.values())
		{
			List<Integer> stageList = chapter.getStageData();
			for(Integer stageId : stageList)
			{
				temps.put(stageId, chapter.getID());
			}
			
			if(chapter.getNeedStage()>0)
			{
				List<Integer> effects = effectTemps.get(chapter.getNeedStage());
				if(effects==null)
				{
					effects = Lists.newArrayList();
					effectTemps.put(chapter.getNeedStage(), effects);
				}
				effects.add(chapter.getID());
			}
		}
		stageChapterMaps = temps;
		stageEffectChapterMaps = effectTemps;
		
		Map<Integer, ConfigChapter> treeTemps = Maps.newTreeMap();
		treeTemps.putAll(maps);
		List<ConfigChapter> normalChapterTemps = Lists.newArrayList();
		List<ConfigChapter> eliteChapterTemps = Lists.newArrayList();
		List<ConfigChapter> purgatoryChapterTemps = Lists.newArrayList();
		for(ConfigChapter chapter : treeTemps.values())
		{
			if(chapter.getLevel() == ChapterType_Normal)
				normalChapterTemps.add(chapter);
			else if(chapter.getLevel() == ChapterType_Elite)
				eliteChapterTemps.add(chapter);
			else if(chapter.getLevel() == ChapterType_Purgatory)
				purgatoryChapterTemps.add(chapter);
		}
		normalChapters = normalChapterTemps;
		eliteChapters = eliteChapterTemps;
		purgatoryChapters = purgatoryChapterTemps;
	}
	
	public static void complete(){
	}
	
	public static Collection<ConfigChapter> getAllChapters()
	{
		return maps.values();
	}
	
	public static int getChapterByStage(int stageId) {
		return stageChapterMaps.getOrDefault(stageId, 0);
	}
	
	public static List<Integer> getEffectChapterByStage(int stageId) {
		return stageEffectChapterMaps.get(stageId);
	}
	
	public static List<ConfigChapter> getNormalChapters() {
		return normalChapters;
	}
	
	public static List<ConfigChapter> getPurgatoryChapters() {
		return purgatoryChapters;
	}
	
	public static List<ConfigChapter> getEliteChapters() {
		return eliteChapters;
	}
	
	public static List<ConfigChapter> getAllChapters(int type) {
		if(type == ChapterType_Purgatory)
			return purgatoryChapters;
		else if(type == ChapterType_Elite)
			return eliteChapters;
		else 
			return normalChapters;
	} 
	
	public static boolean isEliteOrPurgateory(int type) {
		return isElite(type) || isPurgateory(type);
	}
	
	public static boolean isElite(int type) {
		return type == ChapterType_Elite;
	}
	
	public static boolean isPurgateory(int type) {
		return type == ChapterType_Purgatory;
	}
	
	/////////UserDefine End/////////////
	
}
