package com.fatiny.game.game.config.pojo;

public class ConfigPrivilege {

	private int ID;//特权id]
	private String description;//VIP功能描述
	private int type;//数值类型
	private int open;//是否开放
	private int reset;//每日重置
	private int show;//是否显示

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    
	public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    
	public int getOpen(){
        return open;
    }
    public void setOpen(int open){
        this.open = open;
    }
    
	public int getReset(){
        return reset;
    }
    public void setReset(int reset){
        this.reset = reset;
    }
    
	public int getShow(){
        return show;
    }
    public void setShow(int show){
        this.show = show;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}