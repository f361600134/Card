package com.fatiny.game.game.module.common.constant;

public class Config {

//	public static String AppPath; // 应用路径
//	public static String ResourcePath; // 资源路径
	public static String JsonConfigPath = "src/main/resources/config/"; // Json配置路径
//	public static String CommonConfigPath; // Common配置路径
//	public static String localConfigPath; // 本地服务配置路径:local dev
//
//	public static final int ServerStatus_STARTING = 0; // 启动中
//	public static final int ServerStatus_RUNNING = 1; // 运行中
//	public static final int ServerStatus_MAINTENANCE = 2; // 维护中
//	public static final int ServerStatus_CLOSING = 3; // 关闭中
//
//	public static int ServerId;
//	public static int ServerGroup;
//	public static int RedisBbInfoLifetime = 172800; // second 2days
//
//	public static boolean OpenGMFromClient = false; // 对客户端开启GM
//
//	public static String LogTopic;
//	
	public static final int IdNullGood = 99999; // 空物品
//
//	public static final int IdGoodDiamond = 101; // 元宝
//	public static final int IdGoodMimeral = 103; // 矿石
//	public static final int IdGoodGold = 104; // 铜币
//	public static final int IdGoodPower = 105; // 体力
//	public static final int IdGoodPubFamous = 106; // 魂玉
//	public static final int IdGoodPubNormal = 107; // 招募令
//	public static final int IdGoodShopRefresh = 110; // 刷新令
//	public static final int IdGoodShopTreasure = 111; // 将魂
//	public static final int IdGoodShopMilitary = 112; // 军功
//	public static final int IdGoodFamilyDevote = 113; // 家族贡献
//	public static final int IdGoodHorseGhost = 114; // 马魂
//	public static final int IdGoodStarGhost = 115; // 星魂
//	public static final int IdGoodShopBattle = 116; // 战功
//	public static final int IdGoodShopJade = 117; // 玉璧
//	
//	public static final int IdPropertyHp = 201; // 兵力
//	public static final int IdPropertyAtk = 202; // 攻击
//	public static final int IdPropertySpeed = 203; // 攻速
//	public static final int IdPropertyRage = 204; // 聚气
//	public static final int IdPropertyAdDef = 205; // 物防
//	public static final int IdPropertyApDef = 206; // 法防
//
//	public static final int IdPropertyHit = 207; // 命中（率）
//	public static final int IdPropertyDodge = 208; // 闪躲（率）
//	public static final int IdPropertyCrit = 209; // 暴击（率）
//	public static final int IdPropertyDeCrit = 210; // 韧性（率）
//	public static final int IdPropertyParry = 211; // 格挡（率）
//	public static final int IdPropertyDeParry = 212; // 破击（率）
//
//	public static final int IdPropertyDeDam = 213; // 减伤
//	public static final int IdPropertyAddDam = 214; // 必杀
//	public static final int IdPropertyDeCritValue = 215;// 强韧
//	public static final int IdPropertyCare = 216; // 疗效
//	public static final int IdPropertyPlusDam = 217; // 附伤
//	public static final int IdPropertyPlusDamRate = 218; // 附伤系数
//
//	public static final int IdPropertyDeDamRate = 219; // 减伤系数
//	public static final int IdPropertyCareRate = 220; // 疗效系数
//	public static final int IdPropertyRageSpd = 221; // 聚气速度
//
//	public static final int IdPropertyHpPercent = 231; // 兵力%
//	public static final int IdPropertyAtkPercent = 232; // 攻击%
//	public static final int IdPropertySpeedPercent = 233; // 攻速%
//
//	public static final int IdPropertyRagePercent = 234; // 聚气%
//	public static final int IdPropertyAdDefPercent = 235; // 物防%
//	public static final int IdPropertyApDefPercent = 236; // 法防%
//	public static final int IdPropertyHitPercent = 237; // 命中（率）%
//	public static final int IdPropertyDodgePercent = 238; // 闪躲（率）%
//	public static final int IdPropertyCritPercent = 239; // 暴击（率）%
//
//	public static final int IdPropertyDeCritPercent = 240; // 韧性（率）%
//	public static final int IdPropertyParryPercent = 241; // 格挡（率）%
//	public static final int IdPropertyDeParryPercent = 242; // 破击（率）%
//	public static final int IdPropertyDeDamPercent = 243; // 减伤%
//	public static final int IdPropertyAddDamPercent = 244; // 必杀%
//	public static final int IdPropertyDeCritValuePercent = 245; // 强韧%
//
//	public static final int IdPropertyCarePercent = 246; // 疗效%
//	public static final int IdPropertyPlusDamPercent = 247; // 附伤%
//	public static final int IdPropertyPlusDamRatePercent = 248; // 附伤系数%
//	public static final int IdPropertyDeDamRatePercent = 249; // 减伤系数%
//	public static final int IdPropertyCareRatePercent = 250; // 疗效系数%
//	public static final int IdPropertyRageSpdPercent = 251; // 聚气速度%
//
//	public static final int IdPropertyExp = 299; // 主公经验
//
//	// 战力系数
//	public static final float IdRatioHP = 0.1f; // 兵力
//	public static final float IdRatioAtk = 0.5f; // 攻击
//	public static final float IdRatioRage = 4f; // 聚气
//	public static final float IdRatioAdDef = 0.75f; // 物防
//	public static final float IdRatioApDef = 0.75f; // 法防
//	public static final float IdRatioHit = 1.5f; // 命中
//	public static final float IdRatioDodge = 1.5f; // 闪躲
//
//	public static final float IdRatioCrit = 1.5f; // 暴击
//	public static final float IdRatioDeCrit = 1.5f; // 韧性
//	public static final float IdRatioParry = 1.5f; // 格挡
//	public static final float IdRatioDeParry = 1.5f; // 破击
//	public static final float IdRatioPlusDam = 0.4f; // 附伤
//	public static final float IdRatioDeDam = 0.4f; // 减伤
//
//	/**
//	 * 计算战力
//	 */
//	public static int calFC(Map<Integer, Integer> attrs) {
//		float hp = attrs.getOrDefault(IdPropertyHp, 0) * (100 + attrs.getOrDefault(IdPropertyHpPercent, 0)) / 100f * IdRatioHP;
//		float atk = attrs.getOrDefault(IdPropertyAtk, 0) * (100 + attrs.getOrDefault(IdPropertyAtkPercent, 0)) / 100f * IdRatioAtk;
//		float rage = attrs.getOrDefault(IdPropertyRage, 0) * (100 + attrs.getOrDefault(IdPropertyRagePercent, 0)) / 100f * IdRatioRage;
//		float adDef = attrs.getOrDefault(IdPropertyAdDef, 0) * (100 + attrs.getOrDefault(IdPropertyAdDefPercent, 0)) / 100f * IdRatioAdDef;
//		float apDef = attrs.getOrDefault(IdPropertyApDef, 0) * (100 + attrs.getOrDefault(IdPropertyApDefPercent, 0)) / 100f * IdRatioApDef;
//		float hit = attrs.getOrDefault(IdPropertyHit, 0) * (100 + attrs.getOrDefault(IdPropertyHitPercent, 0)) / 100f * IdRatioHit;
//		float dodge = attrs.getOrDefault(IdPropertyDodge, 0) * (100 + attrs.getOrDefault(IdPropertyDodgePercent, 0)) / 100f * IdRatioDodge;
//
//		float crit = attrs.getOrDefault(IdPropertyCrit, 0) * (100 + attrs.getOrDefault(IdPropertyCritPercent, 0)) / 100f * IdRatioCrit;
//		float deCrit = attrs.getOrDefault(IdPropertyDeCrit, 0) * (100 + attrs.getOrDefault(IdPropertyDeCritPercent, 0)) / 100f * IdRatioDeCrit;
//		float parry = attrs.getOrDefault(IdPropertyParry, 0) * (100 + attrs.getOrDefault(IdPropertyParryPercent, 0)) / 100f * IdRatioParry;
//		float deParry = attrs.getOrDefault(IdPropertyDeParry, 0) * (100 + attrs.getOrDefault(IdPropertyDeParryPercent, 0)) / 100f * IdRatioDeParry;
//		float plusDam = attrs.getOrDefault(IdPropertyPlusDam, 0) * (100 + attrs.getOrDefault(IdPropertyPlusDamPercent, 0)) / 100f * IdRatioPlusDam;
//		float deDam = attrs.getOrDefault(IdPropertyDeDam, 0) * (100 + attrs.getOrDefault(IdPropertyDeDamPercent, 0)) / 100f * IdRatioDeDam;
//
//		int fc = (int) (hp + atk + rage + adDef + apDef + hit + dodge + crit + deCrit + parry + deParry + plusDam + deDam);
//		return fc;
//	}
//
//	public static String getConfigInfo() {
//
//		Mbeans.create();
//
//		ThreadGroup group = Thread.currentThread().getThreadGroup();
//		ThreadGroup topGroup = group;
//		// 遍历线程组树，获取根线程组
//		while (group != null) {
//			topGroup = group;
//			group = group.getParent();
//		}
//
//		Runtime runtime = Runtime.getRuntime();
//		long usedMemory = runtime.totalMemory() - runtime.freeMemory();
//
//		StringBuilder builder = new StringBuilder();
//		builder.append("\n\n").append("====================================================").append("\n");
//		builder.append("虚拟机可用处理器:").append(runtime.availableProcessors()).append("\n");
//		builder.append("当前的活动线程总数:").append(topGroup.activeCount()).append("\n");
//		builder.append("线程组总数:").append(topGroup.activeGroupCount()).append("\n");
//		builder.append("虚拟机可用最大内存:").append(runtime.maxMemory() / 1024 / 1024).append("M").append("\n");
//		builder.append("虚拟机占用总内存:").append(runtime.totalMemory() / 1024 / 1024).append("M").append("\n");
//		builder.append("虚拟机空闲内存:").append(runtime.freeMemory() / 1024 / 1024).append("M").append("\n");
//		builder.append("当前使用内存:").append(usedMemory / 1024 / 1024).append("M").append("\n");
//		builder.append("ObjectPendingFinalizationCount:").append(Mbeans.currObtain.getFinallzationCount).append("\n");
//		builder.append("heapMemoryUsage:").append(Mbeans.currObtain.heapMemoryUsage).append("\n");
//		builder.append("nonHeapMemoryUsage:").append(Mbeans.currObtain.nonHeapMemoryUsage).append("\n");
//
//		builder.append("----------------------------------------------------").append("\n");
//		builder.append("应用路径:").append(AppPath).append("\n");
//		builder.append("资源路径:").append(ResourcePath).append("\n");
//		builder.append("Json配置路径:").append(JsonConfigPath).append("\n");
//		builder.append("Common配置路径:").append(CommonConfigPath).append("\n");
//		builder.append("本地服务配置路径:").append(localConfigPath).append("\n");
//		builder.append("ServerId:").append(ServerId).append("\n");
//		builder.append("ServerGroup:").append(ServerGroup).append("\n");
//		builder.append("是否开启了GM:").append(OpenGMFromClient).append("\n");
//
////		DruidDataSource dataSource = SpringContextHolder.getInstance().getBean(com.alibaba.druid.pool.DruidDataSource.class);
////		builder.append("数据库url:").append(dataSource.getUrl()).append("\n");
////		builder.append("----------------------------------------------------").append("\n");
////		// builder.append("连接的服务器地址:").append(serverIP).append(":").append(serverPort).append("\n");
////		builder.append("====================================================").append("\n\n");
//		return builder.toString();
//	}
//
//	public static class Mbeans {
//		public static Mbeans lastObtain = null;
//		public static Mbeans currObtain = null;
//
//		public long obtainTime = 0;
//		public long getFinallzationCount;
//		public String heapMemoryUsage;
//		public String nonHeapMemoryUsage;
//
//		public static Mbeans create() {
//			lastObtain = currObtain;
//			currObtain = new Mbeans();
//			if (lastObtain == null)
//				lastObtain = currObtain;
//
//			currObtain.obtainTime = System.currentTimeMillis();
//
//			MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
//			currObtain.getFinallzationCount = memoryMXBean.getObjectPendingFinalizationCount();
//			currObtain.heapMemoryUsage = memoryMXBean.getHeapMemoryUsage().toString();
//			currObtain.nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage().toString();
//
//			return currObtain;
//		}
//	}

}
