package com.fatiny.game.game.config.pojo;

public class ConfigTreasureShop {

	private int ID;//序号
	private int itemId;//物品ID
	private String name;//名称
	private int count;//数量
	private int currency;//购买的货币类型
	private int amount;//购买的货币数量
	private int weight;//权重
	private int upLimit;//此物品出现的上限值
	private int sale;//是否出售

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getItemId(){
        return itemId;
    }
    public void setItemId(int itemId){
        this.itemId = itemId;
    }
    
	public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
	public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count = count;
    }
    
	public int getCurrency(){
        return currency;
    }
    public void setCurrency(int currency){
        this.currency = currency;
    }
    
	public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    
	public int getWeight(){
        return weight;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    
	public int getUpLimit(){
        return upLimit;
    }
    public void setUpLimit(int upLimit){
        this.upLimit = upLimit;
    }
    
	public int getSale(){
        return sale;
    }
    public void setSale(int sale){
        this.sale = sale;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}