package com.fatiny.core.akka.remote;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * TypedActor远程调用方法
 */
@Slf4j
public class TypedActorMethods {
	
	private Object actorObj;
	private Map<String, Method> actorMethods = new HashMap<>();
	
	
	public static TypedActorMethods create(Class<?> interface0, Object actorObj) {
		TypedActorMethods actorMethods = new TypedActorMethods();
		actorMethods.actorObj = actorObj;
		try {
			Method[] methods = interface0.getDeclaredMethods();
			for (Method method : methods) {
				actorMethods.putMethod(method);
			}
		} catch (Exception e) {
			log.error("初始化远程Actor Method Call异常, {}", interface0);
			throw e;
		}
		return actorMethods;
	}
	
	
	private void putMethod(Method method) {
		Method old = actorMethods.put(method.getName(), method);
		if (old != null) {
			throw new IllegalStateException("远程Actor调用不允许出现重复的方法名");
		}
	}
	
	
	public Object typedActorObj() {
		return actorObj;
	}
	
	public Class<?> typedActorCls() {
		return actorObj.getClass();
	}
	
	public Map<String, Method> all() {
		return actorMethods;
	}
	
	public Method get(String name) {
		return actorMethods.get(name);
	}
	
	
	public Object call(RemoteMethodCall methodCall) {
		try {
			String name = methodCall.getMethodName();
			Method method = get(name);
			if (methodCall.isParamsEmpty()) {
				return method.invoke(actorObj); 
			} 
			Object[] params = methodCall.getParameters();
			return method.invoke(actorObj, params);
		} catch (Exception e) {
			log.error("actor method call ocurr exception", e);
			Throwable actualCause = e.getCause();
			if (actualCause == null) {
				return RemoteFailure.exception("远程调用");
			} 
			String exceptionName = actualCause.getClass().getName();
			String exceptionMsg = actualCause.getMessage();
			String detail = exceptionName + ": " + exceptionMsg; 
			return RemoteFailure.exception(detail);
		}
	}
	
}
