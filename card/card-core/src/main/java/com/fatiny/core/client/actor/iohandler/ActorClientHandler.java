package com.fatiny.core.client.actor.iohandler;

import java.io.IOException;
import java.net.SocketAddress;

import com.fatiny.core.akka.remote.ActorMessage;
import com.fatiny.core.client.actor.ActorDetectionHander;
import com.fatiny.core.client.actor.ActorMsgHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ActorClientHandler extends SimpleChannelInboundHandler<ActorMessage> {
	
	private ActorMsgHandler msgHandler;
	private ActorDetectionHander detctionHandler;
//	private ExecutorService service;
	
	public ActorClientHandler(ActorMsgHandler msgHandler, ActorDetectionHander detectionHandler) {
		this.msgHandler = msgHandler;
		this.detctionHandler = detectionHandler;
//		initService();
	}
	
//	private void initService() {
//		int nThreads = Runtime.getRuntime().availableProcessors() / 2;
//		service = Executors.newFixedThreadPool(nThreads);
//	}
	
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("检测到远程Actor连接断开:{}", ctx.channel().remoteAddress());
	}
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ActorMessage msg) throws Exception {
		if (msg.isHeartbeat()) {
			SocketAddress addr = ctx.channel().remoteAddress();
			detctionHandler.response(addr);
		} else {
			msgHandler.handleResp(msg); 
		}
	}

	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (cause instanceof IOException) {
			return;
		} else {
			log.error("actor客户端通信异常", cause);
		}
	}
	
}
