package com.fatiny.game.game.config.pojo;

public class ConfigHorse {

	private int ID;//序列
	private int level;//主殿等级

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
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