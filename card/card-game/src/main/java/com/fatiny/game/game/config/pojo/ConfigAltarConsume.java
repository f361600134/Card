package com.fatiny.game.game.config.pojo;

public class ConfigAltarConsume {

	private int ID;//购买次数
	private int goldConsume;//抽金币消耗元宝
	private int equipConsume;//抽装备消耗元宝
	private int ghostConsume;//抽星魂消耗元宝
	private int diamondConsume;//抽宝石消耗元宝

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getGoldConsume(){
        return goldConsume;
    }
    public void setGoldConsume(int goldConsume){
        this.goldConsume = goldConsume;
    }
    
	public int getEquipConsume(){
        return equipConsume;
    }
    public void setEquipConsume(int equipConsume){
        this.equipConsume = equipConsume;
    }
    
	public int getGhostConsume(){
        return ghostConsume;
    }
    public void setGhostConsume(int ghostConsume){
        this.ghostConsume = ghostConsume;
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