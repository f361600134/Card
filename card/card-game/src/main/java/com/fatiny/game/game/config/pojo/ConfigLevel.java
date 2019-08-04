package com.fatiny.game.game.config.pojo;

public class ConfigLevel {

	private int ID;//ID和等级
	private int needExp;//升级所需经验
	private int normalStageExp;//普通关卡经验
	private int eliteStageExp;//精英关卡经验
	private int superStageExp;//炼狱关卡经验
	private int hallMaxLevel;//主殿最大等级
	private int maxPower;//体力上限
	private int formation;//解锁阵型
	private int addPower;//升级增加体力

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getNeedExp(){
        return needExp;
    }
    public void setNeedExp(int needExp){
        this.needExp = needExp;
    }
    
	public int getNormalStageExp(){
        return normalStageExp;
    }
    public void setNormalStageExp(int normalStageExp){
        this.normalStageExp = normalStageExp;
    }
    
	public int getEliteStageExp(){
        return eliteStageExp;
    }
    public void setEliteStageExp(int eliteStageExp){
        this.eliteStageExp = eliteStageExp;
    }
    
	public int getSuperStageExp(){
        return superStageExp;
    }
    public void setSuperStageExp(int superStageExp){
        this.superStageExp = superStageExp;
    }
    
	public int getHallMaxLevel(){
        return hallMaxLevel;
    }
    public void setHallMaxLevel(int hallMaxLevel){
        this.hallMaxLevel = hallMaxLevel;
    }
    
	public int getMaxPower(){
        return maxPower;
    }
    public void setMaxPower(int maxPower){
        this.maxPower = maxPower;
    }
    
	public int getFormation(){
        return formation;
    }
    public void setFormation(int formation){
        this.formation = formation;
    }
    
	public int getAddPower(){
        return addPower;
    }
    public void setAddPower(int addPower){
        this.addPower = addPower;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}