package com.fatiny.game.game.config.pojo;

public class ConfigTreasureShopRefresh {

	private int ID;//刷新次数
	private int needDiamond;//刷新所需元宝

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
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}