package com.fatiny.core;

import com.fatiny.core.bootstrap.IServer;
import com.fatiny.core.bootstrap.ServerManager;

import lombok.extern.slf4j.Slf4j;

/**
 * 服务启动入口
 * 
 * @author huachp
 */
@Slf4j
public class ServerStart {
	
	public static void main(String[] args) {
		try {
			long start = System.currentTimeMillis();
//			log.init(); // 初始化日志工具类
			IServer server = ServerManager.bootServer();
			log.info("正在启动[{}]服务", server.getName());
			server.run();
			long elapsed = System.currentTimeMillis() - start;
			log.info("[{}]服务启动成功, 耗时:{}毫秒", server.getName(), elapsed);
			System.gc();
			server.startInstruct();//启动后台指令
		} catch (Exception e) {
			log.error("服务器初始化过程出现异常, 启动失败", e);
		}
	}
	
}
