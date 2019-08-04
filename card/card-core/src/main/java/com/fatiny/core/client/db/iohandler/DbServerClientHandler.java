package com.fatiny.core.client.db.iohandler;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fatiny.core.client.db.DbServerDetectionHander;
import com.fatiny.core.client.db.DbServerMsgHandler;
import com.fatiny.core.server.db.message.DbServerMsg;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据服客户端消息处理器
 */
@Slf4j
public class DbServerClientHandler extends SimpleChannelInboundHandler<Object> {
	
	private DbServerMsgHandler msgHandler;
	private DbServerDetectionHander detctionHandler;
	private ExecutorService service;
	
	public DbServerClientHandler(DbServerMsgHandler msgHandler, 
			DbServerDetectionHander detectionHandler) {
		this.msgHandler = msgHandler;
		this.detctionHandler = detectionHandler;
		initService();
	}
	
	private void initService() {
		service = Executors.newSingleThreadExecutor();
	}
	
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("检测到数据服连接断开:{}", ctx.channel().remoteAddress());
	}
	

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		final Object responseMsg = msg;
		if (responseMsg instanceof Byte) {
			SocketAddress addr = ctx.channel().remoteAddress();
			detctionHandler.response(addr, (Byte) responseMsg);
			return;
		}
		if (!(msg instanceof DbServerMsg)) {
			log.error("收到不合法的消息:{}", msg);
			return;
		}
		service.execute(new Runnable() {
			@Override
			public void run() {
				msgHandler.handleResp((DbServerMsg) responseMsg);
			}
		});
	}
	

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (cause instanceof IOException) {
			return;
		} else {
			log.error("数据服客户端通信异常", cause);
		}
	}
	

}
