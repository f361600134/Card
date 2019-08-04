package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigGem {

	private int ID;//ID
	private String name;//宝石名
	private int type;//类型
	private String desc;//宝石描述
	private String icon;//图标
	private int quality;//初始品质
	private int level;//初始等级
	private int exp;//初始经验值
	private int needExp;//升级所需经验
	private int nextgemid;//下一级宝石
	private int leftButton;//左按钮
	private int rightButton;//右按钮
	private String leftOnclik;//左按钮跳转
	private String rightOnclik;//右按钮跳转
	private List<List<Integer>> attrs;//加属性值

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
    
	public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    
	public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    
	public int getQuality(){
        return quality;
    }
    public void setQuality(int quality){
        this.quality = quality;
    }
    
	public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
    
	public int getExp(){
        return exp;
    }
    public void setExp(int exp){
        this.exp = exp;
    }
    
	public int getNeedExp(){
        return needExp;
    }
    public void setNeedExp(int needExp){
        this.needExp = needExp;
    }
    
	public int getNextgemid(){
        return nextgemid;
    }
    public void setNextgemid(int nextgemid){
        this.nextgemid = nextgemid;
    }
    
	public int getLeftButton(){
        return leftButton;
    }
    public void setLeftButton(int leftButton){
        this.leftButton = leftButton;
    }
    
	public int getRightButton(){
        return rightButton;
    }
    public void setRightButton(int rightButton){
        this.rightButton = rightButton;
    }
    
	public String getLeftOnclik(){
        return leftOnclik;
    }
    public void setLeftOnclik(String leftOnclik){
        this.leftOnclik = leftOnclik;
    }
    
	public String getRightOnclik(){
        return rightOnclik;
    }
    public void setRightOnclik(String rightOnclik){
        this.rightOnclik = rightOnclik;
    }
    
	public List<List<Integer>> getAttrs(){
        return attrs;
    }
    public void setAttrs(List<List<Integer>> attrs){
        this.attrs = attrs;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> attrsTemps = Maps.newHashMap();
    	for(List<Integer> item : this.attrs)
    	{
    		int c = attrsTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		attrsTemps.put(item.get(0), c);
    	}
    	attrsMap = attrsTemps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> attrsMap = Maps.newHashMap();
    public Map<Integer, Integer> getAttrsMap(){
    	return attrsMap;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}