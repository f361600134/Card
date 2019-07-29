package com.fatiny.core.bootstrap;

/**
 * 服务管理
 * 
 * @author huachp
 */
public class ServerManager {
	
	private static IServer server;
	private static ServerConfig serverConfig;
	
	
	public static boolean isServerOn() {
		return server == null ? false : server.isRunning();
	}
	
	public static String getServerName() {
		return server == null ? "" : server.getName();
	}
	
	public static ServerConfig getConfig() {
		return serverConfig;
	}
	

	public static IServer bootServer() throws Exception {
		try {
			serverConfig = ServerConfig.create();
			String ip = serverConfig.getIp();
			int port = serverConfig.getPort();
			String serverName = serverConfig.getServerName();
			String className = serverConfig.getServerClass();
			
			Class<?> cls = Thread.currentThread().getContextClassLoader().loadClass(className);
			IServer newServer = (IServer) cls.newInstance();
			((AbstractServer) newServer).set(ip, port, serverName);	// 初始化服务信息
			server = newServer;
			
			return newServer;
		} catch (Exception e) {
			throw e;
		}
	}
	
}
