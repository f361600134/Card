package com.fatiny.game.game.config.pojo;

public class ConfigBulidingUpgrade {

	private int ID;//索引ID=type *1000+ level
	private int needIron;//所需铁矿
	private int duration;//持续时间
	private int type;//建筑类型（客户端不用）
	private int level;//等级（客户端不用）

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getNeedIron(){
        return needIron;
    }
    public void setNeedIron(int needIron){
        this.needIron = needIron;
    }
    
	public int getDuration(){
        return duration;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    
	public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    
	public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}