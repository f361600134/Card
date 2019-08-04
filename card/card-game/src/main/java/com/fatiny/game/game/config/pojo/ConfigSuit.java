package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigSuit {

	private int ID;//套装ID
	private String name;//套装名
	private int num;//激活数量
	private List<Integer> needEquips;//所需装备
	private List<List<Integer>> Equips2;//两件套
	private List<List<Integer>> Equips4;//四件套
	private List<List<Integer>> Equips6;//六件套

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
    
	public int getNum(){
        return num;
    }
    public void setNum(int num){
        this.num = num;
    }
    
	public List<Integer> getNeedEquips(){
        return needEquips;
    }
    public void setNeedEquips(List<Integer> needEquips){
        this.needEquips = needEquips;
    }
    
	public List<List<Integer>> getEquips2(){
        return Equips2;
    }
    public void setEquips2(List<List<Integer>> Equips2){
        this.Equips2 = Equips2;
    }
    
	public List<List<Integer>> getEquips4(){
        return Equips4;
    }
    public void setEquips4(List<List<Integer>> Equips4){
        this.Equips4 = Equips4;
    }
    
	public List<List<Integer>> getEquips6(){
        return Equips6;
    }
    public void setEquips6(List<List<Integer>> Equips6){
        this.Equips6 = Equips6;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> Equips2Temps = Maps.newHashMap();
    	for(List<Integer> item : this.Equips2)
    	{
    		int c = Equips2Temps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		Equips2Temps.put(item.get(0), c);
    	}
    	Equips2Map = Equips2Temps;
			    
		Map<Integer, Integer> Equips4Temps = Maps.newHashMap();
    	for(List<Integer> item : this.Equips4)
    	{
    		int c = Equips4Temps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		Equips4Temps.put(item.get(0), c);
    	}
    	Equips4Map = Equips4Temps;
			    
		Map<Integer, Integer> Equips6Temps = Maps.newHashMap();
    	for(List<Integer> item : this.Equips6)
    	{
    		int c = Equips6Temps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		Equips6Temps.put(item.get(0), c);
    	}
    	Equips6Map = Equips6Temps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> Equips2Map = Maps.newHashMap();
    public Map<Integer, Integer> getEquips2Map(){
    	return Equips2Map;
    }
    
    private Map<Integer, Integer> Equips4Map = Maps.newHashMap();
    public Map<Integer, Integer> getEquips4Map(){
    	return Equips4Map;
    }
    
    private Map<Integer, Integer> Equips6Map = Maps.newHashMap();
    public Map<Integer, Integer> getEquips6Map(){
    	return Equips6Map;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}