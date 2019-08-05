package com.fatiny.core.client.actor;

import com.fatiny.core.akka.remote.RemoteFailure;

/**
 * 回调任务
 * 
 */
public abstract class CallbackTask {
	
	private Object returnObj;
	
	public void response(Object returnObj) {
		this.returnObj = returnObj;
	}
	
	public void execute() {
		if (returnObj instanceof RemoteFailure) {
			RemoteFailure obj = (RemoteFailure) returnObj;
			String detail = obj.getDetail();
			throw new RuntimeException(detail);
		} else {
			callback(returnObj);
		}
	}
	
	public abstract void callback(Object returnObj);
	
}	
