package com.fatiny.game.game.config.pojo;

import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.common.collect.Maps;

public class ConfigProp {

	private int ID;//ID
	private String name;//物品名称
	private int uiLabel;//ui标签
	private int propType;//类型
	private int sort;//排序
	private String desc;//物品描述
	private String icon;//物品图标
	private int quality;//品质
	private int canSell;//是否可出售
	private int sellPrice;//出售价格
	private int maxCount;//最大叠加数
	private int composeNeedNum;//合成所需数量
	private int composeTarget;//合成目标
	private List<List<Integer>> randRange;//随机数量
	private List<List<Integer>> boxReward;//宝箱投放物品
	private int leftButton;//左按钮
	private int rightButton;//右按钮
	private List<List<Integer>> leftOnclik;//左按钮跳转
	private List<List<Integer>> rightOnclik;//右按钮跳转

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
    
	public int getUiLabel(){
        return uiLabel;
    }
    public void setUiLabel(int uiLabel){
        this.uiLabel = uiLabel;
    }
    
	public int getPropType(){
        return propType;
    }
    public void setPropType(int propType){
        this.propType = propType;
    }
    
	public int getSort(){
        return sort;
    }
    public void setSort(int sort){
        this.sort = sort;
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
    
	public int getCanSell(){
        return canSell;
    }
    public void setCanSell(int canSell){
        this.canSell = canSell;
    }
    
	public int getSellPrice(){
        return sellPrice;
    }
    public void setSellPrice(int sellPrice){
        this.sellPrice = sellPrice;
    }
    
	public int getMaxCount(){
        return maxCount;
    }
    public void setMaxCount(int maxCount){
        this.maxCount = maxCount;
    }
    
	public int getComposeNeedNum(){
        return composeNeedNum;
    }
    public void setComposeNeedNum(int composeNeedNum){
        this.composeNeedNum = composeNeedNum;
    }
    
	public int getComposeTarget(){
        return composeTarget;
    }
    public void setComposeTarget(int composeTarget){
        this.composeTarget = composeTarget;
    }
    
	public List<List<Integer>> getRandRange(){
        return randRange;
    }
    public void setRandRange(List<List<Integer>> randRange){
        this.randRange = randRange;
    }
    
	public List<List<Integer>> getBoxReward(){
        return boxReward;
    }
    public void setBoxReward(List<List<Integer>> boxReward){
        this.boxReward = boxReward;
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
    
	public List<List<Integer>> getLeftOnclik(){
        return leftOnclik;
    }
    public void setLeftOnclik(List<List<Integer>> leftOnclik){
        this.leftOnclik = leftOnclik;
    }
    
	public List<List<Integer>> getRightOnclik(){
        return rightOnclik;
    }
    public void setRightOnclik(List<List<Integer>> rightOnclik){
        this.rightOnclik = rightOnclik;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		TreeMap<Integer, Integer> boxRewardTemps = Maps.newTreeMap();
    	int boxRewardTempRate = 0;
    	for(List<Integer> item : this.boxReward)
    	{
    		boxRewardTempRate += item.get(1);
    		boxRewardTemps.put(boxRewardTempRate, item.get(0));
    	}
    	boxRewardMax = boxRewardTempRate;
    	boxRewardTrees = boxRewardTemps;
			    
		
    }
	
	//id_rate 权重随机
	private int boxRewardMax;
    private TreeMap<Integer, Integer> boxRewardTrees = Maps.newTreeMap();
    public int getBoxRewardByRate(){
    	int rate = (int)(Math.random()*boxRewardMax)+1;
    	Entry<Integer, Integer> entry = boxRewardTrees.ceilingEntry(rate);
    	if(entry==null)
    		return 0;
    	return entry.getValue();
    }
    
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}