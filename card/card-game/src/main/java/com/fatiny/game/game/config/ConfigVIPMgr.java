package com.fatiny.game.game.config;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigVIP;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigVIPMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigVIP.json";
	
	private static Map<Integer, ConfigVIP> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigVIP> list = JSON.parseArray(content, ConfigVIP.class);
		
		Map<Integer, ConfigVIP> temps = Maps.newHashMap();
		for(ConfigVIP config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigVIP getConfig(int id) {
		ConfigVIP data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigVIP! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	public static int maxVip = 12;
	
	public static final int UNACTIVATED	= 0;// 未激活
	public static final int UNRECEIVED 	= 1;// 未领取
	public static final int RECEIVED	= 2;// 已领取
	
	public static final int privilege_1 = 1;//累计充值可享受该级特权次数
	/**每天可刷新神秘商店次数*/
	public static final int privilege_2 = 2;
	/**每天可购买体力次数*/
	public static final int privilege_3 = 3;
	/**每天可购买精英副本次数*/
	public static final int privilege_4 = 4;
	public static final int privilege_5 = 5;//每天可运送军资次数
	public static final int privilege_6 = 6;//每天可抢劫军资次数
	public static final int privilege_7 = 7;//每天使用元宝进行高级召唤次数
	/**每天使用元宝进行铜币祭祀次数*/
	public static final int privilege_8 = 8;
	/**每天使用元宝进行装备祭祀次数*/
	public static final int privilege_9 = 9;
	/**每天使用元宝进行宝石祭祀次数*/
	public static final int privilege_10 = 10;
	/**兵器冢每天可挑战次数*/
	public static final int privilege_11 = 11;
	/**无双试炼可额外重置*/
	public static final int privilege_12 = 12;
	/**每天铜币祭祀可免费进行*/
	public static final int privilege_13 = 13;
	/**每天星魂祭祀可免费进行*/
	public static final int privilege_14 = 14;
	/**每天宝石祭祀可免费进行*/
	public static final int privilege_15 = 15;
	/**每天装备祭祀可免费进行*/
	public static final int privilege_16 = 16;
	public static final int privilege_17 = 17;//金库每日征收赋税
	public static final int privilege_18 = 18;//每日可兑换宝石净化
	/**科技,建筑升级耗时减少*/
	public static final int privilege_19 = 19;
	public static final int privilege_20 = 20;//铜币祭祀最大可产生x倍暴击
	/**建筑队列上升到x个*/
	public static final int privilege_21 = 21;
	public static final int privilege_22 = 22;//运送军资初始品质上升
	/**神兵副本每天可购买x次*/
	public static final int privilege_23 = 23;
	public static final int privilege_24 = 24;//攻城拔寨每日收益加成
	public static final int privilege_25 = 25;//每天可购买寻龙秘鉴次数
	/**星魂祭祀每天可购买*/
	public static final int privilege_26 = 26;
	public static final int privilege_27 = 27;//可占卜次数
	public static final int privilege_28 = 28;//壤星试练每日可购买
	public static final int privilege_29 = 29;//每天可购买寻访次数
	public static final int privilege_30 = 30;//测卦次数每日可购买
	public static final int privilege_31 = 31;//每颗传承晶石的恢复时间增加
	public static final int privilege_32 = 32;//开启一键升星
	public static final int privilege_33 = 33;//开启一键祭祀
	
	public static Map<Integer, ConfigVIP> getMaps() {
		return maps;
	}
	
	public static void analyse(){
		maxVip = Collections.max(maps.keySet());
	}
	
	public static void complete(){
	}
	
	/////////UserDefine End/////////////
	
}
