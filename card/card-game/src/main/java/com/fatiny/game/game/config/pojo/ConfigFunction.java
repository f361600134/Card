package com.fatiny.game.game.config.pojo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

public class ConfigFunction {

	private int ID;//索引
	private String name;//名称
	private List<Integer> redPoints;//红点条件
	private List<List<Integer>> activeCondition;//激活条件
	private int showLv;//可见等级
	private String res;//图标路径
	private int isOpen;//是否开放
	private List<List<Integer>> limits;//条件限制
	private int resetTime;//系统重置时间

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
    
	public List<Integer> getRedPoints(){
        return redPoints;
    }
    public void setRedPoints(List<Integer> redPoints){
        this.redPoints = redPoints;
    }
    
	public List<List<Integer>> getActiveCondition(){
        return activeCondition;
    }
    public void setActiveCondition(List<List<Integer>> activeCondition){
        this.activeCondition = activeCondition;
    }
    
	public int getShowLv(){
        return showLv;
    }
    public void setShowLv(int showLv){
        this.showLv = showLv;
    }
    
	public String getRes(){
        return res;
    }
    public void setRes(String res){
        this.res = res;
    }
    
	public int getIsOpen(){
        return isOpen;
    }
    public void setIsOpen(int isOpen){
        this.isOpen = isOpen;
    }
    
	public List<List<Integer>> getLimits(){
        return limits;
    }
    public void setLimits(List<List<Integer>> limits){
        this.limits = limits;
    }
    
	public int getResetTime(){
        return resetTime;
    }
    public void setResetTime(int resetTime){
        this.resetTime = resetTime;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> limitsTemps = Maps.newHashMap();
    	for(List<Integer> item : this.limits)
    	{
    		int c = limitsTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		limitsTemps.put(item.get(0), c);
    	}
    	limitsMap = limitsTemps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> limitsMap = Maps.newHashMap();
    public Map<Integer, Integer> getLimitsMap(){
    	return limitsMap;
    }
    
	
	/////////UserDefine Begin///////////
	
	public static final int LevelCondition = 1;
	public static final int StageCondition = 2;
	public static final int VIPCondition = 3;
	
	public boolean canUnlockWhenLevelUp(int levelFrom, int levelTo) {
		if(this.activeCondition.isEmpty())
			return false;
		for(List<Integer> condition : this.activeCondition)
		{
			if(condition.get(0)==LevelCondition)
				return (levelFrom < condition.get(1) && condition.get(1) <= levelTo);
		}
		return false;
	}
	
	public boolean isLevelOK(int level) {
		if(this.activeCondition.isEmpty())
			return true;
		for(List<Integer> condition : this.activeCondition)
		{
			if(condition.get(0)==LevelCondition)
				return (condition.get(1) <= level);
		}
		return true;
	}
	
	public boolean canUnlockWhenStagePass(int stageId) {
		if(this.activeCondition.isEmpty())
			return false;
		for(List<Integer> condition : this.activeCondition)
		{
			if(condition.get(0)==StageCondition)
				return (condition.get(2) == stageId);
		}
		return false;
	}
	
	public boolean isStagePassOK(Set<Integer> passStageIds) {
		if(this.activeCondition.isEmpty())
			return true;
		for(List<Integer> condition : this.activeCondition)
		{
			if(condition.get(0)==StageCondition)
				return passStageIds!=null && passStageIds.contains(condition.get(2));
		}
		return true;
	}
	
	public boolean canUnlockWhenVipUp(int vipFrom, int vipTo) {
		if(this.activeCondition.isEmpty())
			return false;
		for(List<Integer> condition : this.activeCondition)
		{
			if(condition.get(0)==VIPCondition)
				return (vipFrom < condition.get(1) && condition.get(1) <= vipTo);
		}
		return false;
	}
	
	public boolean isVipOK(int vip) {
		if(this.activeCondition.isEmpty())
			return true;
		for(List<Integer> condition : this.activeCondition)
		{
			if(condition.get(0)==VIPCondition)
				return (condition.get(1) <= vip);
		}
		return true;
	}
	
	/**
	 * 获取今日刷新时间
	 * @return  
	 * @return Date  
	 * @date 2019年7月14日上午12:05:43
	 */
	public Date getTodayResetTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, this.resetTime);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/////////UserDefine End/////////////
	
}
