package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigStrengthen {

	private int ID;//ID和强化次数
	private int equipId;//装备ID
	private int grade;//品级
	private int strengthenLv;//强化等级
	private int needGold;//强化所需金币
	private List<List<Integer>> attrs;//强化属性

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getEquipId(){
        return equipId;
    }
    public void setEquipId(int equipId){
        this.equipId = equipId;
    }
    
	public int getGrade(){
        return grade;
    }
    public void setGrade(int grade){
        this.grade = grade;
    }
    
	public int getStrengthenLv(){
        return strengthenLv;
    }
    public void setStrengthenLv(int strengthenLv){
        this.strengthenLv = strengthenLv;
    }
    
	public int getNeedGold(){
        return needGold;
    }
    public void setNeedGold(int needGold){
        this.needGold = needGold;
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