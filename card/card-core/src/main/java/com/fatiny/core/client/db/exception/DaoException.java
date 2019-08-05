package com.fatiny.core.client.db.exception;

/**
 * 数据服请求异常
 * 
 */
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = -2201139268160284399L;
	
	public DaoException(String message) {
		super(message);
	}

}
