package com.fatiny.game.game.config.pojo.ext;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class GoodUtility {

	/**
	 * 解析成configId:Count的键值对
	 */
	public static Map<Integer, Integer> parseGoodCount(List<GoodCountData> goods){
		Map<Integer, Integer> datas = Maps.newHashMap();
		parseGoodCount(goods, datas);
		return datas;
	}
	
	/**
	 * 解析成configId:Count的键值对,保存到datas
	 */
	public static void parseGoodCount(List<GoodCountData> goods, Map<Integer, Integer> datas){
		for(GoodCountData good : goods)
		{
			int count = datas.getOrDefault(good.getId(), 0);
			count += good.getCount();
			datas.put(good.getId(), count);
		}
	}
	
}
