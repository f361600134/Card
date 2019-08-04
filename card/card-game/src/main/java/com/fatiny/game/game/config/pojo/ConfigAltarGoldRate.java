package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.common.collect.Maps;

public class ConfigAltarGoldRate {

	private int ID;//VIP等级
	private List<List<Integer>> goldRate;//权重

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public List<List<Integer>> getGoldRate(){
        return goldRate;
    }
    public void setGoldRate(List<List<Integer>> goldRate){
        this.goldRate = goldRate;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		TreeMap<Integer, Integer> goldRateTemps = Maps.newTreeMap();
    	int goldRateTempRate = 0;
    	for(List<Integer> item : this.goldRate)
    	{
    		goldRateTempRate += item.get(1);
    		goldRateTemps.put(goldRateTempRate, item.get(0));
    	}
    	goldRateMax = goldRateTempRate;
    	goldRateTrees = goldRateTemps;
			    
		
    }
	
	//id_rate 权重随机
	private int goldRateMax;
    private TreeMap<Integer, Integer> goldRateTrees = Maps.newTreeMap();
    public int getGoldRateByRate(){
    	int rate = (int)(Math.random()*goldRateMax)+1;
    	Entry<Integer, Integer> entry = goldRateTrees.ceilingEntry(rate);
    	if(entry==null)
    		return 0;
    	return entry.getValue();
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}