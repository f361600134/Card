package com.fatiny.core.client.db;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.fatiny.core.client.db.exception.DaoException;
import com.fatiny.core.server.db.message.BatchLoadMsg;
import com.fatiny.core.server.db.message.DbServerMsg;
import com.fatiny.core.server.db.message.DbServerRespMsg;

/**
 * 消息响应
 * 
 * @author huachp
 */
public class Response {

	/** 请求消息 */
	private DbServerMsg request;
	/** 响应数据 */
	private Object data;
	/** 是否响应设值 */
	private boolean set;
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	
	public Response() {
		
	}
	
	public Response(DbServerMsg request) {
		this.request = request;
	}
	
	
	public <T> T get() {
		return (T) data;
	}

	public void set(Object data) {
		this.data = data;
		set = true;
		latch.countDown();
	}
	
	public boolean isResp() {
		return set;
	}
	
	public boolean isSuccess() {
		return isResp() && !(data instanceof DaoException);
	}
	
	public DbServerMsg getReq() {
		return request;
	}
	
	public void sync(long timeout) {
		try {
			boolean reached = latch.await(timeout, TimeUnit.MILLISECONDS);
			if (!reached) {
				throw new DaoException(request + "请求超时");
			} else if (!isSuccess()) {
				throw (DaoException) data;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void success(DbServerRespMsg responseMsg) {
		set(responseMsg.getData());
	}
	
	public void success(BatchLoadMsg responseMsg) {
		set(responseMsg);
	}
	
	public void failure(DbServerRespMsg responseMsg) {
		String failureMsg = responseMsg.getFailureMsg();
		set(new DaoException(failureMsg));
	}
	
}
