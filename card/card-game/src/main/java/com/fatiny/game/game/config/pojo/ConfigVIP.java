package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigVIP {

	private int ID;//ID
	private String name;//VIP等级
	private int needExp;//升级所需VIP经验
	private List<List<Integer>> exclusiveReward;//VIP专属礼包
	private List<List<Integer>> privilege;//特权
	private List<List<Integer>> cycleReward;//VIP双周礼包

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
    
	public int getNeedExp(){
        return needExp;
    }
    public void setNeedExp(int needExp){
        this.needExp = needExp;
    }
    
	public List<List<Integer>> getExclusiveReward(){
        return exclusiveReward;
    }
    public void setExclusiveReward(List<List<Integer>> exclusiveReward){
        this.exclusiveReward = exclusiveReward;
    }
    
	public List<List<Integer>> getPrivilege(){
        return privilege;
    }
    public void setPrivilege(List<List<Integer>> privilege){
        this.privilege = privilege;
    }
    
	public List<List<Integer>> getCycleReward(){
        return cycleReward;
    }
    public void setCycleReward(List<List<Integer>> cycleReward){
        this.cycleReward = cycleReward;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> exclusiveRewardTemps = Maps.newHashMap();
    	for(List<Integer> item : this.exclusiveReward)
    	{
    		int c = exclusiveRewardTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		exclusiveRewardTemps.put(item.get(0), c);
    	}
    	exclusiveRewardMap = exclusiveRewardTemps;
			    
		Map<Integer, Integer> privilegeTemps = Maps.newHashMap();
    	for(List<Integer> item : this.privilege)
    	{
    		int c = privilegeTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		privilegeTemps.put(item.get(0), c);
    	}
    	privilegeMap = privilegeTemps;
			    
		Map<Integer, Integer> cycleRewardTemps = Maps.newHashMap();
    	for(List<Integer> item : this.cycleReward)
    	{
    		int c = cycleRewardTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		cycleRewardTemps.put(item.get(0), c);
    	}
    	cycleRewardMap = cycleRewardTemps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> exclusiveRewardMap = Maps.newHashMap();
    public Map<Integer, Integer> getExclusiveRewardMap(){
    	return exclusiveRewardMap;
    }
    
    private Map<Integer, Integer> privilegeMap = Maps.newHashMap();
    public Map<Integer, Integer> getPrivilegeMap(){
    	return privilegeMap;
    }
    
    private Map<Integer, Integer> cycleRewardMap = Maps.newHashMap();
    public Map<Integer, Integer> getCycleRewardMap(){
    	return cycleRewardMap;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}