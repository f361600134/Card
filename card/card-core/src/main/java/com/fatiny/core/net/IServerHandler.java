package com.fatiny.core.net;

import com.fatiny.core.server.main.GameSession;

import io.netty.buffer.ByteBuf;

/**
 * IServerHandler
 * 
 * @author huachp
 */
public interface IServerHandler {
	
	void onConnect(GameSession session);

	void onReceive(GameSession session, ByteBuf message);

	void onClose(GameSession session);

	void onException(GameSession session, Throwable e);
	
	void serverStatus(boolean running);
	
}
