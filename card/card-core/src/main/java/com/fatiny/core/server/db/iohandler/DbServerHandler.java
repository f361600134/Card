package com.fatiny.core.server.db.iohandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.fatiny.core.server.db.DbServerConfig;
import com.fatiny.core.server.db.context.RequestContext;
import com.fatiny.core.server.db.executor.MysqlExecutor;
import com.fatiny.core.server.db.executor.RedisExecutor;
import com.fatiny.core.server.db.message.CompositeReqMsg;
import com.fatiny.core.server.db.message.DbServerMsg;
import com.fatiny.core.server.db.message.DbServerReqMsg;
import com.fatiny.core.server.db.message.DbServerRespMsg;
import com.fatiny.core.server.db.message.ResourceReq;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息处理器
 */
@Slf4j
public class DbServerHandler extends SimpleChannelInboundHandler<Object> {
	
	private MysqlExecutor mysqlExecutor;
	private RedisExecutor redisExecutor;
	
	public DbServerHandler(MysqlExecutor mysqlExecutor, RedisExecutor redisExecutor) {
		this.mysqlExecutor = mysqlExecutor;
		this.redisExecutor = redisExecutor;
	}
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("游戏服连接:{}", ctx.channel().remoteAddress());
	}
	

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("游戏服断开连接:{}", ctx.channel().remoteAddress());
	}


	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object message) throws Exception {
		if (message instanceof ResourceReq) {
			ResourceReq req = (ResourceReq) message;
			remoteGetResource(ctx, req); 
			return;
		}
		if (message instanceof Byte) {
			ctx.channel().writeAndFlush(message);
			return;
		}
		submitToExecutor(ctx, (DbServerMsg) message);
	}


	private void remoteGetResource(ChannelHandlerContext ctx, ResourceReq msg) {
		DbServerConfig config = DbServerConfig.getInstance();
		Set<Integer> ids = config.getDataSourceCfgs().keySet();
		List<Integer> data = new ArrayList<>(ids);
		DbServerRespMsg respMsg = new DbServerRespMsg(data);
		respMsg.injectCallbackId(msg.callbackId());
		ctx.channel().writeAndFlush(respMsg);
	}
	
	
	private void submitToExecutor(ChannelHandlerContext ctx, DbServerMsg message) {
		Channel ch = ctx.channel();
		
		if (message instanceof CompositeReqMsg) {
			CompositeReqMsg compositeMsg = (CompositeReqMsg) message;
			LinkedList<DbServerMsg> reqList = compositeMsg.getRequestMsgs();
			
			for (DbServerMsg msg; (msg = reqList.poll()) != null; ) {
				RequestContext requestCtx = new RequestContext(msg, ch);
				processSubmit(msg, requestCtx); 
			}
		} else {
			RequestContext requestCtx = new RequestContext(message, ch);
			processSubmit(message, requestCtx);
		}
	}


	private void processSubmit(DbServerMsg message, RequestContext requestCtx) {
		if (message instanceof DbServerReqMsg) {
			DbServerReqMsg reqMsg = (DbServerReqMsg) message;
			if (!reqMsg.isCacheable()) {
				mysqlExecutor.submit(requestCtx); return;
			}
		}
		redisExecutor.submit(requestCtx);
	}
	

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (cause instanceof IOException) {
			return;
		} else {
			log.error("数据服Netty通信过程出现异常", cause);
		}
	}
	
	
}
