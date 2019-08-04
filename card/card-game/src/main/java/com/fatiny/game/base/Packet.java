package com.fatiny.game.base;

import com.fatiny.core.net.DataCarrier;

import io.netty.buffer.ByteBuf;

/**
 * 数据包
 */
public class Packet {
	
	/** 协议长度 */
	public static final int PROTO_LEN = 2;
	
	private final short cmd;
	private final byte[] data;

	public Packet(short cmd, byte[] data) {
		this.cmd = cmd;
		this.data = data;
	}
	
	public short cmd() {
		return cmd;
	}
	
	public byte[] data() {
		return data;
	}
	
	
	public static Packet decode(ByteBuf byteBuf) {
		short cmd = byteBuf.readShort();
		byte[] newdata = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(newdata);
		return new Packet(cmd, newdata);
	}

	
	public static Object encode(short cmd, byte[] data) {
		return DataCarrier.protocolLen2Bytes(cmd, data);
	}
	
	
	public static Object encode(IProtocol protocol) {
		short cmd = protocol.protocol();
		byte[] data = protocol.toBytes();
		return encode(cmd, data);
	}
	
	
}	
