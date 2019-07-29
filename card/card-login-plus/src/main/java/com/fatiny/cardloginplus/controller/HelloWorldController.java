package com.fatiny.cardloginplus.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatiny.cardloginplus.domain.entity.ActorInfo;
import com.fatiny.cardloginplus.repository.ActorInfoRepository;

@RestController
public class HelloWorldController {
	
	@Autowired
	private ActorInfoRepository actorInfoRepository;

	@RequestMapping("/index")
	public String index() {
		return "Hello World";
	}

	@RequestMapping(value = "/param2")
	public String param2(@RequestParam Map<String, String> parameter) {
		System.out.println(parameter); 
		return parameter.toString();
	}
	
	@RequestMapping(value = "/save")
	public String save(@RequestParam Map<String, String> parameter) {
		ActorInfo info = new ActorInfo();
		info.setActorId(1);
		info.setActorType(1);
		info.setLastUpdate(new Date());
		info.setLevel(1);
		info.setName("aa");
		info.setServerId(1);
		info.setUserName("aa");
		actorInfoRepository.save(info);
		return info.toString();
	}

}
