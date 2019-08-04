package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigActivityMonthCard {

	private int ID;//id
	private String name;//名字
	private List<List<Integer>> reward;//奖励物品
	private int duration;//持续时间(天)

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
	public List<List<Integer>> getReward(){
        return reward;
    }
    public void setReward(List<List<Integer>> reward){
        this.reward = reward;
    }
    
	public int getDuration(){
        return duration;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> rewardTemps = Maps.newHashMap();
    	for(List<Integer> item : this.reward)
    	{
    		int c = rewardTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		rewardTemps.put(item.get(0), c);
    	}
    	rewardMap = rewardTemps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> rewardMap = Maps.newHashMap();
    public Map<Integer, Integer> getRewardMap(){
    	return rewardMap;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}