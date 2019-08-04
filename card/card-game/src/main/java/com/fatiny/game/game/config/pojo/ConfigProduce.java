package com.fatiny.game.game.config.pojo;

public class ConfigProduce {

	private int ID;//索引ID=type *1000+ level
	private int production;//每5分钟产量
	private int capacity;//最大容量
	private int type;//建筑类型（客户端不用）
	private int lv;//等级（客户端不用）
	private int vip;//vip等级

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getProduction(){
        return production;
    }
    public void setProduction(int production){
        this.production = production;
    }
    
	public int getCapacity(){
        return capacity;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    
	public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    
	public int getLv(){
        return lv;
    }
    public void setLv(int lv){
        this.lv = lv;
    }
    
	public int getVip(){
        return vip;
    }
    public void setVip(int vip){
        this.vip = vip;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}