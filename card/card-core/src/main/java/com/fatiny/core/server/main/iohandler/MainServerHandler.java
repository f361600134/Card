package com.fatiny.core.server.main.iohandler;

import java.io.IOException;

import com.fatiny.core.net.IServerHandler;
import com.fatiny.core.server.main.GameSession;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 游戏服务器消息处理器
 * 
 * 注意: 
 * 	1. 一条连接对应一个MainServerHandler
 * 	2. 一条连接对应一个GameSession
 * 	3. 所有MainServerHandler持有的IServerHandler都是同一个引用
 * 
 */
public class MainServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
	
	private GameSession session;
	private IServerHandler serverHandler;
	
	
	public MainServerHandler(IServerHandler serverHandler) {
		this.serverHandler = serverHandler;
	}
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		session = GameSession.create(ctx.channel());	// 新建session
		serverHandler.onConnect(session);
	}

	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		serverHandler.onClose(session);
	}


	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		serverHandler.onReceive(session, msg);
	}
	
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (cause instanceof IOException) {
			return;
		} else {
			cause.printStackTrace();
			serverHandler.onException(session, cause);
		}
	}


}
