package com.fatiny.game.game.config.pojo;

public class ConfigArtifactBuyCount {

	private int ID;//次数
	private int needIngot;//所需元宝

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getNeedIngot(){
        return needIngot;
    }
    public void setNeedIngot(int needIngot){
        this.needIngot = needIngot;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}