package com.fatiny.game.game.config.pojo;

public class ConfigHall {

	private int ID;//序列
	private int level;//主殿等级
	private int skill;//解锁主公技能
	private int battleskill;//解锁上阵技能数

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
    
	public int getSkill(){
        return skill;
    }
    public void setSkill(int skill){
        this.skill = skill;
    }
    
	public int getBattleskill(){
        return battleskill;
    }
    public void setBattleskill(int battleskill){
        this.battleskill = battleskill;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}