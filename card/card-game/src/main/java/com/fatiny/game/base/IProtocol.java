package com.fatiny.game.base;

/**
 * 下发协议接口
 */
public interface IProtocol {
	
	short protocol();
	
	byte[] toBytes();
	
}
