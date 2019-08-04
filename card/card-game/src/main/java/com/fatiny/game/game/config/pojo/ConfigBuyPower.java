package com.fatiny.game.game.config.pojo;

public class ConfigBuyPower {

	private int ID;//ID和购买次数
	private int needDiamond;//购买所需元宝
	private int power;//增加的体力值

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getNeedDiamond(){
        return needDiamond;
    }
    public void setNeedDiamond(int needDiamond){
        this.needDiamond = needDiamond;
    }
    
	public int getPower(){
        return power;
    }
    public void setPower(int power){
        this.power = power;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}