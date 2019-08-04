package com.fatiny.game.game.config.pojo;

import java.util.List;

public class ConfigHeroMission {

	private int ID;//任务ID
	private String name;//任务名称
	private String icon;//任务图标icon
	private String desc;//任务描述
	private int functionId;//任务跳转
	private int completeType;//完成条件类型
	private List<Integer> completeValue;//完成条件值
	private int completeTotal;//完成进度总值
	private int isOpen;//任务是否开放

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
    
	public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    
	public int getFunctionId(){
        return functionId;
    }
    public void setFunctionId(int functionId){
        this.functionId = functionId;
    }
    
	public int getCompleteType(){
        return completeType;
    }
    public void setCompleteType(int completeType){
        this.completeType = completeType;
    }
    
	public List<Integer> getCompleteValue(){
        return completeValue;
    }
    public void setCompleteValue(List<Integer> completeValue){
        this.completeValue = completeValue;
    }
    
	public int getCompleteTotal(){
        return completeTotal;
    }
    public void setCompleteTotal(int completeTotal){
        this.completeTotal = completeTotal;
    }
    
	public int getIsOpen(){
        return isOpen;
    }
    public void setIsOpen(int isOpen){
        this.isOpen = isOpen;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		
		this.parseExt();
    }
	
	
	/////////UserDefine Begin///////////
	private void parseExt(){
	}
	
	/////////UserDefine End/////////////
	
}
