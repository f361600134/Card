package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigStage {

	private int ID;//关卡id
	private int ChapterID;//章节id
	private int isBoss;//是否为boss关
	private int heroId;//站立武将id
	private String name;//关卡名
	private String desc;//描述
	private int maxFightCount;//最大挑战次数
	private int level;//难度等级
	private int needPower;//所需体力
	private int plotId;//剧情id
	private int dropId;//掉落id
	private List<Integer> showItems;//显示物品列表
	private List<Integer> monsterGroups;//怪物组列表
	private List<Float> XY;//坐标
	private int dir;//朝向
	private List<List<Integer>> plot;//触发剧情
	private List<List<Integer>> firstWinReward;//首次通关必掉

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getChapterID(){
        return ChapterID;
    }
    public void setChapterID(int ChapterID){
        this.ChapterID = ChapterID;
    }
    
	public int getIsBoss(){
        return isBoss;
    }
    public void setIsBoss(int isBoss){
        this.isBoss = isBoss;
    }
    
	public int getHeroId(){
        return heroId;
    }
    public void setHeroId(int heroId){
        this.heroId = heroId;
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
    
	public int getMaxFightCount(){
        return maxFightCount;
    }
    public void setMaxFightCount(int maxFightCount){
        this.maxFightCount = maxFightCount;
    }
    
	public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
    
	public int getNeedPower(){
        return needPower;
    }
    public void setNeedPower(int needPower){
        this.needPower = needPower;
    }
    
	public int getPlotId(){
        return plotId;
    }
    public void setPlotId(int plotId){
        this.plotId = plotId;
    }
    
	public int getDropId(){
        return dropId;
    }
    public void setDropId(int dropId){
        this.dropId = dropId;
    }
    
	public List<Integer> getShowItems(){
        return showItems;
    }
    public void setShowItems(List<Integer> showItems){
        this.showItems = showItems;
    }
    
	public List<Integer> getMonsterGroups(){
        return monsterGroups;
    }
    public void setMonsterGroups(List<Integer> monsterGroups){
        this.monsterGroups = monsterGroups;
    }
    
	public List<Float> getXY(){
        return XY;
    }
    public void setXY(List<Float> XY){
        this.XY = XY;
    }
    
	public int getDir(){
        return dir;
    }
    public void setDir(int dir){
        this.dir = dir;
    }
    
	public List<List<Integer>> getPlot(){
        return plot;
    }
    public void setPlot(List<List<Integer>> plot){
        this.plot = plot;
    }
    
	public List<List<Integer>> getFirstWinReward(){
        return firstWinReward;
    }
    public void setFirstWinReward(List<List<Integer>> firstWinReward){
        this.firstWinReward = firstWinReward;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> firstWinRewardTemps = Maps.newHashMap();
    	for(List<Integer> item : this.firstWinReward)
    	{
    		int c = firstWinRewardTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		firstWinRewardTemps.put(item.get(0), c);
    	}
    	firstWinRewardMap = firstWinRewardTemps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> firstWinRewardMap = Maps.newHashMap();
    public Map<Integer, Integer> getFirstWinRewardMap(){
    	return firstWinRewardMap;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}