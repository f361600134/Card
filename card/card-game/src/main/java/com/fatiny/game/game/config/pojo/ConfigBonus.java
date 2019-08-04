package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.fatiny.game.game.config.pojo.ext.GoodCountData;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ConfigBonus {

	private int ID;//奖励组ID
	private String desc;//说明
	private List<List<Integer>> rewardFix;//奖励（百分百获得）
	private List<List<Integer>> rewardRate1;//物品列表（随机获得）
	private List<List<Integer>> rateCount1;//物品数量上限
	private List<List<Integer>> rewardRate2;//物品列表（随机获得）
	private List<List<Integer>> rateCount2;//物品数量上限
	private List<List<Integer>> rewardRate3;//物品列表（随机获得）
	private List<List<Integer>> rateCount3;//物品数量上限

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    
	public List<List<Integer>> getRewardFix(){
        return rewardFix;
    }
    public void setRewardFix(List<List<Integer>> rewardFix){
        this.rewardFix = rewardFix;
    }
    
	public List<List<Integer>> getRewardRate1(){
        return rewardRate1;
    }
    public void setRewardRate1(List<List<Integer>> rewardRate1){
        this.rewardRate1 = rewardRate1;
    }
    
	public List<List<Integer>> getRateCount1(){
        return rateCount1;
    }
    public void setRateCount1(List<List<Integer>> rateCount1){
        this.rateCount1 = rateCount1;
    }
    
	public List<List<Integer>> getRewardRate2(){
        return rewardRate2;
    }
    public void setRewardRate2(List<List<Integer>> rewardRate2){
        this.rewardRate2 = rewardRate2;
    }
    
	public List<List<Integer>> getRateCount2(){
        return rateCount2;
    }
    public void setRateCount2(List<List<Integer>> rateCount2){
        this.rateCount2 = rateCount2;
    }
    
	public List<List<Integer>> getRewardRate3(){
        return rewardRate3;
    }
    public void setRewardRate3(List<List<Integer>> rewardRate3){
        this.rewardRate3 = rewardRate3;
    }
    
	public List<List<Integer>> getRateCount3(){
        return rateCount3;
    }
    public void setRateCount3(List<List<Integer>> rateCount3){
        this.rateCount3 = rateCount3;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		TreeMap<Integer, GoodCountData> rewardRate1Temps = Maps.newTreeMap();
    	int rewardRate1TempRate = 0;
    	for(List<Integer> item : this.rewardRate1)
    	{
    		GoodCountData data = new GoodCountData();
    		data.setId(item.get(0));
    		data.setCount(item.get(1));
    		data.setRate(item.get(2));
    		rewardRate1TempRate += data.getRate();
    		rewardRate1Temps.put(rewardRate1TempRate, data);
    	}
    	rewardRate1Max = rewardRate1TempRate;
    	rewardRate1Trees = rewardRate1Temps;
			    
		TreeMap<Integer, GoodCountData> rewardRate2Temps = Maps.newTreeMap();
    	int rewardRate2TempRate = 0;
    	for(List<Integer> item : this.rewardRate2)
    	{
    		GoodCountData data = new GoodCountData();
    		data.setId(item.get(0));
    		data.setCount(item.get(1));
    		data.setRate(item.get(2));
    		rewardRate2TempRate += data.getRate();
    		rewardRate2Temps.put(rewardRate2TempRate, data);
    	}
    	rewardRate2Max = rewardRate2TempRate;
    	rewardRate2Trees = rewardRate2Temps;
			    
		TreeMap<Integer, GoodCountData> rewardRate3Temps = Maps.newTreeMap();
    	int rewardRate3TempRate = 0;
    	for(List<Integer> item : this.rewardRate3)
    	{
    		GoodCountData data = new GoodCountData();
    		data.setId(item.get(0));
    		data.setCount(item.get(1));
    		data.setRate(item.get(2));
    		rewardRate3TempRate += data.getRate();
    		rewardRate3Temps.put(rewardRate3TempRate, data);
    	}
    	rewardRate3Max = rewardRate3TempRate;
    	rewardRate3Trees = rewardRate3Temps;
			    
    	List<GoodCountData> rewardFixTemp = Lists.newArrayList();
    	for(List<Integer> item : this.rewardFix)
    	{
    		GoodCountData data = new GoodCountData();
    		data.setId(item.get(0));
    		data.setMinCount(item.get(1));
    		data.setMaxCount(item.get(2));
    		rewardFixTemp.add(data);
    	}
    	rewardFixDatas = rewardFixTemp;
			    
		
    }
	
	//id_count_rate 总体概率随机
	private int rewardRate1Max;
    private TreeMap<Integer, GoodCountData> rewardRate1Trees = Maps.newTreeMap();
    public GoodCountData getRewardRate1ByRate(){
    	int rate = (int)(Math.random()*rewardRate1Max)+1;
    	Entry<Integer, GoodCountData> entry = rewardRate1Trees.ceilingEntry(rate);
    	if(entry==null)
    		return null;
    	return entry.getValue();
    }
    
	private int rewardRate2Max;
    private TreeMap<Integer, GoodCountData> rewardRate2Trees = Maps.newTreeMap();
    public GoodCountData getRewardRate2ByRate(){
    	int rate = (int)(Math.random()*rewardRate2Max)+1;
    	Entry<Integer, GoodCountData> entry = rewardRate2Trees.ceilingEntry(rate);
    	if(entry==null)
    		return null;
    	return entry.getValue();
    }
    
	private int rewardRate3Max;
    private TreeMap<Integer, GoodCountData> rewardRate3Trees = Maps.newTreeMap();
    public GoodCountData getRewardRate3ByRate(){
    	int rate = (int)(Math.random()*rewardRate3Max)+1;
    	Entry<Integer, GoodCountData> entry = rewardRate3Trees.ceilingEntry(rate);
    	if(entry==null)
    		return null;
    	return entry.getValue();
    }
    
	//id_minCount_maxCount 数量随机
	private List<GoodCountData> rewardFixDatas = Lists.newArrayList();
    public List<GoodCountData> getRewardFixByRate(){
    	List<GoodCountData> rets = Lists.newArrayList();
    	for(GoodCountData item : this.rewardFixDatas)
    	{
    		int count = item.getMinCount() + (int)(Math.random()*(item.getMaxCount()-item.getMinCount()+1));
    		GoodCountData data = new GoodCountData(item);
    		data.setCount(count);
    		rets.add(data);
    	}
    	return rets;
    }
    
	
	/////////UserDefine Begin///////////
    
    public int getRandCount1() {
    	if(this.getRateCount1().isEmpty())
    		return 0;
    	List<Integer> items = this.getRateCount1().get(0);
    	double rand = Math.random();
    	int count = items.get(0) + (int)(rand*(items.get(1)-items.get(0)+1));
    	//System.out.println("count1:"+count+"    rand1:"+rand);
    	return count;
    }
    
    public int getRandCount2() {
    	if(this.getRateCount2().isEmpty())
    		return 0;
    	List<Integer> items = this.getRateCount2().get(0);
    	double rand = Math.random();
    	int count = items.get(0) + (int)(rand*(items.get(1)-items.get(0)+1));
    	//System.out.println("count2:"+count+"    rand2:"+rand);
    	return count;
    }
    
    public int getRandCount3() {
    	if(this.getRateCount3().isEmpty())
    		return 0;
    	List<Integer> items = this.getRateCount3().get(0);
    	double rand = Math.random();
    	int count = items.get(0) + (int)(rand*(items.get(1)-items.get(0)+1));
    	//System.out.println("count3:"+count+"    rand3:"+rand);
    	return count;
    }
    
	/////////UserDefine End/////////////
	
}
