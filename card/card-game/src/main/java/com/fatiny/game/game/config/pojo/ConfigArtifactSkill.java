package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigArtifactSkill {

	private int ID;//神兵技能ID
	private String name;//技能名称
	private int condition;//精炼等级激活
	private int belong;//所属神兵
	private int rank;//锻造阶数
	private List<List<Integer>> attrValues;//技能附加属性
	private List<Integer> initialSkillId;//初始技能ID
	private int show;//显示不显示

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
    
	public int getCondition(){
        return condition;
    }
    public void setCondition(int condition){
        this.condition = condition;
    }
    
	public int getBelong(){
        return belong;
    }
    public void setBelong(int belong){
        this.belong = belong;
    }
    
	public int getRank(){
        return rank;
    }
    public void setRank(int rank){
        this.rank = rank;
    }
    
	public List<List<Integer>> getAttrValues(){
        return attrValues;
    }
    public void setAttrValues(List<List<Integer>> attrValues){
        this.attrValues = attrValues;
    }
    
	public List<Integer> getInitialSkillId(){
        return initialSkillId;
    }
    public void setInitialSkillId(List<Integer> initialSkillId){
        this.initialSkillId = initialSkillId;
    }
    
	public int getShow(){
        return show;
    }
    public void setShow(int show){
        this.show = show;
    }
    

	////////////////////// 特殊扩展 //////////////
	public void parse(){
		Map<Integer, Integer> attrValuesTemps = Maps.newHashMap();
    	for(List<Integer> item : this.attrValues)
    	{
    		int c = attrValuesTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		attrValuesTemps.put(item.get(0), c);
    	}
    	attrValuesMap = attrValuesTemps;
    	
    	this.parseExt();
    }
	
	//id_count ID数量
    private Map<Integer, Integer> attrValuesMap = Maps.newHashMap();
    public Map<Integer, Integer> getAttrValuesMap(){
    	return attrValuesMap;
    }
	
	/////////UserDefine Begin///////////
    public void parseExt() {
    }
    
    /**
     * 附加属性
     * 包含品阶之前所有需要加的属性
     */
    private Map<Integer, Integer> attrTotalValues;//技能附加属性
	public Map<Integer, Integer> getAttrTotalValues() {
		return attrTotalValues;
	}
	public void setAttrTotalValues(Map<Integer, Integer> attrValues) {
		this.attrTotalValues = attrValues;
	}
	/////////UserDefine End/////////////
	
}