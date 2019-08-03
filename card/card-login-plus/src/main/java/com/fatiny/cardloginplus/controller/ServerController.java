package com.fatiny.cardloginplus.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fatiny.cardloginplus.common.result.SystemCodeEnum;
import com.fatiny.cardloginplus.domain.ServerListResult;
import com.fatiny.cardloginplus.domain.entity.ActorInfo;
import com.fatiny.cardloginplus.domain.entity.ServerStatus;
import com.fatiny.cardloginplus.service.ActorService;
import com.fatiny.cardloginplus.service.ServerService;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/server")
public class ServerController {
	
	@Autowired
	private ServerService serverService;
	
	@Autowired
	private ActorService actorService;
	
	/**
	 * 刷新服务器列表
	 * @test http://localhost:8181/chole/server/reload
	 */
	@RequestMapping("/reload")
	public String reload(){
		try{
			serverService.init();
		}catch(Exception ex){
			log.error("reload error! ", ex);
			return SystemCodeEnum.ERROR_RELOAD.getDesc();
		}
		Map<Integer, List<ServerStatus>> map = serverService.getAllChannelServer();
		log.info("map:{}", map);
		return JSONObject.toJSONString(map);
	}
	
	/**
	 * 服务器列表
	 * @test http://localhost:8181/chole/server/list?ch=99&username=aa
	 */
	@RequestMapping("/list")
	public String list(@RequestParam("ch") Integer channel, @RequestParam("username") String username){
		List<ServerListResult> resultList = Lists.newArrayList();
		log.info("ch:{}, username:{}", channel, username);
		try{
			List<ServerStatus> servers = serverService.getServersForChannel(channel);
			List<ActorInfo> actors = actorService.getActors(username);
			if(servers!=null){
				for(ServerStatus server : servers){
					ServerListResult result = new ServerListResult();
					result.serverInfo(server);
					ActorInfo actor = this.getActorInServer(server.getId(), actors);
					if (actor == null)
						continue;
					result.actorInfo(actor);
					resultList.add(result);
				}
			}
		}catch(Exception ex){
			log.error("server list error! ", ex);
		}
		return JSONObject.toJSONString(resultList);
	}
	
	private ActorInfo getActorInServer(int serverId, List<ActorInfo> actors){
		if(actors==null)
			return null;
		for(ActorInfo actor : actors){
			if(actor.getServerId() == serverId)
				return actor;
		}
		return null;
	}
	
}
