package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigBuliding;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigBulidingMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigBuliding.json";
	
	private static Map<Integer, ConfigBuliding> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigBuliding> list = JSON.parseArray(content, ConfigBuliding.class);
		
		Map<Integer, ConfigBuliding> temps = Maps.newHashMap();
		for(ConfigBuliding config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigBuliding getConfig(int id) {
		ConfigBuliding data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigBuliding! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static void analyse(){
		Map<Integer, List<ConfigBuliding>> typeMapsTemp = Maps.newHashMap();
		Map<Integer, List<ConfigBuliding>> conditionMapsTemp = Maps.newHashMap();
		for(ConfigBuliding config : maps.values())
		{
			//type
			List<ConfigBuliding> builds = typeMapsTemp.get(config.getType());
			if(builds==null)
			{
				builds = Lists.newArrayList();
				typeMapsTemp.put(config.getType(), builds);
			}
			builds.add(config);
			
			//condition
			for(List<Integer> condition : config.getActiveCondition())
			{
				builds = conditionMapsTemp.get(condition.get(0));
				if(builds==null)
				{
					builds = Lists.newArrayList();
					conditionMapsTemp.put(condition.get(0), builds);
				}
				builds.add(config);
			}
		}
		typeMaps = typeMapsTemp;
		conditionMaps = conditionMapsTemp;
	}
	
	public static void complete(){
	}
	
	public static final int BuildingStateLock = 0;	//未解锁
	public static final int BuildingStateIdle = 1;	//待升级
	public static final int BuildingStateLeveling = 2;	//升级中
	
	public static final int CategoryMallHall = 1;	//主殿
	public static final int CategoryBarracks = 2;   //兵营
	public static final int CategoryForging = 3;    //锻造室
	public static final int CategoryCalledSolider = 4;     //点将台
	public static final int CategoryTournament = 5;        //比武场
	public static final int CategoryHorse = 6;             //马场
	public static final int CategoryMilitaryCamp = 7;      //军营
	public static final int CategoryMilitaryOffice = 8;    //军机处
	public static final int CategoryTombs = 9;             //神兵冢
	public static final int CategoryPub = 10;              //酒馆
	public static final int CategoryShop = 11;             //密保商店
	public static final int CategoryAltar = 12;            //祭坛
	public static final int CategoryFolkHouse = 13;        //民居
	public static final int CategoryMineField = 14;        //矿场
	public static final int CategoryTreasury = 15;         //金库
	
	public static final int NormalQueueNumber = 3;			//普通用户升级队列
	
	//type:List<ConfigBuliding>
	private static Map<Integer, List<ConfigBuliding>> typeMaps = Maps.newHashMap();
	//condition:List<ConfigBuliding>
	private static Map<Integer, List<ConfigBuliding>> conditionMaps = Maps.newHashMap();
	
	public static List<ConfigBuliding> getBuildingsByType(int type){
		return typeMaps.get(type);
	}
	
	public static ConfigBuliding getBuildingByType(int type){
		List<ConfigBuliding> buildings = typeMaps.get(type);
		if(buildings==null || buildings.isEmpty())
			return null;
		return buildings.get(0);
	}
	
	public static List<ConfigBuliding> getBuildingsByCondition(int condition){
		return conditionMaps.get(condition);
	}
	
	/**
	 * 模块是否已开启
	 * @param type
	 * @param level
	 * @param vip
	 * @param passedStages
	 * @return
	 */
	public static boolean isBuildingUnlock(int type, int level, int vip, Set<Integer> passedStages)
	{
		ConfigBuliding building = getBuildingByType(type);
		if(building==null)
			return false;
		return building.isLevelOK(level) && building.isStagePassOK(passedStages) && 
				building.isVipOK(vip);
	}
	
	/**
	 * 模块是否已开启
	 * @param type
	 * @param level
	 * @param vip
	 * @param passedStages
	 * @return
	 */
	public static boolean isBuildingUnlockById(int buildingId, int level, int vip, Set<Integer> passedStages)
	{
		ConfigBuliding building = getConfig(buildingId);
		if(building==null)
			return false;
		return building.isLevelOK(level) && building.isStagePassOK(passedStages) && 
				building.isVipOK(vip);
	}
	
	/**
	 * 获取升级队列数量
	 * @return  
	 * @return int  
	 * @date 2019年6月10日下午5:27:23
	 */
	public static int getQueueNumber(int playerId) {
//		IVipService vipService = SpringContextHolder.getInstance().getBean(VipService.class);
//		int vipCount = vipService.getLeftCount(playerId, ConfigVIPMgr.privilege_21);
//		return NormalQueueNumber + vipCount;
		return NormalQueueNumber;
	}
	
	/////////UserDefine End/////////////
	
}
