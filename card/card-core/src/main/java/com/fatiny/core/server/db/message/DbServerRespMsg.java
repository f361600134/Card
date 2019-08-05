package com.fatiny.core.server.db.message;

import java.util.List;

import com.fatiny.core.client.db.Response;

/**
 * 数据服响应协议
 * 
 */
public class DbServerRespMsg extends AbstractDbServerMsg {

	/** 业务实体ID */
	private long id;
	/** 协议cmd */
	private Command cmd;
	/** 查询数据 */
	private List data;
	/** 是否失败 */
	private boolean failure;
	/** 失败消息 */
	private String failureMsg;
	
	
	public static DbServerRespMsg createLoadResp(DbServerMsg reqMsg, List data) {
		DbServerRespMsg respMsg = new DbServerRespMsg(reqMsg);
		respMsg.data = data;
		return respMsg;
	}
	
	
	public static DbServerRespMsg createSaveResp(DbServerMsg reqMsg) {
		DbServerRespMsg respMsg = new DbServerRespMsg(reqMsg);
		return respMsg;
	}
	
	
	public static DbServerRespMsg createFailResp(DbServerMsg reqMsg, String failMsg) {
		DbServerRespMsg respMsg = new DbServerRespMsg(reqMsg);
		respMsg.failure = true;
		respMsg.failureMsg = failMsg;
		return respMsg;
	}
	
	
	public DbServerRespMsg() {
		
	}
	
	public DbServerRespMsg(DbServerMsg reqMsg) {
		this.id = reqMsg.getId();
		this.cmd = reqMsg.getCmd();
		injectCallbackId(reqMsg.callbackId());
	}
	
	
	public DbServerRespMsg(List data) {
		this.data = data;
	}
	

	public List getData() {
		return data;
	}

	public boolean isFailure() {
		return failure;
	}

	public String getFailureMsg() {
		return failureMsg;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public Command getCmd() {
		return cmd;
	}


	@Override
	public void doResponse(Response response) {
		if (isFailure()) {
			response.failure(this);
		} else {
			response.success(this);
		}
	}
	
}
