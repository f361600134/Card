package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigRobotHero {

	private int ID;//ID
	private List<Integer> heroIds;//武将id
	private List<List<Integer>> attValues;//武将属性
	private int starLevel;//星级
	private int fc;//战力

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public List<Integer> getHeroIds(){
        return heroIds;
    }
    public void setHeroIds(List<Integer> heroIds){
        this.heroIds = heroIds;
    }
    
	public List<List<Integer>> getAttValues(){
        return attValues;
    }
    public void setAttValues(List<List<Integer>> attValues){
        this.attValues = attValues;
    }
    
	public int getStarLevel(){
        return starLevel;
    }
    public void setStarLevel(int starLevel){
        this.starLevel = starLevel;
    }
    
	public int getFc(){
        return fc;
    }
    public void setFc(int fc){
        this.fc = fc;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> attValuesTemps = Maps.newHashMap();
    	for(List<Integer> item : this.attValues)
    	{
    		int c = attValuesTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		attValuesTemps.put(item.get(0), c);
    	}
    	attValuesMap = attValuesTemps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> attValuesMap = Maps.newHashMap();
    public Map<Integer, Integer> getAttValuesMap(){
    	return attValuesMap;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}