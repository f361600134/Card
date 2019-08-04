package com.fatiny.core.client.db;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据服路由分配处理器
 */
@Slf4j
public class DbServerRouteHandler {

	/** 数据服客户端 */
	private DbServerClient client;
	/** 轮询节点 */
	private AtomicInteger roundRobinNode = new AtomicInteger(0);
	/** 消息路由 */
	private LoadingCache<Long, RouteInfo> routeCache = CacheBuilder.newBuilder()
			.expireAfterAccess(60, TimeUnit.MINUTES) // 60分钟清缓存
			.build(
				new CacheLoader<Long, RouteInfo>() {
					public RouteInfo load(Long id) throws Exception {
						return new RouteInfo(id);
					}
			});
	
	
	DbServerRouteHandler(DbServerClient client) {
		this.client = client;
	}
	
	
	public void checkId(long id) {
		if (id < -1) {
			throw new IllegalArgumentException("不合法的消息id:" + id);
		}
	}
	
	
	private RouteInfo getRouteInfo(long id) throws Exception {
		if (id > 0) {
			return routeCache.get(id);
		} else {
			return new RouteInfo(id);
		}
	}
	
	
	public DbServerInfo allocate(long id) {
		try {
			checkId(id);
			List<DbServerInfo> serverInfos = client.getServerList();
			if (serverInfos.size() == 1) {
				return serverInfos.get(0); // 只有一个服
			}
			RouteInfo routeInfo = getRouteInfo(id);
			int index = routeInfo.getIndex();
			if (index > -1) {
				DbServerInfo exist = serverInfos.get(index);
				if (!exist.isConnect()) {
					return reallocate(id);
				}
			}
			int increment = roundRobinNode.getAndIncrement();
			index = Math.abs(increment) % serverInfos.size();
			DbServerInfo serverInfo = serverInfos.get(index);
			int serverId = serverInfo.getServerId();
			routeInfo.change(serverId, index);
			if (!serverInfo.isConnect()) {
				serverInfo = reallocate(id);
			}
			return serverInfo;
		} catch (Exception e) {
			log.error("数据服路线分配出错:{}", id, e);
			return null;	
		}
	}
	
	
	public DbServerInfo reallocate(long id) {
		try {
			List<DbServerInfo> serverInfos = client.getServerList();
			RouteInfo routeInfo = routeCache.get(id);
			for (int i = 0; i < serverInfos.size(); i++) {
				if (routeInfo.getIndex() == i) {
					continue;
				}
				DbServerInfo serverInfo = serverInfos.get(i);
				if (serverInfo.isConnect()) {
					int serverId = serverInfo.getServerId();
					routeInfo.change(serverId, i);
					return serverInfo;
				}
			}
		} catch (Exception e) {
			log.error("重新分配数据服路线出错:{}", id, e);
		}
		return null;
	}
	
	
	class RouteInfo {

		/** 保存的消息ID(玩家ID或公共业务ID) */
		private long id;
		/** 数据服ID */
		private int dbServerId;
		/** 保存的轮询索引 */
		private int index = -1;
		
		RouteInfo(long id) {
			this.id = id;
		}
		
		long getId() {
			return id;
		}

		int getIndex() {
			return index;
		}
		
		int getDbServerId() {
			return dbServerId;
		}
		
		void change(int dbServerId, int index) {
			this.dbServerId = dbServerId;
			this.index = index;
		}
	}

}
