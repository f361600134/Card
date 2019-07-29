package com.fatiny.core.bootstrap;

import com.fatiny.core.net.IServerHandler;

/**
 * 业务逻辑启动初始化接口
 * 
 * @author huachp
 */
public interface IStartup {
	
	IServerHandler startup() throws Exception;
	
	void startInstruct();//后台指令
}
