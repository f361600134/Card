package com.fatiny.core.client.db;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.fatiny.core.server.db.message.Command;
import com.fatiny.core.server.db.message.CompositeReqMsg;
import com.fatiny.core.server.db.message.DbServerMsg;
import com.fatiny.core.util.GameLog;

/**
 * 消息回调处理器
 * 
 * @author huachp
 */
public class DbServerMsgHandler {
	
	/** callbackId生成器 */
	private static AtomicInteger ID_CREATOR = new AtomicInteger();
	/** 回调信息 */
	private ConcurrentMap<Integer, Response> callbacks = new ConcurrentHashMap<>();
	
	DbServerMsgHandler() {
		
	}
	
	public Response addCallback(DbServerMsg request) {
		if (request instanceof CompositeReqMsg) {
			return addCompositeMsg(request);
		}
		
		int callbackId = Math.abs(ID_CREATOR.incrementAndGet());
		request.injectCallbackId(callbackId);
		Response response = new Response(request);
		callbacks.put(callbackId, response);
		return response;
	}

	private Response addCompositeMsg(DbServerMsg request) {
		CompositeReqMsg compositeMsg = (CompositeReqMsg) request;
		Response response = new Response();
		for (DbServerMsg elementMsg : compositeMsg.getRequestMsgs()) {
			int callbackId = Math.abs(ID_CREATOR.incrementAndGet());
			elementMsg.injectCallbackId(callbackId);
			callbacks.put(callbackId, response);
		}
		return response;
	}
	
	public void handleResp(DbServerMsg responseMsg) {
		int callbackId = responseMsg.callbackId();
		Response resp = callbacks.remove(callbackId);
		if (resp == null) {
			Command cmd = responseMsg.getCmd();
			GameLog.error("收到不存在回调的响应:{}, callbackId:{}", cmd, callbackId);
			return;
		}
		responseMsg.doResponse(resp);
	}
	
}
