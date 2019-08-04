package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigRelation {

	private int ID;//羁绊ID
	private String name;//羁绊名称
	private String desc;//羁绊描述
	private String icon;//icon图标
	private int RelationType;//羁绊类型
	private List<Integer> groups;//羁绊组合
	private List<List<Integer>> attrs;//羁绊加属性

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
    
	public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    
	public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    
	public int getRelationType(){
        return RelationType;
    }
    public void setRelationType(int RelationType){
        this.RelationType = RelationType;
    }
    
	public List<Integer> getGroups(){
        return groups;
    }
    public void setGroups(List<Integer> groups){
        this.groups = groups;
    }
    
	public List<List<Integer>> getAttrs(){
        return attrs;
    }
    public void setAttrs(List<List<Integer>> attrs){
        this.attrs = attrs;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> attrsTemps = Maps.newHashMap();
    	for(List<Integer> item : this.attrs)
    	{
    		int c = attrsTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		attrsTemps.put(item.get(0), c);
    	}
    	attrsMap = attrsTemps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> attrsMap = Maps.newHashMap();
    public Map<Integer, Integer> getAttrsMap(){
    	return attrsMap;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}