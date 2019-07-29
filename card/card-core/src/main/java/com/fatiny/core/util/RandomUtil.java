package com.fatiny.core.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

	public static Random getRandom(){
		return ThreadLocalRandom.current();
	}

	/**
	 * 
	 * @param middle
	 *            中间值
	 * @param min
	 *            最小值，可包含
	 * @param max
	 *            最大值，可包含
	 * @param period
	 *            正态跨度
	 * @return
	 */
	public static int gaussianRandom(int middle, int min, int max, int period) {

		double g = getRandom().nextGaussian();

		int res = middle;
		res = (int) Math.round(middle + g * period);

		if (res < min) {
			res = min;
		} else if (res > max) {
			res = max;
		}
		return res;
	}
	
	/**
	 * 随机范围值
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomInt(int min,int max){
		int abs = Math.abs(max - min) + 1;
		return (min + getRandom().nextInt(abs));
	}
	
	public static void main(String[] args) {
		
		int res;
		
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		
		for (int i = 50; i < 76; i++) {
			map.put(i, 0);
		}
		
		for (int i = 0; i < 1000000; i++) {
			res=RandomUtil.gaussianRandom(66, 50, 75, 5);
			
			map.put(res, map.get(res)+1);
			
		}
		
		
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey()+" "+(entry.getValue()/10000f)+"%");
		}
	}
	
}
