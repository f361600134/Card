package com.fatiny.game.game.config.pojo;

public class ConfigSoulBoss {

	private int ID;//ID
	private int boss;//bossid
	private int groupId;//怪物组
	private int level;//转生等级

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getBoss(){
        return boss;
    }
    public void setBoss(int boss){
        this.boss = boss;
    }
    
	public int getGroupId(){
        return groupId;
    }
    public void setGroupId(int groupId){
        this.groupId = groupId;
    }
    
	public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}