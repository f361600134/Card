package com.fatiny.game.game.config.pojo;

public class ConfigPlayer {

	private int ID;//ID
	private int sex;//性别
	private String bodyImg;//立绘资源
	private String animation;//动画资源
	private String headIcon;//头像资源
	private int initLevel;//初始等级
	private int initVIP;//初始VIP等级
	private int initExp;//初始经验
	private int initCE;//初始战力
	private int initPower;//初始体力
	private int initDiamond;//初始元宝
	private int initGold;//初始金币
	private int initMineral;//初始矿石
	private int rageMax;//主公技能怒气上限

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getSex(){
        return sex;
    }
    public void setSex(int sex){
        this.sex = sex;
    }
    
	public String getBodyImg(){
        return bodyImg;
    }
    public void setBodyImg(String bodyImg){
        this.bodyImg = bodyImg;
    }
    
	public String getAnimation(){
        return animation;
    }
    public void setAnimation(String animation){
        this.animation = animation;
    }
    
	public String getHeadIcon(){
        return headIcon;
    }
    public void setHeadIcon(String headIcon){
        this.headIcon = headIcon;
    }
    
	public int getInitLevel(){
        return initLevel;
    }
    public void setInitLevel(int initLevel){
        this.initLevel = initLevel;
    }
    
	public int getInitVIP(){
        return initVIP;
    }
    public void setInitVIP(int initVIP){
        this.initVIP = initVIP;
    }
    
	public int getInitExp(){
        return initExp;
    }
    public void setInitExp(int initExp){
        this.initExp = initExp;
    }
    
	public int getInitCE(){
        return initCE;
    }
    public void setInitCE(int initCE){
        this.initCE = initCE;
    }
    
	public int getInitPower(){
        return initPower;
    }
    public void setInitPower(int initPower){
        this.initPower = initPower;
    }
    
	public int getInitDiamond(){
        return initDiamond;
    }
    public void setInitDiamond(int initDiamond){
        this.initDiamond = initDiamond;
    }
    
	public int getInitGold(){
        return initGold;
    }
    public void setInitGold(int initGold){
        this.initGold = initGold;
    }
    
	public int getInitMineral(){
        return initMineral;
    }
    public void setInitMineral(int initMineral){
        this.initMineral = initMineral;
    }
    
	public int getRageMax(){
        return rageMax;
    }
    public void setRageMax(int rageMax){
        this.rageMax = rageMax;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}