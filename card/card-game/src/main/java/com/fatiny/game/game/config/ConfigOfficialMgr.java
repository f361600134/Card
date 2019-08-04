package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigOfficial;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigOfficialMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigOfficial.json";
	
	private static Map<Integer, ConfigOfficial> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigOfficial> list = JSON.parseArray(content, ConfigOfficial.class);
		
		Map<Integer, ConfigOfficial> temps = Maps.newHashMap();
		for(ConfigOfficial config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigOfficial getConfig(int id) {
		ConfigOfficial data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigOfficial! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	/** 玩家初始官位品阶 */
	public static int initialOfficialId = 0;
	
	/**发送奖励的时间*/
	public static final int REWARD_TIME = 21;
	
	/** 默认刷新挑战对手数量 */
	public static final int FIGHTER_NUMBER = 4;
	
	/** 战报数量*/
	public static final int REPORT_NUMBER = 20;
	public static final int REPORT_ACK = 1; //战报类型, 攻
	public static final int REPORT_DEF = 2; //战报类型, 守
	
	//默认皇城比武次数
	public static int fightCount = 6;
	
	public static void analyse(){
		for (ConfigOfficial config : maps.values()) {
			initialOfficialId = Math.max(initialOfficialId, config.getID());
		}
	}
	
	public static void complete(){
		fightCount = ConfigFunctionMgr.getLimitCount(ConfigFunctionMgr.CategoryOfficial, ConfigFunctionMgr.LimitFreeCount);
	}
	
	/////////UserDefine End/////////////
	
}
