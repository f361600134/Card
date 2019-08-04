package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.fatiny.game.util.CollectionUtilitys;
import com.google.common.collect.Maps;

public class ConfigWuSStage {

	private int ID;//关卡id
	private int boss;//bossid
	private List<Integer> friends;//援军
	private int time;//时长
	private List<List<Integer>> firstReward;//首次通关奖励
	private List<List<Integer>> reward;//通关奖励
	private List<List<Integer>> dailyFirstReward;//每日首次通关奖励
	private int isShow;//是否显示

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getBoss(){
        return boss;
    }
    public void setBoss(int boss){
        this.boss = boss;
    }
    
	public List<Integer> getFriends(){
        return friends;
    }
    public void setFriends(List<Integer> friends){
        this.friends = friends;
    }
    
	public int getTime(){
        return time;
    }
    public void setTime(int time){
        this.time = time;
    }
    
	public List<List<Integer>> getFirstReward(){
        return firstReward;
    }
    public void setFirstReward(List<List<Integer>> firstReward){
        this.firstReward = firstReward;
    }
    
	public List<List<Integer>> getReward(){
        return reward;
    }
    public void setReward(List<List<Integer>> reward){
        this.reward = reward;
    }
    
	public List<List<Integer>> getDailyFirstReward(){
        return dailyFirstReward;
    }
    public void setDailyFirstReward(List<List<Integer>> dailyFirstReward){
        this.dailyFirstReward = dailyFirstReward;
    }
    
	public int getIsShow(){
        return isShow;
    }
    public void setIsShow(int isShow){
        this.isShow = isShow;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		Map<Integer, Integer> firstRewardTemps = Maps.newHashMap();
    	for(List<Integer> item : this.firstReward)
    	{
    		int c = firstRewardTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		firstRewardTemps.put(item.get(0), c);
    	}
    	firstRewardMap = firstRewardTemps;
			    
		Map<Integer, Integer> rewardTemps = Maps.newHashMap();
    	for(List<Integer> item : this.reward)
    	{
    		int c = rewardTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		rewardTemps.put(item.get(0), c);
    	}
    	rewardMap = rewardTemps;
			    
		Map<Integer, Integer> dailyFirstRewardTemps = Maps.newHashMap();
    	for(List<Integer> item : this.dailyFirstReward)
    	{
    		int c = dailyFirstRewardTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		dailyFirstRewardTemps.put(item.get(0), c);
    	}
    	dailyFirstRewardMap = dailyFirstRewardTemps;
    	
    	//组装奖励, 首次通关奖励 = 首通奖励 + 普通奖励 + 每次首通奖励
    	CollectionUtilitys.mergeToMap(rewardTemps, firstRewardMap);
    	CollectionUtilitys.mergeToMap(dailyFirstRewardTemps, firstRewardMap);
    	
    	//组装奖励, 每次首通奖励 = 普通奖励 + 每次首通奖励
    	CollectionUtilitys.mergeToMap(rewardTemps, dailyFirstRewardMap);
    }
	
	//id_count ID数量
	/**
	 * 首次通关奖励, 如果玩家第一次通关, 那么需要给与玩家
	 * firstRewardMap + rewardMap + dailyFirstRewardMap
	 */
    private Map<Integer, Integer> firstRewardMap = Maps.newHashMap();
    public Map<Integer, Integer> getFirstRewardMap(){
    	return firstRewardMap;
    }
    
    /**
     * 普通奖励
     */
    private Map<Integer, Integer> rewardMap = Maps.newHashMap();
    public Map<Integer, Integer> getRewardMap(){
    	return rewardMap;
    }
    
    /**
     * 每日首通奖励, 如果玩家每天第一次通关, 奖励如下
     * dailyFirstRewardMap + rewardMap
     */
    private Map<Integer, Integer> dailyFirstRewardMap = Maps.newHashMap();
    public Map<Integer, Integer> getDailyFirstRewardMap(){
    	return dailyFirstRewardMap;
    }
    
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}