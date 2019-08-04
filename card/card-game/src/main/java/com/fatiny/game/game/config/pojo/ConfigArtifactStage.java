package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.common.collect.Maps;

public class ConfigArtifactStage {

	private int ID;//神兵副本ID
	private String name;//副本名称
	private String icon;//图标icon
	private int level;//难度等级
	private int needLevel;//主公等级开启
	private String desc;//副本文字描述
	private List<Integer> showItems;//显示奖励
	private List<Integer> monsterGroups;//怪物组列表
	private List<List<Integer>> reward;//掉落奖励
	private List<Integer> count;//每次掉落数量

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
    
	public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    
	public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
    
	public int getNeedLevel(){
        return needLevel;
    }
    public void setNeedLevel(int needLevel){
        this.needLevel = needLevel;
    }
    
	public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    
	public List<Integer> getShowItems(){
        return showItems;
    }
    public void setShowItems(List<Integer> showItems){
        this.showItems = showItems;
    }
    
	public List<Integer> getMonsterGroups(){
        return monsterGroups;
    }
    public void setMonsterGroups(List<Integer> monsterGroups){
        this.monsterGroups = monsterGroups;
    }
    
	public List<List<Integer>> getReward(){
        return reward;
    }
    public void setReward(List<List<Integer>> reward){
        this.reward = reward;
    }
    
	public List<Integer> getCount(){
        return count;
    }
    public void setCount(List<Integer> count){
        this.count = count;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		TreeMap<Integer, Integer> rewardTemps = Maps.newTreeMap();
    	int rewardTempRate = 0;
    	for(List<Integer> item : this.reward)
    	{
    		rewardTempRate += item.get(1);
    		rewardTemps.put(rewardTempRate, item.get(0));
    	}
    	rewardMax = rewardTempRate;
    	rewardTrees = rewardTemps;
			    
		
    }
	
	//id_rate 权重随机
	private int rewardMax;
    private TreeMap<Integer, Integer> rewardTrees = Maps.newTreeMap();
    public int getRewardByRate(){
    	int rate = (int)(Math.random()*rewardMax)+1;
    	Entry<Integer, Integer> entry = rewardTrees.ceilingEntry(rate);
    	if(entry==null)
    		return 0;
    	return entry.getValue();
    }
    
	
	/////////UserDefine Begin///////////
    // 数量随机
    //根据权重计算出来的道具奖励
    public Map<Integer, Integer> getRewards(){
    	Map<Integer, Integer> rewardMap = Maps.newTreeMap();
    	int min = count.get(0);
    	int max = count.get(1);
    	int real = (int)(Math.random()*(max-min+1))+(min);
    	for (int i = 1; i <= real; i++) {
    		Integer rewardId = getRewardByRate();
    		if (rewardMap.containsKey(rewardId)) 
    			rewardMap.put(rewardId, rewardMap.get(rewardId)+1);
			else 
				rewardMap.put(rewardId, 1);
		}
    	return rewardMap;
    }
    
	/////////UserDefine End/////////////
	
}
