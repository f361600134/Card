package com.fatiny.cardloginplus.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatiny.cardloginplus.domain.entity.ActorInfo;
import com.fatiny.cardloginplus.repository.ActorInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActorService {
	
	@Autowired
	private ActorInfoRepository actorRepository;
	
	/**
	 * 获取指定账号的所有服务器上的角色
	 */
	public List<ActorInfo> getActors(String username)
	{
		if(StringUtils.isBlank(username))
			return null;
		return actorRepository.selectAllById(username);
	}
	
	/**
	 * 添加角色
	 */
	public ActorInfo insertActor(ActorInfo actorInfo)
	{
		this.actorRepository.save(actorInfo);
//		if(ret>0)
//			this.addActorToCache(username, actor);
		return actorInfo;
	}
	

}
