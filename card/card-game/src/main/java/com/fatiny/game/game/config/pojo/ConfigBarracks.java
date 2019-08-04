package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigBarracks {

	private int ID;//索引ID=ID(ConfigTechnology中的ID) *1000+ level
	private int openBarrackLv;//所需兵营等级
	private int duration;//升级持续时间
	private int needIron;//所需铁矿
	private List<List<Integer>> attrs;//升级获得属性

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getOpenBarrackLv(){
        return openBarrackLv;
    }
    public void setOpenBarrackLv(int openBarrackLv){
        this.openBarrackLv = openBarrackLv;
    }
    
	public int getDuration(){
        return duration;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    
	public int getNeedIron(){
        return needIron;
    }
    public void setNeedIron(int needIron){
        this.needIron = needIron;
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