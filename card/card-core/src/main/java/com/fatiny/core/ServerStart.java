package com.fatiny.core;

import com.fatiny.core.bootstrap.IServer;
import com.fatiny.core.bootstrap.ServerManager;
import com.fatiny.core.util.GameLog;

/**
 * 服务启动入口
 * 
 * @author huachp
 */
public class ServerStart {
	
	public static void main(String[] args) {
		try {
			long start = System.currentTimeMillis();
//			GameLog.init(); // 初始化日志工具类
			IServer server = ServerManager.bootServer();
			GameLog.info("正在启动[{}]服务", server.getName());
			server.run();
			long elapsed = System.currentTimeMillis() - start;
			GameLog.info("[{}]服务启动成功, 耗时:{}毫秒", server.getName(), elapsed);
			System.gc();
			server.startInstruct();//启动后台指令
		} catch (Exception e) {
			GameLog.error("服务器初始化过程出现异常, 启动失败", e);
		}
	}
	
}
