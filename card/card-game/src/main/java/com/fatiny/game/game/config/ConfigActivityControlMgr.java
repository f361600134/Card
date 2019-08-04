package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigActivityControl;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigActivityControlMgr {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);
	
	private static String filename = Config.JsonConfigPath+"ConfigActivityControl.json";
	
	private static Map<Integer, ConfigActivityControl> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigActivityControl> list = JSON.parseArray(content, ConfigActivityControl.class);
		
		Map<Integer, ConfigActivityControl> temps = Maps.newHashMap();
		for(ConfigActivityControl config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigActivityControl getConfig(int id) {
		ConfigActivityControl data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigActivityControl! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static Map<Integer, ConfigActivityControl> getMaps(){
		return maps;
	}
	
	public static void analyse(){
		for (ConfigActivityControl control : maps.values()) {
			control.parseAfter();
		}
	}
	
	public static void complete(){
	}
	
	/**
	 * 判断活动是否开启
	 * @param activityId
	 * @return  
	 * @return boolean  
	 * @date 2019年4月18日下午2:52:40
	 */
	public static boolean checkOpen(int activityId) {
		ConfigActivityControl activityControl = getConfig(activityId);
		if (activityControl == null) {
			return false;
		}
		return activityControl.checkOpen();
	}
	
	/////////UserDefine End/////////////
	
}
