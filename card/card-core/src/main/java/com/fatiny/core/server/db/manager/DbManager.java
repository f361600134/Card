package com.fatiny.core.server.db.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.fatiny.core.server.db.DbServerConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据库连接管理
 */
@Slf4j
public class DbManager {
	
	static Map<Integer, DataSource> dataSources = new HashMap<>();
	
	public static void init() {
		try {
			DbServerConfig dbConfig = DbServerConfig.getInstance();
			
			for (Map.Entry<Integer, Properties> entry : dbConfig.getDataSourceCfgs().entrySet()) {
				Integer serverId = entry.getKey();
				Properties config = entry.getValue();
				config.setProperty(DruidDataSourceFactory.PROP_INIT, "true");	// 初始化所有数据库连接
				DataSource ds = DruidDataSourceFactory.createDataSource(config);
				dataSources.put(serverId, ds);
				
				log.info("初始化数据库连接池:{}", config.getProperty("url"));
			}
			
		} catch (Exception e) {
			log.error("数据库连接池初始化过程出现错误", e);
		}
		
	}
	
	
	public static DataSource getDataSource(int serverId) {
		if (dataSources.size() == 1) { // 只有一个连接池
			return dataSources.values().iterator().next();
		}
		return dataSources.get(serverId);
	}
	
	
	public static Collection<DataSource> allDataSource() {
		return dataSources.values();
	}
	
	
	public static Connection getConn(int serverId) {
		try {
			DataSource ds = getDataSource(serverId);
			return ds.getConnection();
		} catch (Exception e) {
			log.error("获取连接出现错误, serverId:{}", serverId, e);
		}
		return null;
	}
	
	
	public static void closeConn(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			log.error("关闭连接出现错误", e);
		}
	}
	
	
	public static void closeStatement(Statement st) {
		try {
			st.close();
		} catch (SQLException e) {
			log.error("closeStatement occur ex", e);
		}
	}
	
	
}
