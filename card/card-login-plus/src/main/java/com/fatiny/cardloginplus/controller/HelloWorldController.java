package com.fatiny.cardloginplus.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatiny.cardloginplus.common.generator.SnowflakeGenerator;
import com.fatiny.cardloginplus.domain.entity.ActorInfo;
import com.fatiny.cardloginplus.domain.entity.ServerStatus;
import com.fatiny.cardloginplus.repository.ActorInfoRepository;
import com.fatiny.cardloginplus.repository.ServerStatusRepository;

@RestController
public class HelloWorldController {
	
	@Autowired
	private ActorInfoRepository actorInfoRepository;
	
	@Autowired
	private ServerStatusRepository serverStatusRepository;
	
	@Autowired
	private SnowflakeGenerator generator;

	@RequestMapping("/index")
	public String index() {
		System.out.println("=====================");
		return "Hello World";
	}

	@RequestMapping(value = "/param2")
	public String param2(@RequestParam Map<String, String> parameter) {
		System.out.println(parameter); 
		return parameter.toString();
	}
	
	@RequestMapping(value = "/save")
	public ActorInfo save(@RequestParam Map<String, String> parameter) {
		ActorInfo info = new ActorInfo();
		info.setActorId(1);
		info.setActorType(1);
		info.setLastUpdate(new Date());
		info.setLevel(1);
		info.setName("aa");
		info.setServerId(1);
		info.setUserName("aa");
		actorInfoRepository.save(info);
		return info;
	}
	
	@RequestMapping(value = "/save2")
	public String save2(@RequestParam Map<String, String> parameter) {
		ServerStatus info = new ServerStatus();
		info.setId(1);
		info.setName("aa");
		info.setIp("aa");
		info.setIsRecommand(true);
		info.setIsTest(true);
		info.setInnerIp("aa");
		info.setPort(8080);
		info.setShowId(1);
		info.setStatus((short)1);
		serverStatusRepository.save(info);
		return info.toString();
	}
	
	@RequestMapping("/generator")
	public String generator() {
		long id = generator.nextId();
		return Long.toString(id);
	}


}
