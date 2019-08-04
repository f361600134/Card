package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Set;

public class ConfigBuliding {

	private int ID;//索引
	private String name;//名字
	private int type;//类型
	private List<List<Integer>> activeCondition;//激活条件
	private List<Integer> menus;//二级菜单
	private String desc;//描述
	private List<Integer> managerBuildings;//管理建筑
	private String positionXY;//坐标
	private String res;//资源路径
	private String icon;//icon
	private String effect;//effect
	private String effectLevelUp;//effectLevelUp
	private String tips;//tips

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
    
	public List<List<Integer>> getActiveCondition(){
        return activeCondition;
    }
    public void setActiveCondition(List<List<Integer>> activeCondition){
        this.activeCondition = activeCondition;
    }
    
	public List<Integer> getMenus(){
        return menus;
    }
    public void setMenus(List<Integer> menus){
        this.menus = menus;
    }
    
	public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    
	public List<Integer> getManagerBuildings(){
        return managerBuildings;
    }
    public void setManagerBuildings(List<Integer> managerBuildings){
        this.managerBuildings = managerBuildings;
    }
    
	public String getPositionXY(){
        return positionXY;
    }
    public void setPositionXY(String positionXY){
        this.positionXY = positionXY;
    }
    
	public String getRes(){
        return res;
    }
    public void setRes(String res){
        this.res = res;
    }
    
	public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    
	public String getEffect(){
        return effect;
    }
    public void setEffect(String effect){
        this.effect = effect;
    }
    
	public String getEffectLevelUp(){
        return effectLevelUp;
    }
    public void setEffectLevelUp(String effectLevelUp){
        this.effectLevelUp = effectLevelUp;
    }
    
	public String getTips(){
        return tips;
    }
    public void setTips(String tips){
        this.tips = tips;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	
	public static final int LevelCondition = 1;
	public static final int StageCondition = 2;
	public static final int VIPCondition = 3;
	
	public boolean canUnlockWhenLevelUp(int levelFrom, int levelTo) {
		if(this.activeCondition.isEmpty())
			return false;
		for(List<Integer> condition : this.activeCondition)
		{
			if(condition.get(0)==LevelCondition)
				return (levelFrom < condition.get(1) && condition.get(1) <= levelTo);
		}
		return false;
	}
	
	public boolean isLevelOK(int level) {
		if(this.activeCondition.isEmpty())
			return true;
		for(List<Integer> condition : this.activeCondition)
		{
			if(condition.get(0)==LevelCondition)
				return (condition.get(1) <= level);
		}
		return true;
	}
	
	public boolean canUnlockWhenStagePass(int stageId) {
		if(this.activeCondition.isEmpty())
			return false;
		for(List<Integer> condition : this.activeCondition)
		{
			if(condition.get(0)==StageCondition)
				return (condition.get(2) == stageId);
		}
		return false;
	}
	
	public boolean isStagePassOK(Set<Integer> passStageIds) {
		if(this.activeCondition.isEmpty())
			return true;
		for(List<Integer> condition : this.activeCondition)
		{
			if(condition.get(0)==StageCondition)
				return passStageIds!=null && passStageIds.contains(condition.get(2));
		}
		return true;
	}
	
	public boolean canUnlockWhenVipUp(int vipFrom, int vipTo) {
		if(this.activeCondition.isEmpty())
			return false;
		for(List<Integer> condition : this.activeCondition)
		{
			if(condition.get(0)==VIPCondition)
				return (vipFrom < condition.get(1) && condition.get(1) <= vipTo);
		}
		return false;
	}
	
	public boolean isVipOK(int vip) {
		if(this.activeCondition.isEmpty())
			return true;
		for(List<Integer> condition : this.activeCondition)
		{
			if(condition.get(0)==VIPCondition)
				return (condition.get(1) <= vip);
		}
		return true;
	}
	
	/////////UserDefine End/////////////
	
}
