package com.fatiny.core.bootstrap;

/**
 * 服务启动抽象层
 */
public abstract class AbstractServer implements IServer {
	
	/** 服务器IP */
	protected String ip;
	/** 服务器端口 */
	protected int port;
	/** 服务名称 */
	protected String name;
	/** 运行状态 */
	protected boolean runState;
	
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isRunning() {
		return runState;
	}
	
	
	protected void running() {
		this.runState = true;
	}
	
	@Override
	public void run() {
		if (isRunning()) 
			return;
		boolean running = start();
		if (running) 
			running();
	}
	
	
	void set(String ip, int port, String name) {
		this.ip = ip;
		this.port = port;
		this.name = name;
	}
	
	
	protected abstract boolean start();

}
