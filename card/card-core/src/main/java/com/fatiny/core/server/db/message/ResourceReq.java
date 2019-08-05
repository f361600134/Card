package com.fatiny.core.server.db.message;

/**
 * 数据服资源请求
 * 
 */
public class ResourceReq extends AbstractDbServerMsg {
	
	@Override
	public long getId() {
		return -1;
	}

	@Override
	public Command getCmd() {
		return null;
	}

}
