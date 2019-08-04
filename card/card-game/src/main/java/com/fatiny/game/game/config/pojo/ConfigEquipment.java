package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigEquipment {

	private int ID;//装备id
	private String name;//装备名
	private String desc;//描述
	private String icon;//图标
	private int quality;//品质
	private int grade;//品级
	private int subType;//子类型
	private List<List<Integer>> decompose;//分解碎片
	private int gemCount;//宝石孔
	private int awaken;//觉醒
	private int suitId;//套装id
	private int leftButton;//左按钮
	private int rightButton;//右按钮
	private String leftOnclik;//左按钮跳转
	private String rightOnclik;//右按钮跳转
	private List<List<Integer>> attrs;//装备属性

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
    
	public int getGrade(){
        return grade;
    }
    public void setGrade(int grade){
        this.grade = grade;
    }
    
	public int getSubType(){
        return subType;
    }
    public void setSubType(int subType){
        this.subType = subType;
    }
    
	public List<List<Integer>> getDecompose(){
        return decompose;
    }
    public void setDecompose(List<List<Integer>> decompose){
        this.decompose = decompose;
    }
    
	public int getGemCount(){
        return gemCount;
    }
    public void setGemCount(int gemCount){
        this.gemCount = gemCount;
    }
    
	public int getAwaken(){
        return awaken;
    }
    public void setAwaken(int awaken){
        this.awaken = awaken;
    }
    
	public int getSuitId(){
        return suitId;
    }
    public void setSuitId(int suitId){
        this.suitId = suitId;
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
		
		Map<Integer, Integer> decomposeTemps = Maps.newHashMap();
    	for(List<Integer> item : this.decompose)
    	{
    		int c = decomposeTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		decomposeTemps.put(item.get(0), c);
    	}
    	decomposeMap = decomposeTemps;
			    
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
    private Map<Integer, Integer> decomposeMap = Maps.newHashMap();
    public Map<Integer, Integer> getDecomposeMap(){
    	return decomposeMap;
    }
    
    private Map<Integer, Integer> attrsMap = Maps.newHashMap();
    public Map<Integer, Integer> getAttrsMap(){
    	return attrsMap;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}