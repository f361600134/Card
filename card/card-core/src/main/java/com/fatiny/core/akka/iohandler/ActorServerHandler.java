package com.fatiny.core.akka.iohandler;

import java.io.IOException;

import com.fatiny.core.akka.remote.ActorMessage;
import com.fatiny.core.akka.remote.ActorMsgContext;
import com.fatiny.core.akka.remote.ServerRouteActors;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ActorServerHandler extends SimpleChannelInboundHandler<ActorMessage> {
	
//	public static long start;
	
	private ServerRouteActors routeActors;
	
	public ActorServerHandler(ServerRouteActors routeActors) {
		this.routeActors = routeActors;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		log.info("Actor远程客户端连接:{}", channel.remoteAddress());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		log.info("Actor远程客户端断开连接:{}", channel.remoteAddress());
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ActorMessage msg) throws Exception {
		Channel ch = ctx.channel();
		if (msg.isHeartbeat()) {
			ch.writeAndFlush(msg);
		} else {
//			start = System.nanoTime();
			routeActors.submit(new ActorMsgContext(ch, msg));
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (cause instanceof IOException) {
			return;
		} else {
			log.error("远程Actor通信过程出现异常", cause);
		}
	}
	
}
