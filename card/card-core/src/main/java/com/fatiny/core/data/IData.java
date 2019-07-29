package com.fatiny.core.data;

import java.util.List;
import java.util.Map;

import com.fatiny.core.client.db.Response;
import com.fatiny.core.server.db.message.BatchLoadMsg;


/**
 * 数据接口
 * 
 * @author huachp
 */
public interface IData {


	/**
	 * 查询数据
	 * 流程: 数据服先从redis缓存中获取, 如果没有命中, 则从mysql获取
	 * 
	 * @param cls	      实体类信息
	 * @param primary  主键
	 * @return {@link List} 返回一条或多条数据
	 */
	 <T extends BasePo> List<T> getData(Class<? extends BasePo> cls, long primary);
	
	
	/**
	 * 查询数据, 此方法提供给公共业务获取数据
	 * 流程: 数据服先从redis缓存中获取, 如果没有命中, 则从mysql获取
	 * 
	 * @param key	 如果没有key传-1
	 * @param cls	 实体类信息
	 * @return {@link List} 返回所有数据
	 */
	
	 <T extends BasePo> List<T> getAllData(long key, Class<? extends BasePo> cls);
	
	/**
	 * 通用访问:删除
	 * 流程: 数据服先保存数据到redis, 再保存到mysql
	 * 
	 * @param cls
	 * @param t
	 * @param <T>
	 * @return
	 */
	int delete(Class<? extends BasePo> cls, BasePo t);
	
	
	/**
	 * 通用访问:更新
	 * 流程: 数据服先保存数据到redis, 再保存到mysql
	 * 
	 * @param cls
	 * @param t
	 * @param <T>
	 * @return
	 */
	boolean update(Class<? extends BasePo> cls, BasePo t);
	
	
	/**
	 * 通用访问:插入，不支持自动主键
	 * 流程: 数据服先保存数据到redis, 再保存到mysql
	 * 
	 * @param cls
	 * @param t
	 * @return
	 */
	void insert(Class<? extends BasePo> cls, BasePo t);


	/**
	 * 非缓存类访问:sql查询
	 * 
	 * @param key	 如果没有key传-1
	 * @param cls
	 * @param id
	 * @param sql
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> query(long key, String sql, Object... params);
	
	
	/**
	 * 非缓存类访问:通过属性查找
	 * 
	 * @param key	 如果没有key传-1
	 * @param cls
	 * @param props
	 * @param values
	 * @param <T>
	 * @return
	 */
	<T extends BasePo> List<T> getByProps(long key, Class<? extends BasePo> cls, String[] props, Object[] values);
	
	
	/**
	 * 非缓存类访问:删除
	 * 
	 * @param cls
	 * @param ids
	 * @return 受影响的行数
	 */
	int deleteBySql(long key, Class<? extends BasePo> cls, String[] props, Object[] values);

	
	
	// --------------------------------  以下是异步API  --------------------------------------
	
	
	void insertAsync(Class<? extends BasePo> cls, BasePo t);
	
	
	void updateAsync(Class<? extends BasePo> cls, BasePo t);
	
	
	void deleteAync(Class<? extends BasePo> cls, BasePo t);
	
	
	Response executeBatchSave();
	
	
	BatchLoadMsg executeBatchLoad(BatchLoadMsg batchLoadMsg);
	
	
	void replaceAsync(Class<? extends BasePo> cls, BasePo t);
	
	
}
