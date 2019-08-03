package com.fatiny.cardloginplus.domain;

import com.fatiny.cardloginplus.domain.entity.ActorInfo;
import com.fatiny.cardloginplus.domain.entity.ServerStatus;

public class ServerListResult {

	private String name;	//服务器名
	private Integer id;		//服务器id
	private Integer showid;		//显示列表序号
	private String ip;
	private Integer port;
	private Integer status;	//0:流畅,1:繁忙,2:火爆,3:未开服,4:维护,5:关闭
	
	private Integer old;	//是否有旧角色在该服务器，0=没有；1=有
	
	//旧角色信息
	private Integer level;
	private Integer type;

	//先调用serverInfo，再调用actorInfo(if exist)
	public void serverInfo(ServerStatus server)
	{
		this.old = 0;
		this.name = server.getName();
		this.id = server.getId();
		this.showid = server.getShowId();
		this.ip = server.getIp();
		this.port = server.getPort();
		//this.status = server.getStatus().intValue();
		if(server.getStatus().intValue()>1)
			this.status = 0;
		else
			this.status = server.getStatus().intValue();
	}
	
	public void actorInfo(ActorInfo actor)
	{
		if(actor==null)
			return;
		this.old = 1;
		this.level = actor.getLevel();
		this.type = actor.getActorType();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOld() {
		return old;
	}

	public void setOld(Integer old) {
		this.old = old;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getShowid() {
		return showid;
	}

	public void setShowid(Integer showid) {
		this.showid = showid;
	}
	
	
}
