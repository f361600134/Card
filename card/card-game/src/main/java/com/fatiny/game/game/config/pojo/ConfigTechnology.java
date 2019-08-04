package com.fatiny.game.game.config.pojo;

public class ConfigTechnology {

	private int ID;//索引
	private String name;//名称
	private String icon;//科技icon
	private int openBarrackLv;//开放兵营等级
	private int position;//位置

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
	public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    
	public int getOpenBarrackLv(){
        return openBarrackLv;
    }
    public void setOpenBarrackLv(int openBarrackLv){
        this.openBarrackLv = openBarrackLv;
    }
    
	public int getPosition(){
        return position;
    }
    public void setPosition(int position){
        this.position = position;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}