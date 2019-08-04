package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigPub;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigPubMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigPub.json";
	
	private static Map<Integer, ConfigPub> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigPub> list = JSON.parseArray(content, ConfigPub.class);
		
		Map<Integer, ConfigPub> temps = Maps.newHashMap();
		for(ConfigPub config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigPub getConfig(int id) {
		ConfigPub data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigPub! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static final int NormalPubId = 2;	//良将
	public static final int FamousPubId = 1;	//名将
	
	public static final int NormalHighQualityTotalCount = 10;	//良将抽卡高品质间隔总数
	public static final int FamousHighQualityTotalCount = 10;	//名将抽卡高品质间隔总数
	
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	/**
	 * 指定酒馆类型的能否免费
	 */
	public static boolean canFree(int pubId) {
		ConfigPub pub = maps.get(pubId);
		if(pub==null)
			return false;
		return pub.getFreeTime()>0;
	}
	
	/////////UserDefine End/////////////
	
}
