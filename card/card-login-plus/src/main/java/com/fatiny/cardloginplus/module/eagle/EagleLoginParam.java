package com.fatiny.cardloginplus.module.eagle;

import com.fatiny.cardloginplus.module.core.base.AbstractLoginParam;

/**
 * Eagle 登录参数对象
 * @auth Jeremy
 * @date 2019年4月28日下午2:50:48
 */
public class EagleLoginParam extends AbstractLoginParam{
	
	private String gameId;
	private String token;
	
	public EagleLoginParam() {
	}
	
	public String getGameId() {
		return gameId;
	}
	public String getToken() {
		return token;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
