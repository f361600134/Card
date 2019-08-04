package com.fatiny.game.game.config.pojo;

import java.util.List;

public class ConfigQueue {

	private int ID;//ID和类型
	private String name;//队列名字
	private int type;//类型
	private String desc;//描述
	private int lockLevel;//解锁等级
	private int playerNum;//队列人数
	private List<Integer> queues;//队列
	private List<Integer> addList;//位置
	private int addHP;//属性加成

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
    
	public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    
	public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    
	public int getLockLevel(){
        return lockLevel;
    }
    public void setLockLevel(int lockLevel){
        this.lockLevel = lockLevel;
    }
    
	public int getPlayerNum(){
        return playerNum;
    }
    public void setPlayerNum(int playerNum){
        this.playerNum = playerNum;
    }
    
	public List<Integer> getQueues(){
        return queues;
    }
    public void setQueues(List<Integer> queues){
        this.queues = queues;
    }
    
	public List<Integer> getAddList(){
        return addList;
    }
    public void setAddList(List<Integer> addList){
        this.addList = addList;
    }
    
	public int getAddHP(){
        return addHP;
    }
    public void setAddHP(int addHP){
        this.addHP = addHP;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}