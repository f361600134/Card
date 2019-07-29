package com.fatiny.core.server.db.message;

import com.fatiny.core.client.db.Response;

/**
 * 数据服消息
 * 
 * @author huachp
 */
public interface DbServerMsg {
	
	Byte HEARTBEAT = 0x7F;
	
	int callbackId();
	
	void injectCallbackId(int callbackId);
	
	long getId();
	
	Command getCmd();
	
	void doResponse(Response response);
	
}
