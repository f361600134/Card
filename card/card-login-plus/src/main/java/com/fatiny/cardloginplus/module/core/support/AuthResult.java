package com.fatiny.cardloginplus.module.core.support;

import com.fatiny.cardloginplus.common.result.IResult;
import com.fatiny.cardloginplus.domain.entity.ServerStatus;

public class AuthResult extends ErrorResult {
	//成功
	private String username;//数据库账号,生成的唯一标识name
	private String name;	//服务器名
	private Integer id;		//服务器id
	private String ip;		//游戏服ip
	private Integer port;	//游戏服端口
	private Integer status;	//0:流畅,1:繁忙,2:爆满,3:未开服,4:维护,5:关闭
	private String sessionKey;	//临时登录身份
	private String webip;		//webip
	private Integer webport;	//web端口
	
	private String inputName; //玩家输入的账号
	private Integer loginSid; //登录服id
	
	protected AuthResult(ErrorCodeEnum eEnum) {
		super(eEnum);
	}
	
	/**
	 * 构造成功的返回信息
	 * 
	 * @return
	 */
	public static AuthResult success(String username, String sessionKey, ServerStatus server) {
		AuthResult result = new AuthResult(ErrorCodeEnum.SUCCESS);
		if(server!=null)
		{
			String webIp = server.getIp();
			int webPort = 8080;
//			NodeInfo webNode = NodeManager.getInstance().getNode(NodeConfig.NodeType_Web, Config.ServerGroup);
//			if(webNode==null)
//				webNode = NodeManager.getInstance().getNode(NodeConfig.NodeType_Web, 0);
//			if(webNode!=null)
//			{
//				webIp = webNode.getServerAddress();
//				webPort = webNode.getServerPort();
//			}
			
			result.username = username;
			result.name = server.getName();
			result.id = server.getId();
			result.ip = server.getIp();
			result.port = server.getPort();
			//result.status = server.getStatus().intValue();
			if(server.getStatus().intValue()>1)
				result.status = 0;
			else
				result.status = server.getStatus().intValue();
			result.sessionKey = sessionKey;
			result.webip = webIp;
			result.webport = webPort;
		}
		return result;
	}
	
	/**
	 * 构造成功的返回信息
	 * @param username 玩家输入账号, 以前定死的, 客户端不让改
	 * @param uniqueName 生成的唯一账号, 用于获取账号信息
	 * @param sessionKey 
	 * @param server
	 * @return  
	 * @return AuthResult  
	 * @date 2019年5月15日下午3:33:08
	 */
	public static AuthResult success(String inputName, String username, String sessionKey, ServerStatus server) {
		AuthResult result = new AuthResult(ErrorCodeEnum.SUCCESS);
		if(server!=null)
		{
			String webIp = server.getIp();
			int webPort = 8080;
//			NodeInfo webNode = NodeManager.getInstance().getNode(NodeConfig.NodeType_Web, Config.ServerGroup);
//			if(webNode==null)
//				webNode = NodeManager.getInstance().getNode(NodeConfig.NodeType_Web, 0);
//			if(webNode!=null)
//			{
//				webIp = webNode.getServerAddress();
//				webPort = webNode.getServerPort();
//			}
			
			result.inputName = inputName;
			result.username = username;
			result.name = server.getName();
			result.id = server.getId();
			result.ip = server.getIp();
			result.port = server.getPort();
			//result.status = server.getStatus().intValue();
			if(server.getStatus().intValue()>1)
				result.status = 0;
			else
				result.status = server.getStatus().intValue();
			result.sessionKey = sessionKey;
			result.webip = webIp;
			result.webport = webPort;
//			result.loginSid = Config.ServerId;
		}
		return result;
	}

	/**
	 * 构造消息
	 * @return
	 */
	public static IResult build(ErrorCodeEnum codeEnum) {
		return new AuthResult(codeEnum);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getWebip() {
		return webip;
	}

	public void setWebip(String webip) {
		this.webip = webip;
	}

	public Integer getWebport() {
		return webport;
	}

	public void setWebport(Integer webport) {
		this.webport = webport;
	}

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public Integer getLoginSid() {
		return loginSid;
	}

	public void setLoginSid(Integer loginSid) {
		this.loginSid = loginSid;
	}
}
