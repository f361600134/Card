package com.fatiny.game.game.module.common.constant;

/**
 * 物品类型
 */
public enum ItemType {
	EQUIP(1),//装备
	/**
	 * 资源 对应的资源ID
	 * @see ResType#GOLD
	 * @see ResType#Diamond
	 * @see ResType#Mimeral
	 * @see ...
	 */
	RESOURCE(3), //资源
	ITEM(4), //道具
	GEM(5), //宝石
	HORSE(10),//马匹
	HORSEGHOST(11),//马魂
	;
	private int type;
	private ItemType(int type) {
		this.type = type;
	}
	
	/**
	 * 获取到指定状态枚举
	 * @param status
	 * @return
	 * @return PayOrderStatusEnum
	 */
	public static ItemType getItemType(int type) {
		for (ItemType itemType : values()) {
			if (itemType.type == type) 
				return itemType;
		}
		return null;
	}

}
