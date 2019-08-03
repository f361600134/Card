package com.fatiny.game.po;

import com.fatiny.core.data.*;

/**
 * this class file is auto output by com.fatiny.game.util.po.PoPrinter
 * @author Jeremy
 * @see com.fatiny.game.util.po.PoPrinter
 */
public abstract class TbDbPlayerPo extends BasePo {


	/** 角色ID */
	private java.lang.Integer id = null;
	/** 用户名 */
	private java.lang.String userName = null;
	/** 昵称 */
	private java.lang.String nickName = null;
	/** 玩家输入账号 */
	private java.lang.String inputName = "";
	/** 角色类型 */
	private java.lang.Integer roleType = 0;
	/** 渠道Id */
	private java.lang.Integer channel = 0;
	/** 注册时间 */
	private java.util.Date regTime = null;
	/** 最后登陆时间 */
	private java.util.Date lastTime = null;
	/** 当前服务器Id */
	private java.lang.Integer curServerId = 0;
	/** 最原始服务器Id */
	private java.lang.Integer initServerId = 0;
	/** 1=魏;2=蜀;3=吴 */
	private java.lang.Integer camp = 1;
	/** 当前体力 */
	private java.lang.Short curPower = 0;
	/** 体力变化时间 */
	private java.util.Date powerTime = null;
	/** 元宝 */
	private java.lang.Integer diamond = 0;
	/** 金币 */
	private java.lang.Integer gold = 0;
	/** 矿石 */
	private java.lang.Integer mimeral = 0;
	/** 魂玉 */
	private java.lang.Integer pubFamous = 0;
	/** 招募令 */
	private java.lang.Integer pubNormal = 0;
	/** 将魂 */
	private java.lang.Integer shopTreasure = 0;
	/** 刷新令 */
	private java.lang.Integer shopRefresh = 0;
	/** 军功 */
	private java.lang.Integer shopMilitary = 0;
	/** 家族贡献 */
	private java.lang.Integer familyDevote = 0;
	/** 马魂 */
	private java.lang.Integer horseGhost = 0;
	/** 星魂 */
	private java.lang.Integer starGhost = 0;
	/** 战功 */
	private java.lang.Integer shopBattle = 0;
	/** 玉璧 */
	private java.lang.Integer shopJade = 0;
	/** 等级 */
	private java.lang.Short level = 1;
	/** VIP */
	private java.lang.Short vip = 0;
	/** Vip经验 */
	private java.lang.Integer vipExp = 0;
	/** 经验 */
	private java.lang.Integer exp = 0;
	/** 今日精英副本剩余次数 */
	private java.lang.Integer eliteRemainCount = 0;
	/** 今日炼狱副本剩余次数 */
	private java.lang.Integer purgatoryRemainCount = 0;
	/** 炼狱副本重置时间 */
	private java.util.Date purgatoryResetTime = null;
	/** 最近重置时间 */
	private java.util.Date resetTime = null;
	/** 今日购买体力次数 */
	private java.lang.Integer buyPowerCount = 0;
	/** 月卡持续时间 */
	private java.lang.Long monthcardDuration = 0L;
	/** 至尊卡状态标识,永久 */
	private java.lang.Integer supremeCard = 0;
	/** 充值信息,记录首冲配置[1,2,3,4] */
	private java.lang.String exchargeInfo = "";
	/** 邮件领取记录publicIds[1,2,3,4] */
	private java.lang.String emailRecord = "";
	/** 0=未删除;1=删除 */
	private java.lang.Boolean isDel = false;

	/** get 角色ID */
	public java.lang.Integer getId() {
		return id;
	}

	/** set 角色ID */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	/** get 用户名 */
	public java.lang.String getUserName() {
		return userName;
	}

	/** set 用户名 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	/** get 昵称 */
	public java.lang.String getNickName() {
		return nickName;
	}

	/** set 昵称 */
	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	/** get 玩家输入账号 */
	public java.lang.String getInputName() {
		return inputName;
	}

	/** set 玩家输入账号 */
	public void setInputName(java.lang.String inputName) {
		this.inputName = inputName;
	}

	/** get 角色类型 */
	public java.lang.Integer getRoleType() {
		return roleType;
	}

	/** set 角色类型 */
	public void setRoleType(java.lang.Integer roleType) {
		this.roleType = roleType;
	}

	/** get 渠道Id */
	public java.lang.Integer getChannel() {
		return channel;
	}

	/** set 渠道Id */
	public void setChannel(java.lang.Integer channel) {
		this.channel = channel;
	}

	/** get 注册时间 */
	public java.util.Date getRegTime() {
		return regTime;
	}

	/** set 注册时间 */
	public void setRegTime(java.util.Date regTime) {
		this.regTime = regTime;
	}

	/** get 最后登陆时间 */
	public java.util.Date getLastTime() {
		return lastTime;
	}

	/** set 最后登陆时间 */
	public void setLastTime(java.util.Date lastTime) {
		this.lastTime = lastTime;
	}

	/** get 当前服务器Id */
	public java.lang.Integer getCurServerId() {
		return curServerId;
	}

	/** set 当前服务器Id */
	public void setCurServerId(java.lang.Integer curServerId) {
		this.curServerId = curServerId;
	}

	/** get 最原始服务器Id */
	public java.lang.Integer getInitServerId() {
		return initServerId;
	}

	/** set 最原始服务器Id */
	public void setInitServerId(java.lang.Integer initServerId) {
		this.initServerId = initServerId;
	}

	/** get 1=魏;2=蜀;3=吴 */
	public java.lang.Integer getCamp() {
		return camp;
	}

	/** set 1=魏;2=蜀;3=吴 */
	public void setCamp(java.lang.Integer camp) {
		this.camp = camp;
	}

	/** get 当前体力 */
	public java.lang.Short getCurPower() {
		return curPower;
	}

	/** set 当前体力 */
	public void setCurPower(java.lang.Short curPower) {
		this.curPower = curPower;
	}

	public java.lang.Short isCurPower(){
		return curPower;
	}

	/** get 体力变化时间 */
	public java.util.Date getPowerTime() {
		return powerTime;
	}

	/** set 体力变化时间 */
	public void setPowerTime(java.util.Date powerTime) {
		this.powerTime = powerTime;
	}

	/** get 元宝 */
	public java.lang.Integer getDiamond() {
		return diamond;
	}

	/** set 元宝 */
	public void setDiamond(java.lang.Integer diamond) {
		this.diamond = diamond;
	}

	/** get 金币 */
	public java.lang.Integer getGold() {
		return gold;
	}

	/** set 金币 */
	public void setGold(java.lang.Integer gold) {
		this.gold = gold;
	}

	/** get 矿石 */
	public java.lang.Integer getMimeral() {
		return mimeral;
	}

	/** set 矿石 */
	public void setMimeral(java.lang.Integer mimeral) {
		this.mimeral = mimeral;
	}

	/** get 魂玉 */
	public java.lang.Integer getPubFamous() {
		return pubFamous;
	}

	/** set 魂玉 */
	public void setPubFamous(java.lang.Integer pubFamous) {
		this.pubFamous = pubFamous;
	}

	/** get 招募令 */
	public java.lang.Integer getPubNormal() {
		return pubNormal;
	}

	/** set 招募令 */
	public void setPubNormal(java.lang.Integer pubNormal) {
		this.pubNormal = pubNormal;
	}

	/** get 将魂 */
	public java.lang.Integer getShopTreasure() {
		return shopTreasure;
	}

	/** set 将魂 */
	public void setShopTreasure(java.lang.Integer shopTreasure) {
		this.shopTreasure = shopTreasure;
	}

	/** get 刷新令 */
	public java.lang.Integer getShopRefresh() {
		return shopRefresh;
	}

	/** set 刷新令 */
	public void setShopRefresh(java.lang.Integer shopRefresh) {
		this.shopRefresh = shopRefresh;
	}

	/** get 军功 */
	public java.lang.Integer getShopMilitary() {
		return shopMilitary;
	}

	/** set 军功 */
	public void setShopMilitary(java.lang.Integer shopMilitary) {
		this.shopMilitary = shopMilitary;
	}

	/** get 家族贡献 */
	public java.lang.Integer getFamilyDevote() {
		return familyDevote;
	}

	/** set 家族贡献 */
	public void setFamilyDevote(java.lang.Integer familyDevote) {
		this.familyDevote = familyDevote;
	}

	/** get 马魂 */
	public java.lang.Integer getHorseGhost() {
		return horseGhost;
	}

	/** set 马魂 */
	public void setHorseGhost(java.lang.Integer horseGhost) {
		this.horseGhost = horseGhost;
	}

	/** get 星魂 */
	public java.lang.Integer getStarGhost() {
		return starGhost;
	}

	/** set 星魂 */
	public void setStarGhost(java.lang.Integer starGhost) {
		this.starGhost = starGhost;
	}

	/** get 战功 */
	public java.lang.Integer getShopBattle() {
		return shopBattle;
	}

	/** set 战功 */
	public void setShopBattle(java.lang.Integer shopBattle) {
		this.shopBattle = shopBattle;
	}

	/** get 玉璧 */
	public java.lang.Integer getShopJade() {
		return shopJade;
	}

	/** set 玉璧 */
	public void setShopJade(java.lang.Integer shopJade) {
		this.shopJade = shopJade;
	}

	/** get 等级 */
	public java.lang.Short getLevel() {
		return level;
	}

	/** set 等级 */
	public void setLevel(java.lang.Short level) {
		this.level = level;
	}

	public java.lang.Short isLevel(){
		return level;
	}

	/** get VIP */
	public java.lang.Short getVip() {
		return vip;
	}

	/** set VIP */
	public void setVip(java.lang.Short vip) {
		this.vip = vip;
	}

	public java.lang.Short isVip(){
		return vip;
	}

	/** get Vip经验 */
	public java.lang.Integer getVipExp() {
		return vipExp;
	}

	/** set Vip经验 */
	public void setVipExp(java.lang.Integer vipExp) {
		this.vipExp = vipExp;
	}

	/** get 经验 */
	public java.lang.Integer getExp() {
		return exp;
	}

	/** set 经验 */
	public void setExp(java.lang.Integer exp) {
		this.exp = exp;
	}

	/** get 今日精英副本剩余次数 */
	public java.lang.Integer getEliteRemainCount() {
		return eliteRemainCount;
	}

	/** set 今日精英副本剩余次数 */
	public void setEliteRemainCount(java.lang.Integer eliteRemainCount) {
		this.eliteRemainCount = eliteRemainCount;
	}

	/** get 今日炼狱副本剩余次数 */
	public java.lang.Integer getPurgatoryRemainCount() {
		return purgatoryRemainCount;
	}

	/** set 今日炼狱副本剩余次数 */
	public void setPurgatoryRemainCount(java.lang.Integer purgatoryRemainCount) {
		this.purgatoryRemainCount = purgatoryRemainCount;
	}

	/** get 炼狱副本重置时间 */
	public java.util.Date getPurgatoryResetTime() {
		return purgatoryResetTime;
	}

	/** set 炼狱副本重置时间 */
	public void setPurgatoryResetTime(java.util.Date purgatoryResetTime) {
		this.purgatoryResetTime = purgatoryResetTime;
	}

	/** get 最近重置时间 */
	public java.util.Date getResetTime() {
		return resetTime;
	}

	/** set 最近重置时间 */
	public void setResetTime(java.util.Date resetTime) {
		this.resetTime = resetTime;
	}

	/** get 今日购买体力次数 */
	public java.lang.Integer getBuyPowerCount() {
		return buyPowerCount;
	}

	/** set 今日购买体力次数 */
	public void setBuyPowerCount(java.lang.Integer buyPowerCount) {
		this.buyPowerCount = buyPowerCount;
	}

	/** get 月卡持续时间 */
	public java.lang.Long getMonthcardDuration() {
		return monthcardDuration;
	}

	/** set 月卡持续时间 */
	public void setMonthcardDuration(java.lang.Long monthcardDuration) {
		this.monthcardDuration = monthcardDuration;
	}

	/** get 至尊卡状态标识,永久 */
	public java.lang.Integer getSupremeCard() {
		return supremeCard;
	}

	/** set 至尊卡状态标识,永久 */
	public void setSupremeCard(java.lang.Integer supremeCard) {
		this.supremeCard = supremeCard;
	}

	/** get 充值信息,记录首冲配置[1,2,3,4] */
	public java.lang.String getExchargeInfo() {
		return exchargeInfo;
	}

	/** set 充值信息,记录首冲配置[1,2,3,4] */
	public void setExchargeInfo(java.lang.String exchargeInfo) {
		this.exchargeInfo = exchargeInfo;
	}

	/** get 邮件领取记录publicIds[1,2,3,4] */
	public java.lang.String getEmailRecord() {
		return emailRecord;
	}

	/** set 邮件领取记录publicIds[1,2,3,4] */
	public void setEmailRecord(java.lang.String emailRecord) {
		this.emailRecord = emailRecord;
	}

	/** get 0=未删除;1=删除 */
	public java.lang.Boolean getIsDel() {
		return isDel;
	}

	/** set 0=未删除;1=删除 */
	public void setIsDel(java.lang.Boolean isDel) {
		this.isDel = isDel;
	}

	@Override
	public String[] props() {
		return new String[] {"`id`", "`userName`", "`nickName`", "`inputName`", "`roleType`", "`channel`", "`regTime`", "`lastTime`", "`curServerId`", "`initServerId`", "`camp`", "`curPower`", "`powerTime`", "`diamond`", "`gold`", "`mimeral`", "`pubFamous`", "`pubNormal`", "`shopTreasure`", "`shopRefresh`", "`shopMilitary`", "`familyDevote`", "`horseGhost`", "`starGhost`", "`shopBattle`", "`shopJade`", "`level`", "`vip`", "`vipExp`", "`exp`", "`eliteRemainCount`", "`purgatoryRemainCount`", "`purgatoryResetTime`", "`resetTime`", "`buyPowerCount`", "`monthcardDuration`", "`supremeCard`", "`exchargeInfo`", "`emailRecord`", "`isDel`"};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { getId(), getUserName(), getNickName(), getInputName(), getRoleType(), getChannel(), getRegTime(), getLastTime(), getCurServerId(), getInitServerId(), getCamp(), getCurPower(), getPowerTime(), getDiamond(), getGold(), getMimeral(), getPubFamous(), getPubNormal(), getShopTreasure(), getShopRefresh(), getShopMilitary(), getFamilyDevote(), getHorseGhost(), getStarGhost(), getShopBattle(), getShopJade(), getLevel(), getVip(), getVipExp(), getExp(), getEliteRemainCount(), getPurgatoryRemainCount(), getPurgatoryResetTime(), getResetTime(), getBuyPowerCount(), getMonthcardDuration(), getSupremeCard(), getExchargeInfo(), getEmailRecord(), getIsDel() };
	}

	@Override
	public String[] ids() {
		return new String[] {"`id`"};
	}
	@Override
	public Object[] idValues() {
		return new Object[] { id };
	}
}
