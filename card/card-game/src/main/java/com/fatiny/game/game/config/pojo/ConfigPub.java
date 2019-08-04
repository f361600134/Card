package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.common.collect.Maps;

public class ConfigPub {

	private int ID;//酒馆id
	private int groupType;//卡组类型
	private int goldType;//消耗货币类型
	private int cost01;//抽1次消耗
	private int cost10;//抽10次消耗
	private int freeTime;//免费抽取时间间隔
	private int FirstGroup;//第一次抽卡100%获得
	private List<List<Integer>> CardGroup;//普通卡组
	private List<List<Integer>> CardGroup10;//累计第10次卡组
	private int HighQualityLimit;//十连抽紫卡上限

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getGroupType(){
        return groupType;
    }
    public void setGroupType(int groupType){
        this.groupType = groupType;
    }
    
	public int getGoldType(){
        return goldType;
    }
    public void setGoldType(int goldType){
        this.goldType = goldType;
    }
    
	public int getCost01(){
        return cost01;
    }
    public void setCost01(int cost01){
        this.cost01 = cost01;
    }
    
	public int getCost10(){
        return cost10;
    }
    public void setCost10(int cost10){
        this.cost10 = cost10;
    }
    
	public int getFreeTime(){
        return freeTime;
    }
    public void setFreeTime(int freeTime){
        this.freeTime = freeTime;
    }
    
	public int getFirstGroup(){
        return FirstGroup;
    }
    public void setFirstGroup(int FirstGroup){
        this.FirstGroup = FirstGroup;
    }
    
	public List<List<Integer>> getCardGroup(){
        return CardGroup;
    }
    public void setCardGroup(List<List<Integer>> CardGroup){
        this.CardGroup = CardGroup;
    }
    
	public List<List<Integer>> getCardGroup10(){
        return CardGroup10;
    }
    public void setCardGroup10(List<List<Integer>> CardGroup10){
        this.CardGroup10 = CardGroup10;
    }
    
	public int getHighQualityLimit(){
        return HighQualityLimit;
    }
    public void setHighQualityLimit(int HighQualityLimit){
        this.HighQualityLimit = HighQualityLimit;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		TreeMap<Integer, Integer> CardGroupTemps = Maps.newTreeMap();
    	int CardGroupTempRate = 0;
    	for(List<Integer> item : this.CardGroup)
    	{
    		CardGroupTempRate += item.get(1);
    		CardGroupTemps.put(CardGroupTempRate, item.get(0));
    	}
    	CardGroupMax = CardGroupTempRate;
    	CardGroupTrees = CardGroupTemps;
			    
		TreeMap<Integer, Integer> CardGroup10Temps = Maps.newTreeMap();
    	int CardGroup10TempRate = 0;
    	for(List<Integer> item : this.CardGroup10)
    	{
    		CardGroup10TempRate += item.get(1);
    		CardGroup10Temps.put(CardGroup10TempRate, item.get(0));
    	}
    	CardGroup10Max = CardGroup10TempRate;
    	CardGroup10Trees = CardGroup10Temps;
			    
		
    }
	
	//id_rate 权重随机
	private int CardGroupMax;
    private TreeMap<Integer, Integer> CardGroupTrees = Maps.newTreeMap();
    public int getCardGroupByRate(){
    	int rate = (int)(Math.random()*CardGroupMax)+1;
    	Entry<Integer, Integer> entry = CardGroupTrees.ceilingEntry(rate);
    	if(entry==null)
    		return 0;
    	return entry.getValue();
    }
    
	private int CardGroup10Max;
    private TreeMap<Integer, Integer> CardGroup10Trees = Maps.newTreeMap();
    public int getCardGroup10ByRate(){
    	int rate = (int)(Math.random()*CardGroup10Max)+1;
    	Entry<Integer, Integer> entry = CardGroup10Trees.ceilingEntry(rate);
    	if(entry==null)
    		return 0;
    	return entry.getValue();
    }
    
    
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}