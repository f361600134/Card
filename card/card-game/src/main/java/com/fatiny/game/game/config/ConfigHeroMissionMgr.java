package com.fatiny.game.game.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigHeroMission;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

@AnnotationConfig
public class ConfigHeroMissionMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigHeroMission.json";
	
	private static Map<Integer, ConfigHeroMission> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigHeroMission> list = JSON.parseArray(content, ConfigHeroMission.class);
		
		Map<Integer, ConfigHeroMission> temps = Maps.newHashMap();
		for(ConfigHeroMission config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigHeroMission getConfig(int id) {
		ConfigHeroMission data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigHeroMission! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	//所有任务状态:0=未完成;1=已完成,未激活;2=已完成, 已激活;
	public static final short State_NoComplete = 0;
	public static final short State_Complete = 1;
	public static final short State_Activite = 2;
	
	public static final int God_Activite_StarLvl = 4;//蓝色1星激活任务
	public static final int God_Finish_StarLvl = 6;//蓝色3星激活任务
	
	//按完成类型分类的任务
	private static Multimap<Integer, Integer> typeCompleteMissions;	//completeType:mission ids
	
	public static final int CompleteType_WusCopyPass = 2;//再战无双
	public static final int CompleteType_SoulCopyPass = 3;//将魂英杰
	public static final int CompleteType_Contest = 4;	//擂台争锋
	public static final int CompleteType_Official = 6;	//皇城
	public static final int CompleteType_GemCopyPass = 7;//通关宝石副本
	public static final int CompleteType_ArmCopyPass = 8;//通关兵器冢
	public static final int CompleteType_StagePass = 9;	//通关精英副本
	public static final int CompleteType_Talent = 10;	//培养天赋
	public static final int CompleteType_ArtifactCreate= 11;//打造神兵

	
	public static void analyse(){
		Multimap<Integer, Integer> typeCompleteMissionsTemp = ArrayListMultimap.create();
		for (ConfigHeroMission mission : maps.values()) {
			typeCompleteMissionsTemp.put(mission.getCompleteType(), mission.getID());
		}
		typeCompleteMissions = typeCompleteMissionsTemp; 
	}
	
	public static void complete(){
	}
	
	/**
	 * 获取指定完成类型的任务列表
	 */
	public static Collection<Integer> getTypeCompleteMissions(int type) {
		return typeCompleteMissions.get(type);
	}
	
	/////////UserDefine End/////////////
	
}
