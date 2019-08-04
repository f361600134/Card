package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigArtifactInfo;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigArtifactInfoMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigArtifactInfo.json";
	
	private static Map<Integer, ConfigArtifactInfo> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigArtifactInfo> list = JSON.parseArray(content, ConfigArtifactInfo.class);
		
		Map<Integer, ConfigArtifactInfo> temps = Maps.newHashMap();
		for(ConfigArtifactInfo config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigArtifactInfo getConfig(int id) {
		ConfigArtifactInfo data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigArtifactInfo! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static final short State_NoActivite = 1;//已打造,未激活
	public static final short State_CanUpgrade = 2;//可精炼
	public static final short State_CanBreak = 3;	//可突破
	
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
