package com.fatiny.cardloginplus.module.core.base;

/**
 * 创建订单参数, 游戏客户端和服务器使用, 前端必要参数
 * 
 * @auth Jeremy
 * @date 2019年4月29日下午3:04:17
 */
public class PreOrderParam {
	
	private String ch;	//渠道号
	private String actorId; //角色id, 对应游戏服player里面的id
	private String userName; //账号
	
	private String payItemId; //购买商品id
	private String amount; //充值金额
	
	private String serverId; //游戏服务器id
//	private String serverIp; //游戏服务器ip
//	private String port; //游戏服务器端口
	
	public PreOrderParam() {
	}

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public String getActorId() {
		return actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPayItemId() {
		return payItemId;
	}

	public void setPayItemId(String payItemId) {
		this.payItemId = payItemId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

//	public String getServerIp() {
//		return serverIp;
//	}
//
//	public void setServerIp(String serverIp) {
//		this.serverIp = serverIp;
//	}
//
//	public String getPort() {
//		return port;
//	}
//
//	public void setPort(String port) {
//		this.port = port;
//	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
