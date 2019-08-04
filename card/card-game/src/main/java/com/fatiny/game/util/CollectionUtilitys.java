package com.fatiny.game.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

public class CollectionUtilitys {

	/**
	 * 解析形如List<List<key,value>>成Map<Key,Value>
	 */
	public static Map<Integer, Integer> parseListToMap(List<List<Integer>> list)
	{
		Map<Integer, Integer> map = Maps.newHashMap();
		parseListToMap(list, map);
		return map;
	}
	
	/**
	 * 解析形如List<List<key,value>>成Map<Key,Value>
	 */
	public static void parseListToMap(List<List<Integer>> list, Map<Integer, Integer> map)
	{
		if(list==null)
			return;
		for(List<Integer> items : list)
		{
			if(items.size()!=2)
				continue;
			int v = map.getOrDefault(items.get(0), 0);
			v += items.get(1);
			map.put(items.get(0), v);
		}
	}
	
	/**
	 * 将集合map1和map2叠加到map
	 */
	public static Map<Integer, Integer> mergeMap(Map<Integer, Integer> map1, Map<Integer, Integer> map2)
	{
		Map<Integer, Integer> map = Maps.newHashMap();
		mergeMap(map1, map2, map);
		return map;
	}
	
	/**
	 * 将集合map1和map2叠加到map
	 */
	public static void mergeMap(Map<Integer, Integer> map1, Map<Integer, Integer> map2, Map<Integer, Integer> map)
	{
		if(map1!=null)
			map.putAll(map1);
		mergeToMap(map2, map);
	}
	
	/**
	 * 将集合map1叠加到map
	 */
	public static void mergeToMap(Map<Integer, Integer> map1, Map<Integer, Integer> map)
	{
		if(map1==null)
			return;
		for(Entry<Integer, Integer> entry : map1.entrySet())
		{
			int v = map.getOrDefault(entry.getKey(), 0);
			v += entry.getValue();
			map.put(entry.getKey(), v);
		}
	}
}
