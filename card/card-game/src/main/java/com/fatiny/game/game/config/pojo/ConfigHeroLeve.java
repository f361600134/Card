package com.fatiny.game.game.config.pojo;

public class ConfigHeroLeve {

	private int ID;//武将id
	private int position;//武将位置
	private int nature;//资质
	private float HP;//兵力
	private float atk;//攻击
	private int speed;//攻速
	private int rage;//怒气
	private float adDef;//物防
	private float apDef;//法防
	private int hit;//命中
	private int dodge;//闪避
	private int crit;//暴击
	private int deCrit;//韧性
	private int parry;//格挡
	private int deParry;//破击
	private int deDam;//减伤
	private int addDam;//必杀
	private int deCritValue;//强韧
	private int care;//疗效

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public int getPosition(){
        return position;
    }
    public void setPosition(int position){
        this.position = position;
    }
    
	public int getNature(){
        return nature;
    }
    public void setNature(int nature){
        this.nature = nature;
    }
    
	public float getHP(){
        return HP;
    }
    public void setHP(float HP){
        this.HP = HP;
    }
    
	public float getAtk(){
        return atk;
    }
    public void setAtk(float atk){
        this.atk = atk;
    }
    
	public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    
	public int getRage(){
        return rage;
    }
    public void setRage(int rage){
        this.rage = rage;
    }
    
	public float getAdDef(){
        return adDef;
    }
    public void setAdDef(float adDef){
        this.adDef = adDef;
    }
    
	public float getApDef(){
        return apDef;
    }
    public void setApDef(float apDef){
        this.apDef = apDef;
    }
    
	public int getHit(){
        return hit;
    }
    public void setHit(int hit){
        this.hit = hit;
    }
    
	public int getDodge(){
        return dodge;
    }
    public void setDodge(int dodge){
        this.dodge = dodge;
    }
    
	public int getCrit(){
        return crit;
    }
    public void setCrit(int crit){
        this.crit = crit;
    }
    
	public int getDeCrit(){
        return deCrit;
    }
    public void setDeCrit(int deCrit){
        this.deCrit = deCrit;
    }
    
	public int getParry(){
        return parry;
    }
    public void setParry(int parry){
        this.parry = parry;
    }
    
	public int getDeParry(){
        return deParry;
    }
    public void setDeParry(int deParry){
        this.deParry = deParry;
    }
    
	public int getDeDam(){
        return deDam;
    }
    public void setDeDam(int deDam){
        this.deDam = deDam;
    }
    
	public int getAddDam(){
        return addDam;
    }
    public void setAddDam(int addDam){
        this.addDam = addDam;
    }
    
	public int getDeCritValue(){
        return deCritValue;
    }
    public void setDeCritValue(int deCritValue){
        this.deCritValue = deCritValue;
    }
    
	public int getCare(){
        return care;
    }
    public void setCare(int care){
        this.care = care;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
    }
	
	
	/////////UserDefine Begin///////////
	/////////UserDefine End/////////////
	
}