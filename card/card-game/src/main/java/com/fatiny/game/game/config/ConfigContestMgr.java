package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigContest;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigContestMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigContest.json";
	
	private static Map<Integer, ConfigContest> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigContest> list = JSON.parseArray(content, ConfigContest.class);
		
		Map<Integer, ConfigContest> temps = Maps.newHashMap();
		for(ConfigContest config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigContest getConfig(int id) {
		ConfigContest data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigContest! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static int DailyCount = 6;			//每日挑战次数
	public static final int REFRESH_COST = 5000;//刷新消耗金币
	public static final int FIGHTS_NUMBER = 5; 	//挑战对手数量
	public static final int SKY_LIMIT = 32; 	//天榜人数, 每组32名
	public static final int Merit_Reward= 100; 	//军功奖励
	public static final int Initial_Score= 1000; //初始积分
	
	public static int DailyBuyCount = 10;//每日购买次数
	
	public static void analyse(){
	}
	
	public static void complete(){
		DailyCount = ConfigFunctionMgr.getLimitCount(ConfigFunctionMgr.CategoryContest, ConfigFunctionMgr.LimitFreeCount);
	}
	
	/////////UserDefine End/////////////
	
}
