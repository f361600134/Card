package com.fatiny.game.game.config.pojo;

public class ConfigCityProfit {

	private int ID;//数量
	private int speed;//提升征收速度%
	private int hp;//血量加成%
	private int att;//攻击加成%
	private int def;//物防加成%
	private int apDef;//法防加成%

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    
	public int getHp(){
        return hp;
    }
    public void setHp(int hp){
        this.hp = hp;
    }
    
	public int getAtt(){
        return att;
    }
    public void setAtt(int att){
        this.att = att;
    }
    
	public int getDef(){
        return def;
    }
    public void setDef(int def){
        this.def = def;
    }
    
	public int getApDef(){
        return apDef;
    }
    public void setApDef(int apDef){
        this.apDef = apDef;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}