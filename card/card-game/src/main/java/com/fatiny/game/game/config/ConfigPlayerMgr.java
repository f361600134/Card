package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigPlayer;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@AnnotationConfig
public class ConfigPlayerMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigPlayer.json";
	
	private static Map<Integer, ConfigPlayer> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigPlayer> list = JSON.parseArray(content, ConfigPlayer.class);
		
		Map<Integer, ConfigPlayer> temps = Maps.newHashMap();
		for(ConfigPlayer config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigPlayer getConfig(int id) {
		ConfigPlayer data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigPlayer! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	//1=魏;2=蜀;3=吴
	public static final int CampWei = 1;
	public static final int CampShu = 2;
	public static final int CampWu = 3;
	public static final Set<Integer> campSet = Sets.newHashSet(CampWei, CampShu, CampWu);
	
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	public static boolean isCampValid(int camp)
	{
		return camp==CampWei || camp==CampShu || camp==CampWu;
	}
	
	/////////UserDefine End/////////////
	
}
