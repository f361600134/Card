package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigArtifactInfo {

	private int ID;//神兵ID
	private String name;//神兵名称
	private List<List<Integer>> initialAttrs;//神兵初始属性
	private int initialSkillId;//初始技能ID
	private List<List<Integer>> needItems;//打造材料

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
    
	public List<List<Integer>> getInitialAttrs(){
        return initialAttrs;
    }
    public void setInitialAttrs(List<List<Integer>> initialAttrs){
        this.initialAttrs = initialAttrs;
    }
    
	public int getInitialSkillId(){
        return initialSkillId;
    }
    public void setInitialSkillId(int initialSkillId){
        this.initialSkillId = initialSkillId;
    }
    
	public List<List<Integer>> getNeedItems(){
        return needItems;
    }
    public void setNeedItems(List<List<Integer>> needItems){
        this.needItems = needItems;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> initialAttrsTemps = Maps.newHashMap();
    	for(List<Integer> item : this.initialAttrs)
    	{
    		int c = initialAttrsTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		initialAttrsTemps.put(item.get(0), c);
    	}
    	initialAttrsMap = initialAttrsTemps;
			    
		Map<Integer, Integer> needItemsTemps = Maps.newHashMap();
    	for(List<Integer> item : this.needItems)
    	{
    		int c = needItemsTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		needItemsTemps.put(item.get(0), c);
    	}
    	needItemsMap = needItemsTemps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> initialAttrsMap = Maps.newHashMap();
    public Map<Integer, Integer> getInitialAttrsMap(){
    	return initialAttrsMap;
    }
    
    private Map<Integer, Integer> needItemsMap = Maps.newHashMap();
    public Map<Integer, Integer> getNeedItemsMap(){
    	return needItemsMap;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}