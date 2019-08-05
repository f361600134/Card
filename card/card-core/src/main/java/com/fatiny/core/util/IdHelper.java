package com.fatiny.core.util;

/**
 * ID机制帮助类
 * 
 */
public class IdHelper {
	
	public static final int PLAYER_TYPE = 0;
	public static final int PUBLIC_TYPE = 1;
	
	public static final long AREA_DB_NUM_BITS = 13; // db id占位
	public static final long ID_TYPE_KEY_BIT = 14; // id类型标识位(玩家、公共)
	public static final long AREA_DB_NUM_MAX = (1 << AREA_DB_NUM_BITS) - 1; 
	
	public static final long MODULE_NUM_BITS = 4; // 模块ID占位
	public static final long MODULE_NUM_MAX = (1 << MODULE_NUM_BITS) - 1;
	
	public static int areaDbId(long id) {
		return (int) (id & AREA_DB_NUM_MAX);
	}
	
	// 个人业务ID
	public static long increment(long id) {
		return id >> (AREA_DB_NUM_BITS + 1);
	}
	
	// 公共业务ID
	public static long incrementPub(long id) {
		return id >> (MODULE_NUM_BITS + AREA_DB_NUM_BITS + 1);
	}
	
	public static int idType(long id) {
		return (int) (id >> AREA_DB_NUM_BITS) & 1;
	}
	
	public static boolean isPlayerType(long id) {
		return isValidId(id) && idType(id) == 0;
	}
	
	public static boolean isPublicType(long id) {
		return idType(id) == 1;
	}
	
	public static boolean isValidId(long id) {
		return id > (AREA_DB_NUM_MAX << 1) + 1;
	}
	
}
