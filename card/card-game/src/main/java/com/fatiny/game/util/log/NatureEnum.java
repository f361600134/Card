package com.fatiny.game.util.log;

/**
 * 资源类型, 资源描述
 * @auth Jeremy
 * @date 2019年6月28日下午3:55:10
 */
public enum NatureEnum {
	
	GM(-1, "GM"),
	Unknown(0, "未声明来源"),
	CheckIn(1, "签到"),
	CheckInAdd(2, "补签"),
	LoginJoinCamp(3, "登录加入阵营奖励"),
	PlayerLevelUp(4, "玩家升级"),
	VipReward(5, "Vip奖励"),
	ExchargeReward(6, "充值赠送元宝"),
	PlayerBuyPower(7, "购买体力"),
	PlayerPubFamousBuy(8, "购买魂玉"),
	//酒馆
	GenNormalCard(2001, "酒馆抽良将"),
	GenFamousCard(2002, "酒馆抽名将"),
	//商店
	ShopBuyTreasure(2011,"秘宝商店购买"),
	ShopBuyBattle(2012,"战功商店购买"),
	ShopBuyJade(2013,"玉璧商店购买"),
	ShopBuyMilitary(2014,"军功商店购买"),
	ShopTreasureRefresh(2015,"秘宝商店刷新"),
	//武将
	HeroActiveStar(2031,"武将升星"),
	HeroRelation(2032,"武将羁绊"),
	HeroActiveTalent(2033,"武将星格激活"),
	HeroObtain(2034,"武将获得"),
	HeroRelive(2035,"武将重生"),
	HeroGodActivite(2036,"激活神将"),
	//背包
	BagUse(2041, "背包使用道具"),
	BagComposeEquip(2042, "背包合成装备"),
	BagComposeHero(2043, "背包合成武将"),
	BagComposeGem(2044, "背包合成宝石"),
	BagDecomposeEquip(2045, "背包分解装备"),
	BagDecomposeHero(2046, "背包分解武将"),
	BagWearEquip(2047, "背包穿戴装备"),
	BagUndressEquip(2048, "背包卸下装备"),
	BagSell(2049, "背包出售物品"),
	BagEquipStrengthen(2050, "背包装备强化"),
	BagSettingGem(2051, "背包镶嵌宝石"),
	BagUnSettingGem(2052, "背包卸下宝石"),
	//皇城&擂台赛
	PVPOfficialFight(2061,"皇城挑战开始"),
	PVPOfficialBuy(2062,"皇城购买次数"),
	PVPContestFight(2063,"擂台赛挑战开始"),
	PVPContestBuy(2064,"擂台次数购买"),
	PVPContestRefresh(2065,"擂台赛刷新对手"),
	PVPOfficialFightEnd(2066,"皇城挑战结束"),
	PVPContestFightEnd(2067,"擂台挑战结束"),
	//祭拜
	AltarGold(2071, "钱币祭祀"),
	AltarEquip(2072, "装备祭祀"),
	AltarGem(2073, "宝石祭祀"),
	AltarGhost(2074, "星魂祭祀"),
	//副本
	StageFight(2081, "关卡副本挑战"),
	StageSweep(2082, "关卡副本扫荡"),
	StageBuyCount(2083, "关卡副本次数购买"),
	StageBox(2084, "关卡副本宝箱"),
	ArmStageFight(2085, "兵器副本挑战"),
	ArmStageFightSweep(2086, "兵器副本扫荡"),
	ArtifactStageFight(2087, "神兵副本挑战"),
	ArtifactStageSweep(2088, "神兵副本扫荡"),
	ArtifactStageBuy(2089, "神兵副本次数购买"),
	GemCopyFight(2090, "宝石副本挑战"),
	GemCopySweep(2091, "宝石副本扫荡"),
	WusCopyFight(2092, "无双副本挑战"),
	WusCopySweep(2093, "无双副本扫荡"),
	SoulCopyFight(2094, "将魂副本挑战"),
	CityCopySearchMap(2095, "攻城拔塞搜索地图"),
	//任务
	Mission(2101, "任务领取奖励"),
	MissionFinish(2102, "任务完成"),
	//活动
	ActivityFC(2121, "战力活动"),
	ActivityGrading(2122, "冲级活动"),
	ActivityGrowth(2123, "成长活动"),
	ActivityServen(2124, "七日活动"),
	ActivityStage(2125, "副本活动"),
	//神兵系统
	ArtifactCreate(2140, "神兵打造"),
	ArtifactUpgrade(2141, "神兵精炼"),
	ArtifactBrake(2142, "神兵突破"),
	//建筑
	MainHallBeginLevelUp(2151, "主城升级"),
	MainHallAccelerateLevelUp(2152,"主城加速升级"),
	MinefieldBeginLevelUp(2153,"矿场升级"),
	MinefieldAccelerateLevelUp(2154,"矿场加速升级"),
	MinefieldProduce(2155,"矿场产出"),
	FolkhousesBeginLevelUp(2156,"民居升级"),
	FolkhousesAccelerateLevelUp(2157,"民居加速升级"),
	FolkhousesProduce(2158,"民居产出"),
	BarracksTechAccelLevelUp(2159,"兵营科技加速升级"),
	BarracksTechBeginLevelUp(2160,"兵营科技升级"),
	BarracksAccelerateLevelUp(2161,"兵营加速升级"),
	BarracksBeginLevelUp(2162,"兵营升级"),
	//邮件
	MailReward(2211, "邮件奖励"),
	MailSend(2212, "邮件发送"),
	MailRead(2213, "邮件查看"),
	//玩家登陆登出
	Login(2221, "玩家登陆"),
	Logout(2222, "玩家登出"),
	;
	
	private int logType;
	private String desc;
	private NatureEnum(int logType, String desc) {
		this.logType = logType;
		this.desc = desc;
	}
	
	public int getLogType() {
		return logType;
	}
	public void setLogType(int logType) {
		this.logType = logType;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	 * 根据日志类型, 获取日志枚举
	 * @param logType
	 * @return  
	 * @return NatureEnum  
	 * @date 2019年7月1日下午1:34:07
	 */
	public static NatureEnum getEnum(int logType) {
		for (NatureEnum  nEnum : NatureEnum.values()) {
			if (nEnum.logType == logType) {
				return nEnum;
			}
		}
		return null;
	}
	
}
