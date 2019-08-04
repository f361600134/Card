package com.fatiny.game.game.config.pojo;

public class ConfigCitySearch {

	private int ID;//搜索次数
	private int needGold;//铜币消耗

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getNeedGold(){
        return needGold;
    }
    public void setNeedGold(int needGold){
        this.needGold = needGold;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}