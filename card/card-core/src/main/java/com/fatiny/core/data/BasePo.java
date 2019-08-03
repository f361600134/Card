package com.fatiny.core.data;

import java.io.Serializable;

public abstract class BasePo implements Serializable {

	/**
	 * primary key, 对应数据库的第一主键(玩家ID)
	 * @return
	 */
	abstract public long key();
	
	/**
	 * 缓存二级ID, 如果返回空默认使用key()
	 * @return
	 */
	abstract public String cacheId();
	
	/**
	 * 数据库第一主键的列名
	 * @return
	 */
	abstract public String keyColumn();
	
	
	/**
	 * id的列, 对应数据库的主键和索引
	 * @return
	 */
	abstract public String[] ids();

	/**
	 * 所有id列的值
	 * @return
	 */
	abstract public Object[] idValues();
	
	/**
	 * 所有属性列
	 * @return
	 */
	abstract public String[] props();

	/**
	 * 所有属性列的值
	 * @return
	 */
	abstract public Object[] propValues();

	
}
