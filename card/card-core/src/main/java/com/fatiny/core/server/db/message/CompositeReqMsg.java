package com.fatiny.core.server.db.message;

import java.util.LinkedList;

/**
 * 组合请求包
 * 
 * @author huachp
 */
public class CompositeReqMsg extends AbstractDbServerMsg {

	private LinkedList<DbServerMsg> requestMsgs = new LinkedList<>();
	
	public CompositeReqMsg() {
		
	}
	
	/**
	 * 如果你敢这样调用 addComponent(null, null, null)... 我保证不打死你
	 * 
	 * @param components 
	 */
	public void addComponent(DbServerMsg... components) {
		if (components != null) {
			for (int i = 0; i < components.length; i++) {
				DbServerMsg reqMsg = components[i];
				requestMsgs.add(reqMsg);
			}
		}
	}
	
	
	public void addOne(DbServerMsg reqServerMsg) {
		requestMsgs.add(reqServerMsg);
	}
	
	public LinkedList<DbServerMsg> getRequestMsgs() {
		return requestMsgs;
	}
	
	public int componentSize() {
		return requestMsgs.size();
	}

	
	@Override
	public long getId() {
		return 0;
	}

	@Override
	public Command getCmd() {
		return null;
	}

}
