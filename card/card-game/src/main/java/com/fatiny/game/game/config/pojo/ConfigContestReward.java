package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigContestReward {

	private int ID;//ID
	private int ranking;//排名段
	private int type;//榜位类型
	private List<List<Integer>> rankingAward;//排名奖励
	private List<List<Integer>> admissionAward;//入围奖

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getRanking(){
        return ranking;
    }
    public void setRanking(int ranking){
        this.ranking = ranking;
    }
    
	public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    
	public List<List<Integer>> getRankingAward(){
        return rankingAward;
    }
    public void setRankingAward(List<List<Integer>> rankingAward){
        this.rankingAward = rankingAward;
    }
    
	public List<List<Integer>> getAdmissionAward(){
        return admissionAward;
    }
    public void setAdmissionAward(List<List<Integer>> admissionAward){
        this.admissionAward = admissionAward;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> rankingAwardTemps = Maps.newHashMap();
    	for(List<Integer> item : this.rankingAward)
    	{
    		int c = rankingAwardTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		rankingAwardTemps.put(item.get(0), c);
    	}
    	rankingAwardMap = rankingAwardTemps;
			    
		Map<Integer, Integer> admissionAwardTemps = Maps.newHashMap();
    	for(List<Integer> item : this.admissionAward)
    	{
    		int c = admissionAwardTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		admissionAwardTemps.put(item.get(0), c);
    	}
    	admissionAwardMap = admissionAwardTemps;
			    
    }
	
	//id_count ID数量
    private Map<Integer, Integer> rankingAwardMap = Maps.newHashMap();
    public Map<Integer, Integer> getRankingAwardMap(){
    	return rankingAwardMap;
    }
    
    private Map<Integer, Integer> admissionAwardMap = Maps.newHashMap();
    public Map<Integer, Integer> getAdmissionAwardMap(){
    	return admissionAwardMap;
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}