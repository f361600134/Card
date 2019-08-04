package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigFunction;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigFunctionMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigFunction.json";
	
	private static Map<Integer, ConfigFunction> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigFunction> list = JSON.parseArray(content, ConfigFunction.class);
		
		Map<Integer, ConfigFunction> temps = Maps.newHashMap();
		for(ConfigFunction config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigFunction getConfig(int id) {
		ConfigFunction data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigFunction! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static final int CategoryOfficial = 1000;		//皇城比武
	public static final int CategoryContest = 1100;			//擂台赛
	
	public static final int CategoryShopMilitary = 1301;	//军功
	public static final int CategoryShopBattle = 1302;		//战功
	public static final int CategoryShopJade = 1303;		//玉璧
	
	public static final int CategoryArtifactStage = 2500;	//神兵副本
	public static final int CategoryShopTreasure = 2800;	//秘宝商店
	
	public static final int CategoryAltar = 2900;			//祭坛
	public static final int CategoryAltarGem = 2901;		//宝石祭祀
	public static final int CategoryAltarEquip = 2902;		//装备祭祀
	public static final int CategoryAltarGold = 2903;		//铜币祭祀
	public static final int CategoryAltarGhost = 2904;		//星魂祭祀
	
	public static final int CategoryMission = 3500;			//任务
	
	public static final int CategoryGemCopy = 3601;			//宝石副本
	public static final int CategoryArmCopy = 3602;			//兵器冢
	public static final int CategorySoulStage = 3603;		//将魂试炼
	public static final int CategoryWusStage = 3604;		//无双试炼
//	public static final int CategoryShopJade = 3605;		//运送军资
//	public static final int CategoryShopJade = 3606;		//斩将夺宝
	public static final int CategoryCityStage = 3607;		//攻城拔寨

	public static final int CategoryStage = 3800;			//副本
	public static final int CategoryCheckIn = 3900;			//签到
	
	public static final int LimitFreeCount = 1;				//每日免费次数
	public static final int LimitRefreshCount = 2;			//每日免费刷新次数
	public static final int LimitTransportCount = 3;		//运送次数
	public static final int LimitRobCount = 4;				//掠夺次数
	public static final int LimitSta = 5;					//耐力值
	public static final int LimitDivineCount = 6;			//占卜次数
	public static final int LimitBuyCount = 7;				//购买次数
	
	//condition:List<ConfigBuliding>
	private static Map<Integer, List<ConfigFunction>> conditionMaps = Maps.newHashMap();
	
	public static void analyse(){
		Map<Integer, List<ConfigFunction>> conditionMapsTemp = Maps.newHashMap();
		for(ConfigFunction config : maps.values())
		{
			//condition
			for(List<Integer> condition : config.getActiveCondition())
			{
				List<ConfigFunction> builds = conditionMapsTemp.get(condition.get(0));
				if(builds==null)
				{
					builds = Lists.newArrayList();
					conditionMapsTemp.put(condition.get(0), builds);
				}
				builds.add(config);
			}
		}
		conditionMaps = conditionMapsTemp;
	}
	
	public static void complete(){
	}
	
	
	/**
	 * 功能是否已开启
	 * @param type
	 * @param level
	 * @param vip
	 * @param passedStages
	 * @return
	 */
	public static boolean isFunctionUnlock(int functionId, int level, int vip, Set<Integer> passedStages)
	{
		ConfigFunction func = getConfig(functionId);
		if(func==null)
			return false;
		return func.isLevelOK(level) && func.isStagePassOK(passedStages) && 
				func.isVipOK(vip);
	}
	
	public static List<ConfigFunction> getFunctionsByCondition(int condition){
		return conditionMaps.get(condition);
	}
	
	/**
	 * 获取某个模块的限制次数
	 * @param functionId
	 * @param limitType
	 * @return
	 */
	public static int getLimitCount(int functionId, int limitType) {
		ConfigFunction func = getConfig(functionId);
		if(func==null)
			return 0;
		return func.getLimitsMap().getOrDefault(limitType, 0);
	}
	
	/////////UserDefine End/////////////
	
}
