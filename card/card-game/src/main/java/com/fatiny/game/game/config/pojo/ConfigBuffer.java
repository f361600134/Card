package com.fatiny.game.game.config.pojo;

import java.util.List;

public class ConfigBuffer {

	private int ID;//bufferId
	private String skillEffect;//技能特效
	private String tips;//战斗飘字
	private String desc;//BUFF描述
	private int buffType;//类型
	private int severClient;//服务器用
	private int triggerCondition;//触发条件
	private int functionType;//类型
	private int triggerRate;//触发概率%
	private int effectType;//属性生效类型
	private float bloodValue;//血线值%
	private List<Integer> composeHeros;//触发武将
	private float addBuffer;//关联BUFFid
	private int camp;//阵营
	private List<Integer> targetSelect;//目标选取
	private int targetNum;//目标数量
	private float duration;//持续时间
	private int jumpCd;//跳动间隔
	private int triggerNum;//可触发次数
	private int overlayNum;//可叠加层数
	private int propertyEffectType;//增减属性
	private List<List<Integer>> attValues;//自身属性
	private int deBuff;//负面效果
	private List<Integer> shieldDeDam;//护盾减伤
	private float damScale;//伤害系数
	private List<List<Integer>> extraDam;//额外伤害
	private String icon;//技能图标

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public String getSkillEffect(){
        return skillEffect;
    }
    public void setSkillEffect(String skillEffect){
        this.skillEffect = skillEffect;
    }
    
	public String getTips(){
        return tips;
    }
    public void setTips(String tips){
        this.tips = tips;
    }
    
	public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    
	public int getBuffType(){
        return buffType;
    }
    public void setBuffType(int buffType){
        this.buffType = buffType;
    }
    
	public int getSeverClient(){
        return severClient;
    }
    public void setSeverClient(int severClient){
        this.severClient = severClient;
    }
    
	public int getTriggerCondition(){
        return triggerCondition;
    }
    public void setTriggerCondition(int triggerCondition){
        this.triggerCondition = triggerCondition;
    }
    
	public int getFunctionType(){
        return functionType;
    }
    public void setFunctionType(int functionType){
        this.functionType = functionType;
    }
    
	public int getTriggerRate(){
        return triggerRate;
    }
    public void setTriggerRate(int triggerRate){
        this.triggerRate = triggerRate;
    }
    
	public int getEffectType(){
        return effectType;
    }
    public void setEffectType(int effectType){
        this.effectType = effectType;
    }
    
	public float getBloodValue(){
        return bloodValue;
    }
    public void setBloodValue(float bloodValue){
        this.bloodValue = bloodValue;
    }
    
	public List<Integer> getComposeHeros(){
        return composeHeros;
    }
    public void setComposeHeros(List<Integer> composeHeros){
        this.composeHeros = composeHeros;
    }
    
	public float getAddBuffer(){
        return addBuffer;
    }
    public void setAddBuffer(float addBuffer){
        this.addBuffer = addBuffer;
    }
    
	public int getCamp(){
        return camp;
    }
    public void setCamp(int camp){
        this.camp = camp;
    }
    
	public List<Integer> getTargetSelect(){
        return targetSelect;
    }
    public void setTargetSelect(List<Integer> targetSelect){
        this.targetSelect = targetSelect;
    }
    
	public int getTargetNum(){
        return targetNum;
    }
    public void setTargetNum(int targetNum){
        this.targetNum = targetNum;
    }
    
	public float getDuration(){
        return duration;
    }
    public void setDuration(float duration){
        this.duration = duration;
    }
    
	public int getJumpCd(){
        return jumpCd;
    }
    public void setJumpCd(int jumpCd){
        this.jumpCd = jumpCd;
    }
    
	public int getTriggerNum(){
        return triggerNum;
    }
    public void setTriggerNum(int triggerNum){
        this.triggerNum = triggerNum;
    }
    
	public int getOverlayNum(){
        return overlayNum;
    }
    public void setOverlayNum(int overlayNum){
        this.overlayNum = overlayNum;
    }
    
	public int getPropertyEffectType(){
        return propertyEffectType;
    }
    public void setPropertyEffectType(int propertyEffectType){
        this.propertyEffectType = propertyEffectType;
    }
    
	public List<List<Integer>> getAttValues(){
        return attValues;
    }
    public void setAttValues(List<List<Integer>> attValues){
        this.attValues = attValues;
    }
    
	public int getDeBuff(){
        return deBuff;
    }
    public void setDeBuff(int deBuff){
        this.deBuff = deBuff;
    }
    
	public List<Integer> getShieldDeDam(){
        return shieldDeDam;
    }
    public void setShieldDeDam(List<Integer> shieldDeDam){
        this.shieldDeDam = shieldDeDam;
    }
    
	public float getDamScale(){
        return damScale;
    }
    public void setDamScale(float damScale){
        this.damScale = damScale;
    }
    
	public List<List<Integer>> getExtraDam(){
        return extraDam;
    }
    public void setExtraDam(List<List<Integer>> extraDam){
        this.extraDam = extraDam;
    }
    
	public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}