package com.fatiny.core.bootstrap;

/**
 * 服务启动接口
 * 
 * @author huachp
 */
public interface IServer {
	
	String getName();
	
	void run();
	
	void stop();
	
	boolean isRunning();
	
	void startInstruct();//后台指令
}
