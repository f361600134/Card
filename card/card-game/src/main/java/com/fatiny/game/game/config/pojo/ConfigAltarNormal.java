package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.fatiny.game.game.config.pojo.ext.GoodCountData;
import com.google.common.collect.Maps;

public class ConfigAltarNormal {

	private int ID;//ID
	private int type;//类型
	private int buyTime;//每天付费次数
	private List<List<Integer>> group0;//免费组
	private List<List<Integer>> group50;//50以下
	private List<List<Integer>> group250;//50-250
	private List<List<Integer>> group850;//250-850
	private List<List<Integer>> group1500;//850-1500
	private List<List<Integer>> group3000;//1500-3000
	private List<List<Integer>> groupMore;//3000以上

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    
	public int getBuyTime(){
        return buyTime;
    }
    public void setBuyTime(int buyTime){
        this.buyTime = buyTime;
    }
    
	public List<List<Integer>> getGroup0(){
        return group0;
    }
    public void setGroup0(List<List<Integer>> group0){
        this.group0 = group0;
    }
    
	public List<List<Integer>> getGroup50(){
        return group50;
    }
    public void setGroup50(List<List<Integer>> group50){
        this.group50 = group50;
    }
    
	public List<List<Integer>> getGroup250(){
        return group250;
    }
    public void setGroup250(List<List<Integer>> group250){
        this.group250 = group250;
    }
    
	public List<List<Integer>> getGroup850(){
        return group850;
    }
    public void setGroup850(List<List<Integer>> group850){
        this.group850 = group850;
    }
    
	public List<List<Integer>> getGroup1500(){
        return group1500;
    }
    public void setGroup1500(List<List<Integer>> group1500){
        this.group1500 = group1500;
    }
    
	public List<List<Integer>> getGroup3000(){
        return group3000;
    }
    public void setGroup3000(List<List<Integer>> group3000){
        this.group3000 = group3000;
    }
    
	public List<List<Integer>> getGroupMore(){
        return groupMore;
    }
    public void setGroupMore(List<List<Integer>> groupMore){
        this.groupMore = groupMore;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
    	TreeMap<Integer, GoodCountData> group0Temps = Maps.newTreeMap();
    	int group0TempRate = 0;
    	for(List<Integer> item : this.group0)
    	{
    		GoodCountData data = new GoodCountData();
    		data.setId(item.get(0));
    		data.setRate(item.get(1));
    		data.setMinCount(item.get(2));
    		data.setMaxCount(item.get(3));
    		group0TempRate += data.getRate();
    		group0Temps.put(group0TempRate, data);
    	}
    	group0Max = group0TempRate;
    	group0Trees = group0Temps;
    	
    	TreeMap<Integer, GoodCountData> group50Temps = Maps.newTreeMap();
    	int group50TempRate = 0;
    	for(List<Integer> item : this.group50)
    	{
    		GoodCountData data = new GoodCountData();
    		data.setId(item.get(0));
    		data.setRate(item.get(1));
    		data.setMinCount(item.get(2));
    		data.setMaxCount(item.get(3));
    		group50TempRate += data.getRate();
    		group50Temps.put(group50TempRate, data);
    	}
    	group50Max = group50TempRate;
    	group50Trees = group50Temps;
    	
    	TreeMap<Integer, GoodCountData> group250Temps = Maps.newTreeMap();
    	int group250TempRate = 0;
    	for(List<Integer> item : this.group250)
    	{
    		GoodCountData data = new GoodCountData();
    		data.setId(item.get(0));
    		data.setRate(item.get(1));
    		data.setMinCount(item.get(2));
    		data.setMaxCount(item.get(3));
    		group250TempRate += data.getRate();
    		group250Temps.put(group250TempRate, data);
    	}
    	group250Max = group250TempRate;
    	group250Trees = group250Temps;
    	
    	TreeMap<Integer, GoodCountData> group850Temps = Maps.newTreeMap();
    	int group850TempRate = 0;
    	for(List<Integer> item : this.group850)
    	{
    		GoodCountData data = new GoodCountData();
    		data.setId(item.get(0));
    		data.setRate(item.get(1));
    		data.setMinCount(item.get(2));
    		data.setMaxCount(item.get(3));
    		group850TempRate += data.getRate();
    		group850Temps.put(group850TempRate, data);
    	}
    	group850Max = group850TempRate;
    	group850Trees = group850Temps;
    	
    	TreeMap<Integer, GoodCountData> group1500Temps = Maps.newTreeMap();
    	int group1500TempRate = 0;
    	for(List<Integer> item : this.group1500)
    	{
    		GoodCountData data = new GoodCountData();
    		data.setId(item.get(0));
    		data.setRate(item.get(1));
    		data.setMinCount(item.get(2));
    		data.setMaxCount(item.get(3));
    		group1500TempRate += data.getRate();
    		group1500Temps.put(group1500TempRate, data);
    	}
    	group1500Max = group1500TempRate;
    	group1500Trees = group1500Temps;
    	
    	TreeMap<Integer, GoodCountData> group3000Temps = Maps.newTreeMap();
    	int group3000TempRate = 0;
    	for(List<Integer> item : this.group3000)
    	{
    		GoodCountData data = new GoodCountData();
    		data.setId(item.get(0));
    		data.setRate(item.get(1));
    		data.setMinCount(item.get(2));
    		data.setMaxCount(item.get(3));
    		group3000TempRate += data.getRate();
    		group3000Temps.put(group3000TempRate, data);
    	}
    	group3000Max = group3000TempRate;
    	group3000Trees = group3000Temps;
    	
    	TreeMap<Integer, GoodCountData> groupMoreTemps = Maps.newTreeMap();
    	int groupMoreTempRate = 0;
    	for(List<Integer> item : this.groupMore)
    	{
    		GoodCountData data = new GoodCountData();
    		data.setId(item.get(0));
    		data.setRate(item.get(1));
    		data.setMinCount(item.get(2));
    		data.setMaxCount(item.get(3));
    		groupMoreTempRate += data.getRate();
    		groupMoreTemps.put(groupMoreTempRate, data);
    	}
    	groupMoreMax = groupMoreTempRate;
    	groupMoreTrees = groupMoreTemps;
    	
		
    }
	
	//id_rate_mincount_maxcount 权重数量
	private int group0Max;
    private TreeMap<Integer, GoodCountData> group0Trees = Maps.newTreeMap();
    public GoodCountData getGroup0ByRate(){
    	int rate = (int)(Math.random()*group0Max)+1;
    	Entry<Integer, GoodCountData> entry = group0Trees.ceilingEntry(rate);
    	if(entry==null)
    		return null;
    	GoodCountData item = entry.getValue();
    	int count = item.getMinCount() + (int)(Math.random()*(item.getMaxCount()-item.getMinCount()+1));
    	GoodCountData data = new GoodCountData(item);
    	data.setCount(count);
    	return data;
    }
    
	private int group50Max;
    private TreeMap<Integer, GoodCountData> group50Trees = Maps.newTreeMap();
    public GoodCountData getGroup50ByRate(){
    	int rate = (int)(Math.random()*group50Max)+1;
    	Entry<Integer, GoodCountData> entry = group50Trees.ceilingEntry(rate);
    	if(entry==null)
    		return null;
    	GoodCountData item = entry.getValue();
    	int count = item.getMinCount() + (int)(Math.random()*(item.getMaxCount()-item.getMinCount()+1));
    	GoodCountData data = new GoodCountData(item);
    	data.setCount(count);
    	return data;
    }
    
	private int group250Max;
    private TreeMap<Integer, GoodCountData> group250Trees = Maps.newTreeMap();
    public GoodCountData getGroup250ByRate(){
    	int rate = (int)(Math.random()*group250Max)+1;
    	Entry<Integer, GoodCountData> entry = group250Trees.ceilingEntry(rate);
    	if(entry==null)
    		return null;
    	GoodCountData item = entry.getValue();
    	int count = item.getMinCount() + (int)(Math.random()*(item.getMaxCount()-item.getMinCount()+1));
    	GoodCountData data = new GoodCountData(item);
    	data.setCount(count);
    	return data;
    }
    
	private int group850Max;
    private TreeMap<Integer, GoodCountData> group850Trees = Maps.newTreeMap();
    public GoodCountData getGroup850ByRate(){
    	int rate = (int)(Math.random()*group850Max)+1;
    	Entry<Integer, GoodCountData> entry = group850Trees.ceilingEntry(rate);
    	if(entry==null)
    		return null;
    	GoodCountData item = entry.getValue();
    	int count = item.getMinCount() + (int)(Math.random()*(item.getMaxCount()-item.getMinCount()+1));
    	GoodCountData data = new GoodCountData(item);
    	data.setCount(count);
    	return data;
    }
    
	private int group1500Max;
    private TreeMap<Integer, GoodCountData> group1500Trees = Maps.newTreeMap();
    public GoodCountData getGroup1500ByRate(){
    	int rate = (int)(Math.random()*group1500Max)+1;
    	Entry<Integer, GoodCountData> entry = group1500Trees.ceilingEntry(rate);
    	if(entry==null)
    		return null;
    	GoodCountData item = entry.getValue();
    	int count = item.getMinCount() + (int)(Math.random()*(item.getMaxCount()-item.getMinCount()+1));
    	GoodCountData data = new GoodCountData(item);
    	data.setCount(count);
    	return data;
    }
    
	private int group3000Max;
    private TreeMap<Integer, GoodCountData> group3000Trees = Maps.newTreeMap();
    public GoodCountData getGroup3000ByRate(){
    	int rate = (int)(Math.random()*group3000Max)+1;
    	Entry<Integer, GoodCountData> entry = group3000Trees.ceilingEntry(rate);
    	if(entry==null)
    		return null;
    	GoodCountData item = entry.getValue();
    	int count = item.getMinCount() + (int)(Math.random()*(item.getMaxCount()-item.getMinCount()+1));
    	GoodCountData data = new GoodCountData(item);
    	data.setCount(count);
    	return data;
    }
    
	private int groupMoreMax;
    private TreeMap<Integer, GoodCountData> groupMoreTrees = Maps.newTreeMap();
    public GoodCountData getGroupMoreByRate(){
    	int rate = (int)(Math.random()*groupMoreMax)+1;
    	Entry<Integer, GoodCountData> entry = groupMoreTrees.ceilingEntry(rate);
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