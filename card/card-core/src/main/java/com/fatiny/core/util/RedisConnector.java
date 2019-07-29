package com.fatiny.core.util;

import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis连接实例
 */
public class RedisConnector {

	private JedisPool pool;

	// default conf begin
	/**
	 * 对象池的大小
	 */
	private final static int MAX_ACTIVE = 50;
	/**
	 * 最大保持空闲状态的对象
	 */
	private final static int MAX_IDLE = 20;
	/**
	 * 获取池内对象最大等待时间
	 */
	private final static int MAX_WAIT = 1000;
	/**
	 * 当调用borrow Object方法时，是否进行有效性检查
	 */
	private final static boolean TEST_ON_BORROW = true;
	/**
	 * 当调用return Object方法时，是否进行有效性检查
	 */
	private final static boolean TEST_ON_RETURN = true;
	// default conf end

	
	// Redis Define Status begin
	/**
	 * ok
	 */
	public static final String OK = "OK";

	/**
	 * 不存在
	 */
	public static final String NX = "NX";

	/**
	 * 存在
	 */
	public static final String XX = "XX";

	/**
	 * 以秒为过期单位
	 */
	public static final String EX = "EX";

	/**
	 * 以毫秒为过期单位
	 */
	public static final String PX = "PX";
	// Redis Define Status end

	
	/**
	 * 使用默认配置创建Redis连接
	 *
	 * @param ip   redis-ip
	 * @param port redis-port
	 */
	public RedisConnector(String ip, int port) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(MAX_ACTIVE);
		config.setMaxIdle(MAX_IDLE);
		config.setMaxWaitMillis(MAX_WAIT);
		config.setTestOnBorrow(TEST_ON_BORROW);
		config.setTestOnReturn(TEST_ON_RETURN);

		pool = new JedisPool(config, ip, port);
	}

	/**
	 * 使用自定义配置创建Redis连接
	 *
	 * @param ip        redis-ip
	 * @param port      redis-port
	 * @param jedisConfig 配置
	 */
	public RedisConnector(Properties jedisConfig) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(Integer.parseInt(jedisConfig.getProperty("maxActive")));
		config.setMaxIdle(Integer.parseInt(jedisConfig.getProperty("maxIdle")));
		config.setMaxWaitMillis(Integer.parseInt(jedisConfig.getProperty("maxWait")));
		config.setTestOnBorrow(TEST_ON_BORROW);
		config.setTestOnReturn(TEST_ON_RETURN);
		
		String ip = jedisConfig.getProperty("ip");
		int port = Integer.parseInt(jedisConfig.getProperty("port"));
		pool = new JedisPool(config, ip, port);
	}

	/**
	 * 从对象池获得一个redis连接
	 *
	 * @return {@link Jedis}
	 */
	public Jedis getConn() {
		return pool.getResource();
	}

	
	/**
	 * 关闭redis连接
	 *
	 * @param conn
	 */
	public void closeConn(Jedis conn) {
		if (conn != null) {
			conn.close();
		}
	}
	
	
	/**
	 * 当前获取连接数
	 * 
	 * @return {@link int}
	 */
	public int activeNum() {
		return pool.getNumActive();
	}
	
	
	/**
	 * 当前等待
	 * 
	 * @return {@link int}
	 */
	public int waitNum() {
		return pool.getNumWaiters();
	}
	

}
