package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigActivityStageClear {

	private int ID;//id
	private String name;//名字
	private List<List<Integer>> condition;//条件
	private List<List<Integer>> reward;//奖励物品

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
    
	public List<List<Integer>> getCondition(){
        return condition;
    }
    public void setCondition(List<List<Integer>> condition){
        this.condition = condition;
    }
    
	public List<List<Integer>> getReward(){
        return reward;
    }
    public void setReward(List<List<Integer>> reward){
        this.reward = reward;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> conditionTemps = Maps.newHashMap();
    	for(List<Integer> item : this.condition)
    	{
    		int c = conditionTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		conditionTemps.put(item.get(0), c);
    	}
    	conditionMap = conditionTemps;
			    
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
    private Map<Integer, Integer> conditionMap = Maps.newHashMap();
    public Map<Integer, Integer> getConditionMap(){
    	return conditionMap;
    }
    
    private Map<Integer, Integer> rewardMap = Maps.newHashMap();
    public Map<Integer, Integer> getRewardMap(){
    	return rewardMap;
    }
    
	
	/////////UserDefine Begin///////////
    private int type;

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public int getConditionValue() {
		return conditionMap.getOrDefault(getType(), 0);
	}
	
    
	/////////UserDefine End/////////////
	
}