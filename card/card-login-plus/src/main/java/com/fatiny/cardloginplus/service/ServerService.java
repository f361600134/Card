package com.fatiny.cardloginplus.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatiny.cardloginplus.domain.entity.ChannelServer;
import com.fatiny.cardloginplus.domain.entity.ServerStatus;
import com.fatiny.cardloginplus.repository.ChannelServerRepository;
import com.fatiny.cardloginplus.repository.ServerStatusRepository;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class ServerService {
	
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ServerStatusRepository serverStatusRepository;
	
	@Autowired
	private ChannelServerRepository chennelServerRepository;
	
	//serverId-->server
	private Map<Integer, ServerStatus> servers = Maps.newLinkedHashMap();
	
	//channelId-->server list
	private Map<Integer, List<ServerStatus>> channelServers = Maps.newHashMap();
	
	//channelId-->recommand serverId
	private Map<Integer, Integer> channelRecommandServers = Maps.newHashMap();
	
	/**
	 * 更新服务器
	 */
	public boolean updateServer(ServerStatus server)
	{
		server = this.serverStatusRepository.save(server);
		if(server != null) {
			this.servers.put(server.getId(), server);
			return true;
		}
		return false;
	}
	
	/**
	 * 删除服务器
	 */
	public boolean deleteServer(int id)
	{
		this.serverStatusRepository.deleteById(id);
		boolean bool = this.serverStatusRepository.existsById(id);
		if(bool)
			this.servers.remove(id);
		return bool;
	}
	/**
	 * 获取指定渠道的所有服务器
	 */
	public List<ServerStatus> getServersForChannel(int channel)
	{
		return this.channelServers.get(channel);
	}
	
	/**
	 * 设置服务器给渠道
	 */
	public boolean setServerToChannel(int id, int channel){
		ServerStatus server = this.getServer(id);
		if(server==null)
			return false;	//服务器不存在
		List<ServerStatus> chservers = this.getServersForChannel(channel);
		if(chservers!=null)
		{
			for(ServerStatus s : chservers)
			{
				if(s.getId() == id)
					return false;	//记录已存在
			}
		}
		ChannelServer chserver = new ChannelServer();
		chserver.setChannel(channel);
		chserver.setServerid(id);
		chserver = chennelServerRepository.save(chserver);
		if (chserver != null)
		{
			this.setChannelServer(chserver);
			return true;
		}
		return false;
	}
	
	/**
	 * 回收服务器从渠道
	 */
	public boolean removeServerFromChannel(int id, int channel){
		int defaultServer = this.channelRecommandServers.getOrDefault(channel, 0);
		if(id == defaultServer)
		{//推荐服务器
			this.channelRecommandServers.remove(channel);
		}
		ChannelServer chserver = new ChannelServer();
		chserver.setChannel(channel);
		chserver.setServerid(id);
		chennelServerRepository.deleteById(id);
		boolean ret = chennelServerRepository.existsById(id);
		if (ret)
		{
			List<ServerStatus> channelservers = channelServers.get(channel);
			Iterator<ServerStatus> it = channelservers.iterator();
			while(it.hasNext())
			{
				ServerStatus server = it.next();
				if(server.getId() == id)
				{
					it.remove();
					break;
				}
			}
		}
		return ret;
	}	
	
	public static void main(String[] args) {
		List<Integer> channelservers = Lists.newArrayList(12,2,3,4,5,6);
		Iterator<Integer> itor = channelservers.iterator();
		itor.forEachRemaining(server -> {
			System.out.println(server);
			if (server == 2) {
				itor.remove();
			}
		});
//		System.out.println(channelservers);
	}
	
	/**
	 * 重新从数据库load服务器列表
	 */
	private void reloadServers() {
		servers.clear();
		Iterable<ServerStatus> rets = serverStatusRepository.findAll();
		rets.forEach(ret -> servers.put(ret.getId(), ret));
	}
	
	/**
	 * 重新从数据库load渠道服务器关系表
	 */
	private void reloadChannelServers() {
		channelServers.clear();
		channelRecommandServers.clear();
		List<ChannelServer> rets = chennelServerRepository.selectByOrder();
		rets.forEach(ret -> setChannelServer(ret));
	}
	
	private void setChannelServer(ChannelServer chserver)
	{
		List<ServerStatus> channelservers = channelServers.get(chserver.getChannel());
		if(channelservers==null)
		{
			channelservers = Lists.newArrayList();
			channelServers.put(chserver.getChannel(), channelservers);
		}
		
		ServerStatus server = servers.get(chserver.getServerid());
		if(server==null)
		{
			logger.error("not found server for channel: {}", chserver.getChannel());
			return;
		}
//		else if(server.getIsTest() && chserver.getChannel() != Config.CHANNEL_BUSINESS)
//			return;		//测试服务器不能对外
		
		channelservers.add(server);
		logger.info("reload channel server from db: {}", chserver);

		lookupChannelRecommand(chserver.getChannel(), chserver.getServerid());
	}
	
	private void lookupChannelRecommand(int channel, int serverId)
	{
		ServerStatus server = servers.get(serverId);
		if(server.getIsRecommand())
		{
			channelRecommandServers.put(channel, serverId);
			logger.info("lookup default recommand server {} for channel: {}", serverId, channel);
		}
	}
	
	
	/**
	 * 获取指定服务器
	 * 0:流畅,1:繁忙,2:爆满,3:未开服,4:维护,5:关闭
	 */
	public ServerStatus getServer(int serverId)
	{
		return this.servers.get(serverId);
	}
	
	/**
	 * 获取指定服务器
	 */
	public ServerStatus getServer(int serverId, int channel)
	{
		ServerStatus serverStatus = servers.get(serverId);
		if (serverStatus == null) {
			serverStatus = getRecommandServer(channel);
		}
		return serverStatus;
	}
	
	/**
	 * 获取指定渠道所推荐的默认服务器
	 * 0:流畅,1:繁忙,2:爆满,3:未开服,4:维护,5:关闭
	 */
	public ServerStatus getRecommandServer(int channel)
	{
		Integer recommand = this.channelRecommandServers.get(channel);
		if(recommand==null)
		{
			List<ServerStatus> serverForChannel = this.channelServers.get(channel);
			if(serverForChannel==null)
				return null;
			for(ServerStatus server : serverForChannel)
			{
				recommand = server.getId();
				break;
			}
		}
		if(recommand==null)
			return null;
		return this.servers.get(recommand);
	}
}
