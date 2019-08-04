package com.fatiny.game.game.config.pojo;

public class ConfigRecharge {

	private int ID;//ID
	private String name;//名称
	private int type;//类型
	private String descption;//描述
	private String icon;//显示图标
	private int amount;//金额（海外）
	private int baseDiamond;//获得元宝
	private int gainDiamond;//赠送元宝
	private int duration;//卡的时效(天)
	private int vipExp;//充值获得VIP经验

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
    
	public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    
	public String getDescption(){
        return descption;
    }
    public void setDescption(String descption){
        this.descption = descption;
    }
    
	public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    
	public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    
	public int getBaseDiamond(){
        return baseDiamond;
    }
    public void setBaseDiamond(int baseDiamond){
        this.baseDiamond = baseDiamond;
    }
    
	public int getGainDiamond(){
        return gainDiamond;
    }
    public void setGainDiamond(int gainDiamond){
        this.gainDiamond = gainDiamond;
    }
    
	public int getDuration(){
        return duration;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    
	public int getVipExp(){
        return vipExp;
    }
    public void setVipExp(int vipExp){
        this.vipExp = vipExp;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}