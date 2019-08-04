package com.fatiny.core.client.actor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fatiny.core.util.CRC32HashUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * typed actor 反射信息
 */
@Slf4j
public class TypedActorInfo {

	private Class<?> typedActorCls;
	private int[] methodNamesHash;
	private MethodInfo[] methodInfos;
	
	public static TypedActorInfo create(Class<?> interface0, Class<?> typedActorCls) {
		Method[] methodArr = interface0.getDeclaredMethods();
		List<Method> tempList = new ArrayList<>();
		try {
			for (Method method : methodArr) {
				tempList.add(method);
			}
			TypedActorInfo actorInfo = new TypedActorInfo();
			actorInfo.typedActorCls = typedActorCls;
			actorInfo.methodNamesHash = new int[tempList.size()];
			actorInfo.methodInfos = new MethodInfo[tempList.size()];
			for (int i = 0; i < tempList.size(); i++) {
				Method m = tempList.get(i);
				int h = CRC32HashUtil.appStrihash(m.getName());
				actorInfo.methodNamesHash[i] = h;
				actorInfo.methodInfos[i] = actorInfo.new MethodInfo(h, m);
			}
			Arrays.sort(actorInfo.methodNamesHash);
			Arrays.sort(actorInfo.methodInfos);
			return actorInfo;
		} catch (Exception e) {
			log.error("初始化TypedActor Method异常, {}", typedActorCls);
			return null;
		}
	}
	
	
	public void checkMethodInfo(String name, Object... params) {
		int h = CRC32HashUtil.appStrihash(name);
		int idx = Arrays.binarySearch(methodNamesHash, h);
		if (idx > -1) {
			MethodInfo methodInfo = methodInfos[idx];
			Method m = methodInfo.method;
			Class<?>[] paramTypes = m.getParameterTypes();
			if (paramTypes.length != params.length) {
				throw new IllegalArgumentException("不合法的参数长度");
			}
			for (int i = 0; i < paramTypes.length; i++) {
				if (paramTypes[i].isPrimitive()) {
					continue;
				}
				Class<?> checkCls = params[i].getClass();
				if (checkCls == paramTypes[i]) {
					continue;
				}
				if (!paramTypes[i].isAssignableFrom(checkCls)) {
					throw new IllegalArgumentException("不合法的参数:" + checkCls);
				}
			}
		} else {
			throw new IllegalArgumentException("找不到方法:" + name);
		}
	}
	

	public Class<?> getTypedActorCls() {
		return typedActorCls;
	}

	public int[] getMethodNamesHash() {
		return methodNamesHash;
	}
	
	public String getActorClsName() {
		return typedActorCls.getName();
	}
	
	
	private class MethodInfo implements Comparable<MethodInfo> {
		
		private int methodNameHash;
		private Method method;
		
		MethodInfo(int methodNameHash, Method method) {
			this.methodNameHash = methodNameHash;
			this.method = method;
		}
		
		@Override
		public int compareTo(MethodInfo o) {
			return this.methodNameHash < o.methodNameHash ? -1 : 1;
		}
		
	}
	
}
