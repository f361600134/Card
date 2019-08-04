package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigEquipment;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigEquipmentMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigEquipment.json";
	
	private static Map<Integer, ConfigEquipment> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigEquipment> list = JSON.parseArray(content, ConfigEquipment.class);
		
		Map<Integer, ConfigEquipment> temps = Maps.newHashMap();
		for(ConfigEquipment config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigEquipment getConfig(int id) {
		ConfigEquipment data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigEquipment! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static int MaxBody = 0;		//最大部位，6

	public static void analyse(){
		for(ConfigEquipment config : maps.values())
		{
			if(config.getSubType() > MaxBody)
				MaxBody = config.getSubType();
		}
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
