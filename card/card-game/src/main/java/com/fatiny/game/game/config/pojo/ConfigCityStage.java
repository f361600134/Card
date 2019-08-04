package com.fatiny.game.game.config.pojo;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class ConfigCityStage {

	private int ID;//城市id
	private String name;//城市名
	private int quality;//品质
	private int rate;//刷新权重
	private List<Integer> counts;//小资源点数量
	private int schemeId;//资源分配方案
	private int produce;//每小时产量
	private int produceTime;//可采集时间
	private int capacity;//容器大小
	private int sta;//占领消耗耐力
	private int staSpeed;//5分钟消耗耐力
	private int backGround;//背景图

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
    
	public int getQuality(){
        return quality;
    }
    public void setQuality(int quality){
        this.quality = quality;
    }
    
	public int getRate(){
        return rate;
    }
    public void setRate(int rate){
        this.rate = rate;
    }
    
	public List<Integer> getCounts(){
        return counts;
    }
    public void setCounts(List<Integer> counts){
        this.counts = counts;
    }
    
	public int getSchemeId(){
        return schemeId;
    }
    public void setSchemeId(int schemeId){
        this.schemeId = schemeId;
    }
    
	public int getProduce(){
        return produce;
    }
    public void setProduce(int produce){
        this.produce = produce;
    }
    
	public int getProduceTime(){
        return produceTime;
    }
    public void setProduceTime(int produceTime){
        this.produceTime = produceTime;
    }
    
	public int getCapacity(){
        return capacity;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    
	public int getSta(){
        return sta;
    }
    public void setSta(int sta){
        this.sta = sta;
    }
    
	public int getStaSpeed(){
        return staSpeed;
    }
    public void setStaSpeed(int staSpeed){
        this.staSpeed = staSpeed;
    }
    
	public int getBackGround(){
        return backGround;
    }
    public void setBackGround(int backGround){
        this.backGround = backGround;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	
    //根据权重计算出来的资源点列表
    public List<Integer> getResources(){
    	List<Integer> resources = Lists.newArrayList();
    	int min = counts.get(0);
    	int max = counts.get(1);
    	int real = (int)(Math.random()*(max-min+1))+(min);
    	//全部res列表
    	for (int i = 2; i <= max+1; i++)
    		resources.add(i);
    	Collections.shuffle(resources);
    	resources = resources.subList(0, real);
    	return resources;
    }
	
	/////////UserDefine End/////////////
	
}
