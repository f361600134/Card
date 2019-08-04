package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigEmail;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigEmailMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath + "ConfigEmail.json";

	private static Map<Integer, ConfigEmail> maps = Maps.newHashMap();

	public static void load() {
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigEmail> list = JSON.parseArray(content, ConfigEmail.class);

		Map<Integer, ConfigEmail> temps = Maps.newHashMap();
		for (ConfigEmail config : list) {
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigEmail getConfig(int id) {
		ConfigEmail data = maps.get(id);
		if (data == null) {
			LOGGER.error("shit! ce hua config error in ConfigEmail! fuck him! id={}", id);
		}
		return data;
	}

	public static boolean exist(int id) {
		return maps.containsKey(id);
	}

	///////// UserDefine Begin///////////

	public static final int EMAIL_OFFCIAL = 1; 			//皇城官位邮件配置
	public static final int EMAIL_CONTEST_PRELIM = 2;	//擂台赛预赛
	public static final int EMAIL_CONTEST_FINAL = 3;	//擂台赛决赛
//	public static final int EMAIL_OFFCIAL = 4;
//	public static final int EMAIL_OFFCIAL = 5;
//	public static final int EMAIL_OFFCIAL = 6;
	public static final int EMAIL_GROUPBUY = 7;			//团购福利

	public static void analyse() {
	}

	public static void complete() {
	}

	///////// UserDefine End/////////////

}
