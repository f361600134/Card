package com.fatiny.core.bootstrap;

import java.io.FileInputStream;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 * 服务配置
 */
@Slf4j
public class ServerConfig {
	
	public static final String MAIN_SERVER = "main"; // game server
	public static final String DB_SERVER = "db"; // db server
	
	
	private Properties config;
	
	ServerConfig() {
		
	}
	
	public ServerConfig(Properties p) {
		this.config = p;
	}
	
	
	public void load(String path) {
		try {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(path);
				Properties p = new Properties();
				p.load(fis);
				this.config = p;
			} finally {
				fis.close();
			}
		} catch (Exception e) {
			log.error("读取服务配置出错", e);
		}
	}
	
	
	public String get(String key) {
		return config.getProperty(key);
	}
	
	public String getIp() {
		return config.getProperty("server.ip").trim();
	}
	
	public String getServerName() {
		return config.getProperty("server.name").trim();
	}
	
	public String getServerClass() {
		return config.getProperty("server.class").trim();
	}
	
	public String getInitClass() {
		return config.getProperty("server.init").trim();
	}
	
	public int getPort() {
		return Integer.parseInt(config.getProperty("server.port").trim());
	}
	
	public int getConnectionIdleTime() {
		return Integer.parseInt(config.getProperty("server.connection.idle", "30").trim());
	}
	
	
	public static ServerConfig create() {
		ServerConfig config = new ServerConfig();
		config.load("src/main/resources/server.properties");
		return config;
	}
	
	
}
