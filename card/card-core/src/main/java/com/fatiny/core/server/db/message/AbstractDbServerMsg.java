package com.fatiny.core.server.db.message;

import com.fatiny.core.client.db.Response;

/**
 * 消息抽象层
 * 
 * @author huachp
 */
public abstract class AbstractDbServerMsg implements DbServerMsg {
	
	/** 消息回调ID */
	protected int callbackId;
	/** 执行开始时间戳(测试用) */
	protected long startTime;
	
	@Override
	public int callbackId() {
		return callbackId;
	}

	@Override
	public void injectCallbackId(int callbackId) {
		this.callbackId = callbackId;
	}
	
	
	public void recordTime() {
		startTime = System.nanoTime();
	}
	
	
	public float calcWasteTime() {
		return (System.nanoTime() - startTime) / 1000000.0f;
	}

	
	@Override
	public void doResponse(Response response) {
		// do nothing
	}
	
}
