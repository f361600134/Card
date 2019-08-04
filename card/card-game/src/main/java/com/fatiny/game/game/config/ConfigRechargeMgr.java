package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigRecharge;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigRechargeMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigRecharge.json";
	
	private static Map<Integer, ConfigRecharge> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigRecharge> list = JSON.parseArray(content, ConfigRecharge.class);
		
		Map<Integer, ConfigRecharge> temps = Maps.newHashMap();
		for(ConfigRecharge config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigRecharge getConfig(int id) {
		ConfigRecharge data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigRecharge! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static int RECHARGE = 1; // 普通充值
	public static int MONTHCARD = 2; //月卡
	public static int SUPREMECARD  = 3; //至尊卡,永久
	
	public static Map<Integer, ConfigRecharge> getMaps(){
		return maps;
	}
	
	
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
