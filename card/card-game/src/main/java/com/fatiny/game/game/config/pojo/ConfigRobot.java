package com.fatiny.game.game.config.pojo;

import java.util.List;

public class ConfigRobot {

	private int ID;//ID
	private int roleType;//主公性别头像
	private String name;//名字
	private List<Integer> skill;//上阵主公技能ID
	private int level;//等级
	private int zx;//阵型
	private int fc;//战力
	private int camp;//所属国家
	private int officialId;//官职
	private int heroGroupId;//武将组

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getRoleType(){
        return roleType;
    }
    public void setRoleType(int roleType){
        this.roleType = roleType;
    }
    
	public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
	public List<Integer> getSkill(){
        return skill;
    }
    public void setSkill(List<Integer> skill){
        this.skill = skill;
    }
    
	public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
    
	public int getZx(){
        return zx;
    }
    public void setZx(int zx){
        this.zx = zx;
    }
    
	public int getFc(){
        return fc;
    }
    public void setFc(int fc){
        this.fc = fc;
    }
    
	public int getCamp(){
        return camp;
    }
    public void setCamp(int camp){
        this.camp = camp;
    }
    
	public int getOfficialId(){
        return officialId;
    }
    public void setOfficialId(int officialId){
        this.officialId = officialId;
    }
    
	public int getHeroGroupId(){
        return heroGroupId;
    }
    public void setHeroGroupId(int heroGroupId){
        this.heroGroupId = heroGroupId;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}