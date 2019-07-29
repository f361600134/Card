package com.fatiny.core.server.main.dispatcher;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fatiny.core.server.main.GameSession;
import com.fatiny.core.util.GameLog;
import com.fatiny.core.util.MessageOutput;
import com.google.protobuf.GeneratedMessageLite;

/**
 * Dispatcher
 *
 * handler函数格式:
 * <pre>
 * &#064;Cmd(id=xx)
 * public void xxx(GameSession session, ProtobufObj params) {...}
 * </pre>
 *
 * @author huachp
 * 
 */
public class Dispatcher {

	private Map<Short, Commander> commanders = new HashMap<>();
	
	
	/**
	 * 协议加载
	 * 
	 * @param classes	逻辑类
	 */
	public synchronized void load(Collection<Class<?>> classes) {

		Map<Short, Commander> newCommanders = new HashMap<>();

		for (Class<?> cls : classes) {
			try {
				Object o = cls.newInstance();
				Method[] methods = cls.getDeclaredMethods();

				for (Method method : methods) {
					Cmd cmd = method.getAnnotation(Cmd.class);
					if (cmd != null) {
						newCommanders.put(cmd.id(), new Commander(o, cmd.mustLogin(), method));
					}
				}
			} catch (Exception e) {
				GameLog.error("协议加载过程出现异常", e);
			}
		}

		commanders = newCommanders;
	}


	/**
	 * 协议调用
	 * 
	 * @param session	玩家会话信息
	 * @param cmd		协议号
	 * @param bytes		协议体
	 * @throws Exception
	 */
	public void invoke(GameSession session, short cmd, byte[] bytes) throws Exception {
		Commander commander = commanders.get(cmd);
		if (commander != null) {
			long begin = System.currentTimeMillis();

			Method parser = commander.protobufParser;
			
			GeneratedMessageLite params = (GeneratedMessageLite) parser.invoke(null, bytes);
			
			GameLog.debug("收到协议[{}], pid={}, params={}, size={}B",
					cmd, session.getPlayerId(), MessageOutput.create(params), bytes.length);

			commander.method.invoke(commander.o, session, params);

			long used = System.currentTimeMillis() - begin;

			GameLog.debug("协议[{}]处理完成，耗时{}ms", cmd, used);

			// 协议处理超过1秒
			if (used > 1000) {
				GameLog.error("协议[{}]处理慢!!!耗时{}ms", cmd, used);
			}

		}
	}
	

	public Commander getCommander(short cmd) {
		return commanders.get(cmd);
	}
	
	
	
	public static class Commander {
		
		private final Object o;
		private final boolean mustLogin;
		private final Method method;
		private final Method protobufParser;

		public Commander(Object o, boolean mustLogin, Method method) throws Exception {
			this.o = o;
			this.mustLogin = mustLogin;
			this.method = method;
			Class<?> paramType = method.getParameterTypes()[1];
			this.protobufParser = paramType.getMethod("parseFrom", byte[].class);
		}

		public boolean isMustLogin() {
			return mustLogin;
		}
		
	}
	
	
}
