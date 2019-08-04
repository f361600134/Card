package com.fatiny.game.game.config;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fatiny.game.base.base.AnnotationConfig;
import com.fatiny.game.game.config.pojo.ConfigNickname;
import com.fatiny.game.game.module.common.constant.Config;
import com.fatiny.game.util.FileUtilitys;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigNicknameMgr {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigActivityControlMgr.class);

	private static String filename = Config.JsonConfigPath+"ConfigNickname.json";
	
	private static Map<Integer, ConfigNickname> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		nicknameList = JSON.parseArray(content, ConfigNickname.class);
	}

	public static ConfigNickname getConfig(int id) {
		ConfigNickname data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigNickname! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	private static List<ConfigNickname> nicknameList = Lists.newArrayList();
	
	public static void analyse(){
//		String content = FileUtilitys.ReadFile(filename);
//		nicknameList = JSON.parseArray(content, ConfigNickname.class);
	}
	
	public static void complete(){
	}
	
	public static void getUnusedNicknames(Set<String> usedNames, List<String> unusedNames)
	{
		for(int i=0; i<nicknameList.size(); i++)
		{
			ConfigNickname prefix = nicknameList.get(i);
			String newName = prefix.getPrefixName().concat(prefix.getSuffixName());
			if(!usedNames.contains(prefix.getSuffixName()))
				unusedNames.add(prefix.getSuffixName());
			if(!prefix.getPrefixName().trim().isEmpty() && !usedNames.contains(newName))
				unusedNames.add(newName);
			if(prefix.getPrefixName().trim().isEmpty())
				continue;
			for(int j=i+1; j<nicknameList.size(); j++){
				ConfigNickname suffix = nicknameList.get(j);
				if(suffix.getSuffixName().trim().isEmpty())
					continue;
				String tempName = prefix.getPrefixName().concat(suffix.getSuffixName());
				if(!usedNames.contains(tempName))
					unusedNames.add(tempName);
			}
		}
		nicknameList = null; //let gc do its work.
	}
	
	/////////UserDefine End/////////////
	
}
