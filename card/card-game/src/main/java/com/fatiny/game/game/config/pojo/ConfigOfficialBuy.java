package com.fatiny.game.game.config.pojo;

public class ConfigOfficialBuy {

	private int ID;//购买次数
	private int diamondConsume;//消耗元宝

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getDiamondConsume(){
        return diamondConsume;
    }
    public void setDiamondConsume(int diamondConsume){
        this.diamondConsume = diamondConsume;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}