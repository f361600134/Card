package com.fatiny.core.akka.remote;

/**
 * 远程方法调用
 * 
 * @author huachp
 */
public class RemoteMethodCall {
	
	private String className;
	private String methodName;
	private Object[] parameters;
	
	public RemoteMethodCall() {
		
	}
	
	public static RemoteMethodCall create(String methodName
			, String clsName, Object[] params) {
		RemoteMethodCall methodCall = new RemoteMethodCall();
		methodCall.className = clsName;
		methodCall.methodName = methodName;
		methodCall.parameters = params;
		return methodCall;
	}

	public String getClassName() {
		return className;
	}

	public String getMethodName() {
		return methodName;
	}

	public Object[] getParameters() {
		return parameters;
	}
	
	public boolean isParamsEmpty() {
		return parameters == null || parameters.length == 0;
	}
	
	public boolean isStub(Class<?> stubClass) {
		String stubClsName = stubClass.getName();
		return this.className.equals(stubClsName);
	}
}
