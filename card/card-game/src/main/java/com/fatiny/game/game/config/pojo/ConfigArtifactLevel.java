package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigArtifactLevel {

	private int ID;//精炼Id
	private int belong;//所属神兵
	private int level;//等级
	private List<List<Integer>> attrValues;//精炼后获得属性
	private List<List<Integer>> needItems;//精炼所需材料
	private int canBreak;//是否需要突破
	private List<List<Integer>> breakNeedItems;//突破所需材料
	private List<Integer> needHeros;//突破所需英雄

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getBelong(){
        return belong;
    }
    public void setBelong(int belong){
        this.belong = belong;
    }
    
	public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
    
	public List<List<Integer>> getAttrValues(){
        return attrValues;
    }
    public void setAttrValues(List<List<Integer>> attrValues){
        this.attrValues = attrValues;
    }
    
	public List<List<Integer>> getNeedItems(){
        return needItems;
    }
    public void setNeedItems(List<List<Integer>> needItems){
        this.needItems = needItems;
    }
    
	public int getCanBreak(){
        return canBreak;
    }
    public void setCanBreak(int canBreak){
        this.canBreak = canBreak;
    }
    
	public List<List<Integer>> getBreakNeedItems(){
        return breakNeedItems;
    }
    public void setBreakNeedItems(List<List<Integer>> breakNeedItems){
        this.breakNeedItems = breakNeedItems;
    }
    
	public List<Integer> getNeedHeros(){
        return needHeros;
    }
    public void setNeedHeros(List<Integer> needHeros){
        this.needHeros = needHeros;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> attrValuesTemps = Maps.newHashMap();
    	for(List<Integer> item : this.attrValues)
    	{
    		int c = attrValuesTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		attrValuesTemps.put(item.get(0), c);
    	}
    	attrValuesMap = attrValuesTemps;
			    
		Map<Integer, Integer> needItemsTemps = Maps.newHashMap();
    	for(List<Integer> item : this.needItems)
    	{
    		int c = needItemsTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		needItemsTemps.put(item.get(0), c);
    	}
    	needItemsMap = needItemsTemps;
			    
		Map<Integer, Integer> breakNeedItemsTemps = Maps.newHashMap();
    	for(List<Integer> item : this.breakNeedItems)
    	{
    		int c = breakNeedItemsTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		breakNeedItemsTemps.put(item.get(0), c);
    	}
    	breakNeedItemsMap = breakNeedItemsTemps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> attrValuesMap = Maps.newHashMap();
    public Map<Integer, Integer> getAttrValuesMap(){
    	return attrValuesMap;
    }
    
    private Map<Integer, Integer> needItemsMap = Maps.newHashMap();
    public Map<Integer, Integer> getNeedItemsMap(){
    	return needItemsMap;
    }
    
    private Map<Integer, Integer> breakNeedItemsMap = Maps.newHashMap();
    public Map<Integer, Integer> getBreakNeedItemsMap(){
    	return breakNeedItemsMap;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}