package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigChapter {

	private int ID;//章节id
	private String name;//章节名
	private String buildImage;//建筑图片
	private String sceneImage;//关卡场景
	private String battleImages;//战斗场景列表
	private String battleImages1;//战斗场景近景
	private int level;//难度
	private int needLevel;//所需等级
	private int needStage;//先要通关副本
	private int needStar;//奖励所需星
	private List<List<Integer>> bonusId;//奖励id
	private String effectName;//特效名
	private List<Integer> stageData;//关卡
	private int plot;//触发剧情

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
    
	public String getBuildImage(){
        return buildImage;
    }
    public void setBuildImage(String buildImage){
        this.buildImage = buildImage;
    }
    
	public String getSceneImage(){
        return sceneImage;
    }
    public void setSceneImage(String sceneImage){
        this.sceneImage = sceneImage;
    }
    
	public String getBattleImages(){
        return battleImages;
    }
    public void setBattleImages(String battleImages){
        this.battleImages = battleImages;
    }
    
	public String getBattleImages1(){
        return battleImages1;
    }
    public void setBattleImages1(String battleImages1){
        this.battleImages1 = battleImages1;
    }
    
	public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
    
	public int getNeedLevel(){
        return needLevel;
    }
    public void setNeedLevel(int needLevel){
        this.needLevel = needLevel;
    }
    
	public int getNeedStage(){
        return needStage;
    }
    public void setNeedStage(int needStage){
        this.needStage = needStage;
    }
    
	public int getNeedStar(){
        return needStar;
    }
    public void setNeedStar(int needStar){
        this.needStar = needStar;
    }
    
	public List<List<Integer>> getBonusId(){
        return bonusId;
    }
    public void setBonusId(List<List<Integer>> bonusId){
        this.bonusId = bonusId;
    }
    
	public String getEffectName(){
        return effectName;
    }
    public void setEffectName(String effectName){
        this.effectName = effectName;
    }
    
	public List<Integer> getStageData(){
        return stageData;
    }
    public void setStageData(List<Integer> stageData){
        this.stageData = stageData;
    }
    
	public int getPlot(){
        return plot;
    }
    public void setPlot(int plot){
        this.plot = plot;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> bonusIdTemps = Maps.newHashMap();
    	for(List<Integer> item : this.bonusId)
    	{
    		int c = bonusIdTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		bonusIdTemps.put(item.get(0), c);
    	}
    	bonusIdMap = bonusIdTemps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> bonusIdMap = Maps.newHashMap();
    public Map<Integer, Integer> getBonusIdMap(){
    	return bonusIdMap;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}