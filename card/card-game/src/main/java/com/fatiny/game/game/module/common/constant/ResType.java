package com.fatiny.game.game.module.common.constant;

import com.fatiny.game.game.module.player.domain.DbPlayer;

/**
 * 资源类型
 */
public enum ResType {
	
	Diamond(101){// 元宝
		@Override
		public void add(DbPlayer player, int value) {
			player.addDiamond(value, "");
		}
		
		@Override
		public boolean checkEnough(DbPlayer player, int needValue) {
			return player.enoughDiamond(needValue);
		}
	},
	Mimeral(103),// 矿石
	GOLD(104),// 铜币
	Power(105),// 体力
	PubFamous(106),// 魂玉
	PubNormal(107), // 招募令
	ShopRefresh(110),// 刷新令
	ShopTreasure(111),// 将魂
	ShopMilitary(112),// 军功
	FamilyDevote(113),// 家族贡献
	HorseGhost(114),// 马魂
	StarGhost(115),// 星魂
	ShopBattle(116),// 战功
	ShopJade(117),// 玉璧
	;
	
	private int type;
	private ResType(int type) {
		this.type = type;
	}
	
	public void add(DbPlayer player, int value) {
	}
	
	public boolean checkEnough(DbPlayer player, int needValue) {
		return false;
	}
	
	/**
	 * 获取到指定状态枚举
	 * @param status
	 * @return
	 * @return PayOrderStatusEnum
	 */
	public static ResType getResType(int type) {
		for (ResType resType : values()) {
			if (resType.type == type) 
				return resType;
		}
		return null;
	}
}
