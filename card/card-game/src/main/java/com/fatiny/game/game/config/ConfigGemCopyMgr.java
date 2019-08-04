package com.fatiny.game.game.config;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigGemCopy;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigGemCopyMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigGemCopy.json";
	
	private static Map<Integer, ConfigGemCopy> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigGemCopy> list = JSON.parseArray(content, ConfigGemCopy.class);
		
		Map<Integer, ConfigGemCopy> temps = Maps.newHashMap();
		for(ConfigGemCopy config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigGemCopy getConfig(int id) {
		ConfigGemCopy data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigGemCopy! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static int DailyCount = 6;		//每日战斗次数
	public static int MaxLevel = 0;				//最高层级
	
	private static Map<Integer, List<Integer>> levelGemCopyMaps;		//矿洞层数:副本id列表
	private static Map<Integer, List<Integer>> openGemCopyMaps;			//矿洞开放等级:副本层级
	
	public static void analyse(){
		
		Map<Integer, List<Integer>> tempLevelGemCopyMaps = Maps.newHashMap();
		Map<Integer, List<Integer>> tempOpenGemCopyMaps = Maps.newHashMap();
		List<ConfigGemCopy> sortedTemps = Lists.newArrayList();
		sortedTemps.addAll(maps.values());
		//按层级和难度升序
		Collections.sort(sortedTemps, new Comparator<ConfigGemCopy>() {
            @Override
            public int compare(ConfigGemCopy o1, ConfigGemCopy o2) {
                //o1-o2 返回值为int类型，大于0表示正序，小于0表示逆序
            	if(o1.getLevel()>o2.getLevel())
            		return 1;
            	else if(o1.getLevel()<o2.getLevel())
            		return -1;
            	else if(o1.getDifficulty()>o2.getDifficulty())
            		return 1;
            	else
            		return -1;
            }
        });

		
		for(ConfigGemCopy config : sortedTemps)
		{
			List<Integer> list = tempLevelGemCopyMaps.get(config.getLevel());
			if(list==null)
			{
				list = Lists.newArrayList();
				tempLevelGemCopyMaps.put(config.getLevel(), list);
			}
			list.add(config.getID());
			
			list = tempOpenGemCopyMaps.get(config.getOpenlevel());
			if(list==null)
			{
				list = Lists.newArrayList();
				tempOpenGemCopyMaps.put(config.getOpenlevel(), list);
			}
			if(!list.contains(config.getLevel()))
				list.add(config.getLevel());
			
			if(config.getLevel()>MaxLevel)
				MaxLevel = config.getLevel();
		}
		levelGemCopyMaps = tempLevelGemCopyMaps;
		openGemCopyMaps = tempOpenGemCopyMaps;
	}
	
	public static void complete(){
		DailyCount = ConfigFunctionMgr.getLimitCount(ConfigFunctionMgr.CategoryGemCopy, ConfigFunctionMgr.LimitFreeCount);
	}
	
	/**
	 * 通过层级获取副本列表，按难度升序
	 * @param level
	 * @return
	 */
	public static List<Integer> getStageIdByLevel(int level){
		return levelGemCopyMaps.get(level);
	}
	
	/**
	 * 通过玩家等级获取受影响开放的层级列表，按难度升序
	 * @param level
	 * @return
	 */
	public static List<Integer> getOpenLevelByPlayerLevel(int playerLevel){
		return openGemCopyMaps.get(playerLevel);
	}
	
	/////////UserDefine End/////////////
	
}
