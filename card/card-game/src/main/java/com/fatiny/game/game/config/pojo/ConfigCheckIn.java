package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigCheckIn {

	private int ID;//天数
	private int type;//类型
	private List<List<Integer>> reward;//每日签到奖励
	private int vipDouble;//VIP等级双倍
	private List<List<Integer>> addUpReward;//连续签到奖励

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    
	public List<List<Integer>> getReward(){
        return reward;
    }
    public void setReward(List<List<Integer>> reward){
        this.reward = reward;
    }
    
	public int getVipDouble(){
        return vipDouble;
    }
    public void setVipDouble(int vipDouble){
        this.vipDouble = vipDouble;
    }
    
	public List<List<Integer>> getAddUpReward(){
        return addUpReward;
    }
    public void setAddUpReward(List<List<Integer>> addUpReward){
        this.addUpReward = addUpReward;
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
			    
		Map<Integer, Integer> addUpRewardTemps = Maps.newHashMap();
    	for(List<Integer> item : this.addUpReward)
    	{
    		int c = addUpRewardTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		addUpRewardTemps.put(item.get(0), c);
    	}
    	addUpRewardMap = addUpRewardTemps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> rewardMap = Maps.newHashMap();
    public Map<Integer, Integer> getRewardMap(){
    	return rewardMap;
    }
    
    private Map<Integer, Integer> addUpRewardMap = Maps.newHashMap();
    public Map<Integer, Integer> getAddUpRewardMap(){
    	return addUpRewardMap;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}