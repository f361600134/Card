package com.fatiny.cardloginplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatiny.cardloginplus.common.result.IResult;
import com.fatiny.cardloginplus.common.result.SystemCodeEnum;
import com.fatiny.cardloginplus.common.result.SystemResult;
import com.fatiny.cardloginplus.service.ServerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/server")
public class ServerController {
	
	@Autowired
	private ServerService serverService;
	
	@RequestMapping("/reload")
	public IResult reload(){
		try{
			serverService.init();
		}
		catch(Exception ex){
			log.error("reload error! ", ex);
			return SystemResult.build(SystemCodeEnum.ERROR_RELOAD);
		}
//		Collection<ServerStatus> list = serverService.getAllServerList();
//		String ret = JSONObject.toJSONString(list);
//		response.content().writeCharSequence(ret, CharsetUtil.UTF_8);
//		
//		Map<Integer, List<ServerStatus>> map = serverService.getAllChannelServer();
//		ret = JSONObject.toJSONString(map);
//		response.content().writeCharSequence(ret, CharsetUtil.UTF_8);
		return SystemResult.build(SystemCodeEnum.SUCCESS);
	}
	
}
