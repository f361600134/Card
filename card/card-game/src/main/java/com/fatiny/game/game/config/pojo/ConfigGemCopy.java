package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.common.collect.Maps;

public class ConfigGemCopy {

	private int ID;//ID
	private int boss;//bossid
	private String icon;//boss头像
	private int level;//矿洞层数
	private int difficulty;//难度
	private int openlevel;//开启等级
	private List<Integer> enemy;//怪物组
	private int time;//挑战时间
	private List<Integer> showItems;//显示奖励
	private String map;//地图
	private List<List<Integer>> reward1;//掉落奖励1
	private int count1;//固定掉落数量1
	private List<List<Integer>> reward2;//掉落奖励2
	private int count2;//固定掉落数量2

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getBoss(){
        return boss;
    }
    public void setBoss(int boss){
        this.boss = boss;
    }
    
	public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    
	public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
    
	public int getDifficulty(){
        return difficulty;
    }
    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    
	public int getOpenlevel(){
        return openlevel;
    }
    public void setOpenlevel(int openlevel){
        this.openlevel = openlevel;
    }
    
	public List<Integer> getEnemy(){
        return enemy;
    }
    public void setEnemy(List<Integer> enemy){
        this.enemy = enemy;
    }
    
	public int getTime(){
        return time;
    }
    public void setTime(int time){
        this.time = time;
    }
    
	public List<Integer> getShowItems(){
        return showItems;
    }
    public void setShowItems(List<Integer> showItems){
        this.showItems = showItems;
    }
    
	public String getMap(){
        return map;
    }
    public void setMap(String map){
        this.map = map;
    }
    
	public List<List<Integer>> getReward1(){
        return reward1;
    }
    public void setReward1(List<List<Integer>> reward1){
        this.reward1 = reward1;
    }
    
	public int getCount1(){
        return count1;
    }
    public void setCount1(int count1){
        this.count1 = count1;
    }
    
	public List<List<Integer>> getReward2(){
        return reward2;
    }
    public void setReward2(List<List<Integer>> reward2){
        this.reward2 = reward2;
    }
    
	public int getCount2(){
        return count2;
    }
    public void setCount2(int count2){
        this.count2 = count2;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		TreeMap<Integer, Integer> reward1Temps = Maps.newTreeMap();
    	int reward1TempRate = 0;
    	for(List<Integer> item : this.reward1)
    	{
    		reward1TempRate += item.get(1);
    		reward1Temps.put(reward1TempRate, item.get(0));
    	}
    	reward1Max = reward1TempRate;
    	reward1Trees = reward1Temps;
			    
		TreeMap<Integer, Integer> reward2Temps = Maps.newTreeMap();
    	int reward2TempRate = 0;
    	for(List<Integer> item : this.reward2)
    	{
    		reward2TempRate += item.get(1);
    		reward2Temps.put(reward2TempRate, item.get(0));
    	}
    	reward2Max = reward2TempRate;
    	reward2Trees = reward2Temps;
			    
		
    }
	
	//id_rate 权重随机
	private int reward1Max;
    private TreeMap<Integer, Integer> reward1Trees = Maps.newTreeMap();
    public int getReward1ByRate(){
    	int rate = (int)(Math.random()*reward1Max)+1;
    	Entry<Integer, Integer> entry = reward1Trees.ceilingEntry(rate);
    	if(entry==null)
    		return 0;
    	return entry.getValue();
    }
    
	private int reward2Max;
    private TreeMap<Integer, Integer> reward2Trees = Maps.newTreeMap();
    public int getReward2ByRate(){
    	int rate = (int)(Math.random()*reward2Max)+1;
    	Entry<Integer, Integer> entry = reward2Trees.ceilingEntry(rate);
    	if(entry==null)
    		return 0;
    	return entry.getValue();
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}