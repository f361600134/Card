package com.fatiny.game.game.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigArtifactSkill;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.CollectionUtilitys;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigArtifactSkillMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigArtifactSkill.json";
	
	private static Map<Integer, ConfigArtifactSkill> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigArtifactSkill> list = JSON.parseArray(content, ConfigArtifactSkill.class);
		
		Map<Integer, ConfigArtifactSkill> temps = Maps.newHashMap();
		for(ConfigArtifactSkill config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigArtifactSkill getConfig(int id) {
		ConfigArtifactSkill data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigArtifactSkill! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	/**
	 * key: configId, artifactConfigId * 100 + breaklevel
	 */
	public static void analyse(){
		for (ConfigArtifactSkill config : maps.values()) {
			int breakLvl = config.getRank();
			Map<Integer, Integer> attrTotalValues = Maps.newHashMap();
			for(int i=1; i<=breakLvl; i++)
			{
				int preId = config.getBelong() * 100 + i;
				ConfigArtifactSkill pre = maps.get(preId);
				CollectionUtilitys.mergeToMap(pre.getAttrValuesMap(), attrTotalValues);
			}
			config.setAttrTotalValues(attrTotalValues);
		}
	}
	
	public static void complete(){
	}
	
	/**
	 * 通过配置id和神兵等级获取到所有的技能
	 * @param artifactConfigId
	 * @param artifactLevel
	 * @return
	 */
	public static List<Integer> getAllSkills(int artifactConfigId, int breakLevel) {
		List<Integer> skills = new ArrayList<>();
		for (int i = 1; i <= breakLevel; i++) {
			int configId = artifactConfigId * 100 + i;
			ConfigArtifactSkill config = maps.get(configId);
			skills.addAll(config.getInitialSkillId());
		}
		return skills;
	}
	
	/**
	 * 通过配置id和神兵等级获取到神兵配置
	 * @param artifactConfigId
	 * @param artifactLevel
	 * @return
	 */
	public static ConfigArtifactSkill getArtifactSkill(int artifactConfigId, int artifactLevel) {
		int key = artifactConfigId * 100 + artifactLevel;
		ConfigArtifactSkill artifactSkill = maps.get(key);
		if (artifactSkill == null) {
			LOGGER.error("shit! ce hua config error in ConfigArtifactLevel! fuck him! id={}", key);
		}
		return artifactSkill;
	}
	
	/////////UserDefine End/////////////
	
}
