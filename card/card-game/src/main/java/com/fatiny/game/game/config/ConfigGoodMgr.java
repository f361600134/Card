package com.fatiny.game.game.config;

import java.util.Map;

import com.fatiny.game.game.config.pojo.ConfigProp;
import com.fatiny.game.game.module.common.constant.Config;
import com.google.common.collect.Maps;

public class ConfigGoodMgr {

	//物品类型
	public static final int Material_Hero_Type = 2;
	public static final int Material_Equip_Type = 3;
	public static final int Box_Random_Type = 4;
	public static final int Box_Normal_Type = 5;
	public static final int Box_Select_Type = 6;
	
	//物品品质
	public static final int Quality_Green = 1;
	public static final int Quality_Blue = 2;
	public static final int Quality_Purple = 3;
	public static final int Quality_Origin = 4;
	public static final int Quality_Red = 5;
	
	public static boolean existGood(int configId)
	{
		if(ConfigEquipmentMgr.exist(configId))
			return true;
		else if(ConfigSuitMgr.exist(configId))
			return true;
		else if(ConfigGemMgr.exist(configId))
			return true;
		else if(ConfigPropMgr.exist(configId))
		{
			ConfigProp config = ConfigPropMgr.getConfig(configId);
			return config.getUiLabel()!=0;		//0表示不是物品，不能放在背包中
		}
		else
			return false;
	}
	
	public static boolean CanOverlay(int configId)
	{
		if(ConfigEquipmentMgr.exist(configId))
			return false;
		else if(ConfigSuitMgr.exist(configId))
			return false;
		else if(ConfigGemMgr.exist(configId))
			return false;
		else if(ConfigPropMgr.exist(configId))
		{
			ConfigProp props = ConfigPropMgr.getConfig(configId);
			return props.getMaxCount()>1;
		}
		else
			return false;
	}
	
	/**
	 * 获取道具, 属性名字
	 * @param configId
	 * @return  
	 * @return String  
	 * @date 2019年6月24日下午2:26:55
	 */
	public static String getItemName(int configId) {
		Item item = getItem(configId);
		return item == null ? "" :  item.getItemName();
	}
	
	/**
	 * 获取道具
	 * @change 每次获取道具, 都要new 一个新对象, 这里可以修改
	 * @param configId
	 * @return  
	 * @return Item  
	 * @date 2019年5月25日上午11:30:52
	 */
	public static Item getItem(int configId)
	{
//		if(ConfigEquipmentMgr.exist(configId)) {
//			ConfigEquipment config = ConfigEquipmentMgr.getConfig(configId);
//			Item item = ItemFactory.getItem(configId, IGood.GOOD_CATEGORY_EQUIP, config.getName());
//			return item;
//		}else if(ConfigGemMgr.exist(configId)){
//			ConfigGem config = ConfigGemMgr.getConfig(configId);  
//			Item item = ItemFactory.getItem(configId, IGood.GOOD_CATEGORY_GEM, config.getName());
//			return item;
//		}else if(ConfigPropMgr.exist(configId)){
//			ConfigProp config = ConfigPropMgr.getConfig(configId);
//			Item item = ItemFactory.getItem(configId, IGood.GOOD_CATEGORY_PROPS, config.getName());
//			return item;
//		}else if(ConfigHeroInfoMgr.exist(configId)){
//			ConfigHeroInfo config = ConfigHeroInfoMgr.getConfig(configId);
//			Item item = ItemFactory.getItem(configId, IGood.GOOD_CATEGORY_HERO, config.getHeroname());
//			return item;
//		}
		return null;
	}
	
	/**
	 * 是否玩家属性
	 */
	public static boolean IsPlayerProperty(int configId)
	{
		return configId <= 300;
	}
	/**
	 * 是否武将
	 */
	public static boolean IsHero(int configId)
	{
		return ConfigHeroInfoMgr.exist(configId);
	}
	
	/**
	 * 是否装备
	 */
	public static boolean IsEquipment(int configId)
	{
		return ConfigEquipmentMgr.exist(configId);
	}
	/**
	 * 是否宝石
	 */
	public static boolean IsGem(int configId)
	{
		return ConfigGemMgr.exist(configId);
	}
	
	/**
	 * 是否物品或属性
	 */
	public static boolean IsGoodOrProperty(int configId) {
		return existGood(configId) || IsPlayerProperty(configId) || IsHero(configId);
	}
	/**
	 * 是否空物品
	 */
	public static boolean IsNullGood(int configId) {
		return configId == Config.IdNullGood;
	}
	
	/**
	 * 物品享元工厂
	 * @auth Jeremy
	 * @date 2019年5月25日下午12:39:12
	 */
	public static class ItemFactory{
		private static Map<Integer, Item> pool = Maps.newHashMap();
//		public static Item getItem(int itemId) {
//			if (!pool.containsKey(itemId)) {
//				synchronized (pool) {
//					if (!pool.containsKey(itemId)) {
//						Item item = new Item(itemId);
//						pool.put(itemId, item);
//						return item;
//					}
//				}
//			}
//			return pool.get(itemId);
//		}
		public static Item getItem(int itemId, int type, String name) {
			if (!pool.containsKey(itemId)) {
				synchronized (pool) {
					if (!pool.containsKey(itemId)) {
						Item item = new Item(itemId, type, name);
						pool.put(itemId, item);
						return item;
					}
				}
			}
			return pool.get(itemId);
		}
	}
//	
//	/**
//	 * 物品简述信息
//	 * @auth Jeremy
//	 * @date 2019年5月25日下午12:50:27
//	 */
//	public class ItemProfile {
//		private int itemId;	//配置id
//		private String itemName; //名称
//		
//		public ItemProfile(int itemId, String itemName){
//			this.itemId = itemId;
//			this.itemName = itemName;
//		}
//		public int getItemId() {
//			return itemId;
//		}
//		public void setItemId(int itemId) {
//			this.itemId = itemId;
//		}
//		public String getItemName() {
//			return itemName;
//		}
//		public void setItemName(String itemName) {
//			this.itemName = itemName;
//		}
//	}
	
	/**
	 * 定义一个统一的物品对象
	 * @auth Jeremy
	 * @date 2019年5月24日上午11:35:50
	 */
	public static class Item{
		private int type; //物品类型, 装备:1, 道具:2, 武将:3, 宝石:4, 马:5
		private int itemId;	//配置id
		private String itemName; //名称
		
		public Item(int type) {
			super();
			this.type = type;
		}
		
		public Item(int type, int itemId, String itemName) {
			super();
			this.type = type;
			this.itemId = itemId;
			this.itemName = itemName;
		}
		
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public int getItemId() {
			return itemId;
		}
		public void setItemId(int itemId) {
			this.itemId = itemId;
		}
		public String getItemName() {
			return itemName;
		}
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
	}
	
}
