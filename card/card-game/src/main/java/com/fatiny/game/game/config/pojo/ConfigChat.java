package com.fatiny.game.game.config.pojo;

public class ConfigChat {

	private int ID;//频道ID
	private String name;//频道名字
	private int openlv;//开放等级
	private int speeklv;//发言等级
	private int takltime;//发言时间间隔(s)
	private int IncrementalTime;//触发发言限制后间隔(s)
	private int totalNumber;//缓存聊天数量
	private int preNumber;//每次推送消息数量

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
    
	public int getOpenlv(){
        return openlv;
    }
    public void setOpenlv(int openlv){
        this.openlv = openlv;
    }
    
	public int getSpeeklv(){
        return speeklv;
    }
    public void setSpeeklv(int speeklv){
        this.speeklv = speeklv;
    }
    
	public int getTakltime(){
        return takltime;
    }
    public void setTakltime(int takltime){
        this.takltime = takltime;
    }
    
	public int getIncrementalTime(){
        return IncrementalTime;
    }
    public void setIncrementalTime(int IncrementalTime){
        this.IncrementalTime = IncrementalTime;
    }
    
	public int getTotalNumber(){
        return totalNumber;
    }
    public void setTotalNumber(int totalNumber){
        this.totalNumber = totalNumber;
    }
    
	public int getPreNumber(){
        return preNumber;
    }
    public void setPreNumber(int preNumber){
        this.preNumber = preNumber;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}