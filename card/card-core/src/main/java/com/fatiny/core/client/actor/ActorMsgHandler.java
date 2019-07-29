package com.fatiny.core.client.actor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.fatiny.core.akka.remote.ActorMessage;
import com.fatiny.core.util.GameLog;

/**
 * 消息回调处理器
 * 
 * @author huachp
 */
public class ActorMsgHandler {
	
	/** callbackId生成器 */
	private static AtomicInteger ID_CREATOR = new AtomicInteger();
	/** 回调信息 */
	private ConcurrentMap<Integer, ActorCallback> callbacks = new ConcurrentHashMap<>();
	
	ActorMsgHandler() {
		
	}
	
	public ActorMessage addCallback(ActorMessage actorMsg, ActorCallback callback) {
		int msgId = Math.abs(ID_CREATOR.incrementAndGet());
		actorMsg.injectId(msgId);
		callbacks.put(msgId, callback);
		return actorMsg;
	}
	
	public void handleResp(ActorMessage responseMsg) {
		int msgId = responseMsg.getId();
		ActorCallback callback = callbacks.remove(msgId);
		if (callback == null) {
			GameLog.error("收到不存在的Actor回调, messageId:{}", msgId);
			return;
		}
		callback.onCall(responseMsg);
	}
	
}
