package com.fatiny.game.game.config;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigRobot;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

@AnnotationConfig
public class ConfigRobotMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath + "ConfigRobot.json";

	private static Map<Integer, ConfigRobot> maps = Maps.newHashMap();

	public static void load() {
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigRobot> list = JSON.parseArray(content, ConfigRobot.class);

		Map<Integer, ConfigRobot> temps = Maps.newHashMap();
		for (ConfigRobot config : list) {
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigRobot getConfig(int id) {
		ConfigRobot data = maps.get(id);
		if (data == null) {
			LOGGER.error("shit! ce hua config error in ConfigRobot! fuck him! id={}", id);
		}
		return data;
	}

	public static boolean exist(int id) {
		return maps.containsKey(id);
	}

	///////// UserDefine Begin///////////
	/**
	 * 机器人需配置官位, 但不在程序中做计算
	 * key: 官位id
	 * value: 玩家ids
	 */
	private static Multimap<Integer, Integer> typePlayerIdMap;
	
	private static int floorOfficialId = 21; //最小的官职
	
	public static void analyse() {
		Multimap<Integer, Integer> temp = ArrayListMultimap.create();
		Multimap<Integer, ConfigRobot> tempRobot = ArrayListMultimap.create();
		for (ConfigRobot robot : maps.values()) {
			temp.put(robot.getOfficialId(), robot.getID());
			tempRobot.put(robot.getCamp(), robot);
			floorOfficialId = Math.max(floorOfficialId, robot.getOfficialId());
		}
		typePlayerIdMap = temp;
		typeConfigRobotMap = tempRobot;
	}

	public static void complete() {
	}
	
	/**
	 * 通过官位id随机获取到指定数量的玩家ids.
	 * 不能返回空机器人给前端, 如果获取不到指定官位的机器人, 则给其他官位. 必须要满足返回条件
	 * @return  
	 * @return int  
	 * @date 2019年3月26日上午11:34:41
	 */
	public static List<Integer> getIdbyOfficialId(int officialId, int number) {
		List<Integer> result = Lists.newArrayList();
		List<Integer> playerIds = (List<Integer>)typePlayerIdMap.get(officialId);
		if (CollectionUtils.isEmpty(playerIds)) {
			LOGGER.error("Can not found rebot info when getIdbyOfficialId, officialId:{}, number:{}", officialId, number);
			return getIdbyOfficialId(number);
		}
		if (playerIds.size() < number) {
			LOGGER.error("It's not enough to get data, playerId:{}, number:{}", officialId, number);
			return getIdbyOfficialId(number);
		}
		Collections.shuffle(playerIds);
		result = playerIds.subList(0, number);
		return result;
	}
	
	/**
	 * 保底获取, 从官位21里面随机获取
	 * @param number
	 * @return  
	 * @return List<Integer>  
	 * @date 2019年3月26日上午11:55:41
	 */
	public static List<Integer> getIdbyOfficialId(int number) {
		List<Integer> playerIds = (List<Integer>)typePlayerIdMap.get(floorOfficialId);
		Collections.shuffle(playerIds);
		return playerIds.subList(0, number);
	}
	
	
	/**
	 * 机器人需配置官位, 但不在程序中做计算
	 * key: 国家id
	 * value: 玩家ids
	 */
	private static Multimap<Integer, ConfigRobot> typeConfigRobotMap;
	
	public static List<ConfigRobot> getRobotBycampId(int campId){
		return Lists.newArrayList(typeConfigRobotMap.get(campId));
	}
	
	
	///////// UserDefine End/////////////

}
