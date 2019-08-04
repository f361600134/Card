package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigOfficial {

	private int ID;//ID
	private String name;//mame
	private int grade;//品级
	private int count;//容纳人数
	private int color;//称号字色
	private int mineReward;//每小时矿石奖励
	private List<List<Integer>> dailyReward;//每日结算奖励
	private List<Integer> privilege;//官职特权
	private int monsterGroup;//怪物组ID

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
    
	public int getGrade(){
        return grade;
    }
    public void setGrade(int grade){
        this.grade = grade;
    }
    
	public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count = count;
    }
    
	public int getColor(){
        return color;
    }
    public void setColor(int color){
        this.color = color;
    }
    
	public int getMineReward(){
        return mineReward;
    }
    public void setMineReward(int mineReward){
        this.mineReward = mineReward;
    }
    
	public List<List<Integer>> getDailyReward(){
        return dailyReward;
    }
    public void setDailyReward(List<List<Integer>> dailyReward){
        this.dailyReward = dailyReward;
    }
    
	public List<Integer> getPrivilege(){
        return privilege;
    }
    public void setPrivilege(List<Integer> privilege){
        this.privilege = privilege;
    }
    
	public int getMonsterGroup(){
        return monsterGroup;
    }
    public void setMonsterGroup(int monsterGroup){
        this.monsterGroup = monsterGroup;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> dailyRewardTemps = Maps.newHashMap();
    	for(List<Integer> item : this.dailyReward)
    	{
    		int c = dailyRewardTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		dailyRewardTemps.put(item.get(0), c);
    	}
    	dailyRewardMap = dailyRewardTemps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> dailyRewardMap = Maps.newHashMap();
    public Map<Integer, Integer> getDailyRewardMap(){
    	return dailyRewardMap;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}