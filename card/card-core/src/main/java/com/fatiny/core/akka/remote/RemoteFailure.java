package com.fatiny.core.akka.remote;

import java.rmi.RemoteException;

import akka.actor.Status;

/**
 * 远程失败
 * 
 * @author huachp
 */
public class RemoteFailure {
	
	static final String FAILURE_1 = "服务端Actor存根[%s]与消息Actor[%s]类型不一致";
	static final String FAILURE_2 = "服务端Actor业务逻辑出错:[%s]";
	
	private String detail;
	
	public static RemoteFailure illegal(String actorType0, String actorType1) {
		String detail = String.format(FAILURE_1, actorType0, actorType1);
		return new RemoteFailure(detail);
	}
	
	public static RemoteFailure exception(String exception) {
		String detail = String.format(FAILURE_2, exception);
		return new RemoteFailure(detail);
	}
	
	public RemoteFailure() {
		
	}
	
	public RemoteFailure(String detail) {
		this.detail = detail;
	}
	
	public String getDetail() {
		return detail;
	}
	
	public Status.Failure statusFail() {
		Exception e = new RemoteException(detail);
		return new Status.Failure(e);
	}
	
}
