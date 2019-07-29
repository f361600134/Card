package com.fatiny.core.server.main.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.fatiny.core.server.main.GameSession;

/**
 * session管理
 * 
 * @author huachp
 */
public class SessionGroup {
	
	private static ConcurrentMap<Long, GameSession> sessions = new ConcurrentHashMap<>();
	
	
	public static GameSession getSession(long id) {
		GameSession session = sessions.get(id);
		return session;
	}
	
	public static void saveSession(GameSession session) {
		sessions.put(session.getPlayerId(), session);
	}
	
	
	public static Collection<GameSession> allSessions() {
		return new ArrayList<>(sessions.values());
	}
	
	
	public static void closeAll() {
		for (GameSession session : sessions.values()) {
			session.disConnect();
		}
		sessions.clear();
	}
	
	
	public static boolean closeSession(long id) {
		return sessions.remove(id) != null;
	}
	
	
	public static int onlineMembers() {
		return sessions.size();
	}
	
	
}
