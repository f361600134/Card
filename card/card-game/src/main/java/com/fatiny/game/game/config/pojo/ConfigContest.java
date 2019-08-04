package com.fatiny.game.game.config.pojo;

public class ConfigContest {

	private int ID;//ID
	private String name;//名称
	private int type;//比赛类型
	private int group;//小组
	private int maximum;//最大人数

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
    
	public int getGroup(){
        return group;
    }
    public void setGroup(int group){
        this.group = group;
    }
    
	public int getMaximum(){
        return maximum;
    }
    public void setMaximum(int maximum){
        this.maximum = maximum;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}