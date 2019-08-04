package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigContestReward;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigContestRewardMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath + "ConfigContestReward.json";

	private static Map<Integer, ConfigContestReward> maps = Maps.newHashMap();

	public static void load() {
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigContestReward> list = JSON.parseArray(content, ConfigContestReward.class);

		Map<Integer, ConfigContestReward> temps = Maps.newHashMap();
		for (ConfigContestReward config : list) {
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigContestReward getConfig(int id) {
		ConfigContestReward data = maps.get(id);
		if (data == null) {
			LOGGER.error("shit! ce hua config error in ConfigContestReward! fuck him! id={}", id);
		}
		return data;
	}

	public static boolean exist(int id) {
		return maps.containsKey(id);
	}

	///////// UserDefine Begin///////////

	public static final int Rank_Sky = 1; // 天榜类型
	public static final int Rank_Land = 2; // 地榜类型
	/**
	 * key: 榜位类型1:天榜, 2:地榜 value: TreeMap<ConfigContestReward>
	 */
	private static Map<Integer, TreeMap<Integer, ConfigContestReward>> rewardMap = Maps.newHashMap();

	public static void analyse() {
		Map<Integer, TreeMap<Integer, ConfigContestReward>> tempMap = Maps.newHashMap();
		TreeMap<Integer, ConfigContestReward> tempTreeMap = Maps.newTreeMap();
		for (ConfigContestReward reward : maps.values()) {
			tempTreeMap = tempMap.get(reward.getType());
			if (tempTreeMap == null) {
				tempTreeMap = Maps.newTreeMap();
				tempMap.put(reward.getType(), tempTreeMap);
			}
			tempTreeMap.put(reward.getRanking(), reward);
		}
		rewardMap = tempMap;
	}

	public static void complete() {
	}

	/**
	 * 获取奖励信息
	 * 
	 * @param type
	 * @return
	 * @return TreeMap<Integer,ConfigContestReward>
	 * @date 2019年4月3日上午10:13:12
	 */
	public static TreeMap<Integer, ConfigContestReward> getRewardMap(int type) {
		return rewardMap.get(type);
	}

	/**
	 * 获取奖励信息
	 * 
	 * @param type
	 * @return
	 * @return TreeMap<Integer,ConfigContestReward>
	 * @date 2019年4月3日上午10:13:12
	 */
	public static ConfigContestReward getConfigReward(int type, int ranking) {
		TreeMap<Integer, ConfigContestReward> treeMap = rewardMap.get(type);
		if (treeMap == null)
			return null;
		Entry<Integer, ConfigContestReward> reward = treeMap.ceilingEntry(ranking);
		if (reward == null) {
			reward = treeMap.floorEntry(ranking);// 保底获取
		}
		return reward.getValue();
	}

	public static TreeMap<Double, Integer> probabilityMap = Maps.newTreeMap();
	static {
		probabilityMap.put(0.03, 19);
		probabilityMap.put(0.05, 15);
		probabilityMap.put(0.1, 5);
		probabilityMap.put(0.2, 2);
		probabilityMap.put(0.3, 1);
		probabilityMap.put(1.0, 0);
	}
	/**
	 * 根据一定的公式, 计算快速挑战者的战斗结果
	 * 如果ackFc和defFc差距过大, 有极小的概率可能会赢
	 * @return
	 * @return int
	 * @date 2019年6月5日上午9:51:51
	 */
	public static int result(int ackFc, int defFc) {
		final double ackFcValue = Math.abs((double) ackFc);
		final double defFcValue = Math.abs((double) defFc);
		double proportion = Math.abs(ackFcValue - defFcValue) / ackFcValue;
		Double key = probabilityMap.ceilingKey(proportion);
		if (key == null) {
			key = probabilityMap.lastKey();
		}
		int probability = probabilityMap.get(key);
		int randomNum = (int) (Math.random() * 100);
		probability = ackFcValue > defFcValue ? (100-probability)  : probability;
		int result = randomNum <= probability ? 1 : 0;
//		System.out.println("ackFcValue:"+ackFcValue+",defFcValue:"+defFcValue+", proportion:"+proportion+",probability:"+probability+",randomNum:"+randomNum);
		return result;
	}
	
	///////// UserDefine End/////////////

}
