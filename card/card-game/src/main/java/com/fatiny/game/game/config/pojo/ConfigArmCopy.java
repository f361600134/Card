package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.fatiny.game.game.config.pojo.ext.GoodCountData;
import com.google.common.collect.Maps;

public class ConfigArmCopy {

	private int ID;//ID
	private int heroID;//武将ID
	private int openLevel;//开启等级
	private List<Integer> enemy;//怪物组
	private int time;//挑战时间
	private List<List<Integer>> reward;//掉落奖励
	private List<Integer> showItems;//显示奖励
	private String heading;//标题
	private String describe;//武将描述
	private String map;//地图

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getHeroID(){
        return heroID;
    }
    public void setHeroID(int heroID){
        this.heroID = heroID;
    }
    
	public int getOpenLevel(){
        return openLevel;
    }
    public void setOpenLevel(int openLevel){
        this.openLevel = openLevel;
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
    
	public List<List<Integer>> getReward(){
        return reward;
    }
    public void setReward(List<List<Integer>> reward){
        this.reward = reward;
    }
    
	public List<Integer> getShowItems(){
        return showItems;
    }
    public void setShowItems(List<Integer> showItems){
        this.showItems = showItems;
    }
    
	public String getHeading(){
        return heading;
    }
    public void setHeading(String heading){
        this.heading = heading;
    }
    
	public String getDescribe(){
        return describe;
    }
    public void setDescribe(String describe){
        this.describe = describe;
    }
    
	public String getMap(){
        return map;
    }
    public void setMap(String map){
        this.map = map;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
    	TreeMap<Integer, GoodCountData> rewardTemps = Maps.newTreeMap();
    	int rewardTempRate = 0;
    	for(List<Integer> item : this.reward)
    	{
    		GoodCountData data = new GoodCountData();
    		data.setId(item.get(0));
    		data.setRate(item.get(1));
    		data.setMinCount(item.get(2));
    		data.setMaxCount(item.get(3));
    		rewardTempRate += data.getRate();
    		rewardTemps.put(rewardTempRate, data);
    	}
    	rewardMax = rewardTempRate;
    	rewardTrees = rewardTemps;
    	
		
    }
	
	//id_rate_mincount_maxcount 权重数量
	private int rewardMax;
    private TreeMap<Integer, GoodCountData> rewardTrees = Maps.newTreeMap();
    public GoodCountData getRewardByRate(){
    	int rate = (int)(Math.random()*rewardMax)+1;
    	Entry<Integer, GoodCountData> entry = rewardTrees.ceilingEntry(rate);
    	if(entry==null)
    		return null;
    	GoodCountData item = entry.getValue();
    	int count = item.getMinCount() + (int)(Math.random()*(item.getMaxCount()-item.getMinCount()+1));
    	GoodCountData data = new GoodCountData(item);
    	data.setCount(count);
    	return data;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}