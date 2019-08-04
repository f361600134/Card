package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigCampReward {

	private int ID;//ID和购买次数
	private List<List<Integer>> CampReward;//随机奖励

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public List<List<Integer>> getCampReward(){
        return CampReward;
    }
    public void setCampReward(List<List<Integer>> CampReward){
        this.CampReward = CampReward;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> CampRewardTemps = Maps.newHashMap();
    	for(List<Integer> item : this.CampReward)
    	{
    		int c = CampRewardTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		CampRewardTemps.put(item.get(0), c);
    	}
    	CampRewardMap = CampRewardTemps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> CampRewardMap = Maps.newHashMap();
    public Map<Integer, Integer> getCampRewardMap(){
    	return CampRewardMap;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}