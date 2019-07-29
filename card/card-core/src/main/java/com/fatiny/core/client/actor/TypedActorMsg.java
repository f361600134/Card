package com.fatiny.core.client.actor;

/**
 * typed actor message
 * 
 * @author huachp
 */
public class TypedActorMsg {

	private String methodName;
	private Object[] params;
	private CallbackTask callbackTask;
	
	public static TypedActorMsg create(String methodName, Object... params) {
		TypedActorMsg actorMsg = new TypedActorMsg();
		actorMsg.methodName = methodName;
		actorMsg.params = params;
		return actorMsg;
	}
	
	public String getMethodName() {
		return methodName;
	}

	public Object[] getParams() {
		return params;
	}

	public CallbackTask getCallback() {
		return callbackTask;
	}
	
	public void lazyCall(CallbackTask task) {
		this.callbackTask = task;
	}

}
