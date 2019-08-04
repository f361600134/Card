package com.fatiny.game.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SourceNature {
	
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	/*
	 * 道具相关 日志格式:
	 * logType,日期,角色id,角色名,账号,输入账号,物品ID,物品名称,产出/消耗,行为,变化数量,变化前数量,变化后数量
	 */
	public final static int obtainItem = 1001; // 道具增加
	public final static int consumeItem = 1002; // 道具消耗
	public final static int obtainDiamond = 1003; // 元宝增加
	public final static int consumeDiamond = 1004; // 元宝减少
	public final static int obtainGold = 1005; // 铜币增加
	public final static int consumeGold = 1006; // 铜币减少
	public final static int obtainMimeral = 1007; // 矿石增加
	public final static int consumeMimeral = 1008; // 矿石减少
	public final static int obtainPubFamous = 1009; // 魂玉增加
	public final static int consumePubFamous = 1010; // 魂玉减少
	public final static int obtainPower = 1011; // 体力增加
	public final static int consumePower = 1012; // 体力减少
	public final static int obtainShopMilitary = 1013; // 军功增加
	public final static int consumeShopMilitary = 1014; // 军功减少
	public final static int obtainStarGhost = 1015; // 星魂增加
	public final static int consumeStarGhost = 1016; // 星魂减少
	public final static int obtainShopBattle = 1017; // 战功增加
	public final static int consumeShopBattle = 1018; // 战功减少
	public final static int obtainShopJade = 1019; // 玉璧增加
	public final static int consumeShopJade = 1020; // 玉璧减少
	public final static int obtainPubNormal = 1021; // 招募令增加
	public final static int consumePubNormal = 1022; // 招募令减少
	public final static int obtainHorseGhost = 1023; // 马魂增加
	public final static int consumeHorseGhost = 1024; // 马魂减少
	public final static int obtainShopTreasure = 1025; // 将魂增加
	public final static int consumeShopTreasure = 1026; // 将魂减少
	public final static int obtainShopRefresh = 1027; // 商店刷新次数增加
	public final static int consumeShopRefresh = 1028; // 商店刷新次数减少
	public final static int obtainFamilyDevote = 1029; // 家族贡献增加
	public final static int consumeFamilyDevote = 1030; // 家族贡献减少
	public final static int obtainExp = 1031; 			// 经验增加

//	/**
//	 * 列表转成日志对应格式
//	 * @param list
//	 * @return  
//	 * @return String 格式:名字1(configId)*数量1|名字2(configId)*数量2
//	 * @date 2019年6月28日下午9:06:10
//	 */
//	public static String itemListToStr(List<Integer> list) {
//		try {
//			StringBuilder builder = new StringBuilder();
//			for (Integer itemId : list) {
//				builder.append(ConfigGoodMgr.getItemName(itemId));// 拼接名字
//				builder.append("(").append(itemId).append(")"); // 配置id
//				builder.append("*").append(1);// 数量
//			}
//			return builder.toString();
//		} catch (Exception e) {
//			logger.error("itemToStr error", e);
//			return "";
//		}
//	}
//	

//	/**
//	 * 奖励转换成字符串用于显示
//	 * @return
//	 * @return String 格式:名字1(configId)*数量1|名字2(configId)*数量2
//	 * @date 2019年6月28日下午12:52:22
//	 */
//	public static String itemToStr(Integer itemId, Integer count) {
//		try {
//			StringBuilder builder = new StringBuilder();
//			builder.append(ConfigGoodMgr.getItemName(itemId));// 拼接名字
//			builder.append("(").append(itemId).append(")"); // 配置id
//			builder.append("*").append(count);// 数量
//			return builder.toString();
//		} catch (Exception e) {
//			logger.error("itemToStr error", e);
//			return "";
//		}
//	}

//	/**
//	 * map转string日志格式
//	 * @return
//	 * @return String 格式:名字1(configId)*数量1|名字2(configId)*数量2
//	 * @date 2019年6月28日下午12:52:22
//	 */
//	public static String itemMapToStr(Map<Integer, Integer> rewardMap) {
//		try {
//			StringBuilder builder = new StringBuilder();
//			for (Integer key : rewardMap.keySet()) {
//				builder.append(ConfigGoodMgr.getItemName(key));// 拼接名字
//				builder.append("(").append(key).append(")"); // 配置id
//				builder.append("*").append(rewardMap.get(key));// 数量
//				builder.append("|");
//			}
//			if (builder.length() > 1) {
//				builder.deleteCharAt(builder.length() - 1);
//			}
//			return builder.toString();
//		} catch (Exception e) {
//			logger.error("itemMapToStr error, rewardMap:{}", e,rewardMap);
//			return "";
//		}
//	}
}
