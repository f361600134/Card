package com.fatiny.cardloginplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatiny.cardloginplus.domain.entity.Account;
import com.fatiny.cardloginplus.domain.entity.ActorInfo;
import com.fatiny.cardloginplus.domain.entity.ServerStatus;
import com.fatiny.cardloginplus.service.AccountService;
import com.fatiny.cardloginplus.service.ActorService;
import com.fatiny.cardloginplus.service.ServerService;

import lombok.extern.slf4j.Slf4j;

/**
 * 账户校验
 * @auth Jeremy
 * @date 2019年8月2日下午11:57:27
 */

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountAuthController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private ServerService serverService;
	@Autowired
	private ActorService actorService;
	
	/**
	 * 用于游戏服登陆验证
	 * @test http://localhost:8181/chole/account/auth?userName=bb&channel=1&sessionKey=asdf&level=1&serverId=1
	 * @date 2019年8月3日下午10:02:27
	 */
	@RequestMapping("/auth")
	public int doAuth(String userName, int channel, String sessionKey, int serverId) {
		log.info("login on LoginAuthService: userName={} channel={} sessionKey={}, serverId:{}", userName, channel, sessionKey, serverId);
		Account account = accountService.getAccount(userName);
		log.info("login on LoginAuthService: account={} ", account);
		if(account==null)
			return 3;
		log.info("login on LoginAuthService: account={} oldServer={}  server={}", account, account.getLastServer(), serverId);
		if(account.getLastServer()!=serverId){
			account.setLastServer(serverId);
			accountService.updateAccount(account);
		}
		log.info("login on LoginAuthService: userName={} ", userName);
		String ssKey = accountService.getSessionKey(userName);
		log.info("login on LoginAuthService: ssKey={} sessionKey={}", ssKey, sessionKey);
		if(ssKey==null || !ssKey.equalsIgnoreCase(sessionKey))
			return 3;
		ServerStatus serverStatus = serverService.getServer(serverId);
		if(serverStatus==null)
			return 1;
		return 0;
	}
	
	/**
	 * 保存角色
	 * @test http://localhost:8181/chole/account/create?actorId=1&userName=bb&name=bb&actorType=1&level=1&serverId=1
	 */
	@RequestMapping("/save")
	public void save(ActorInfo actorInfo) {
		log.info("actorInfo:{}", actorInfo);;
		actorService.insertActor(actorInfo);
	}
	
	
}
