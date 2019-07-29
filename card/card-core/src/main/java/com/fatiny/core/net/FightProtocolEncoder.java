package com.fatiny.core.net;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;

/**
 * 战斗服协议编码器
 * 
 * @author huachp
 */
public class FightProtocolEncoder extends MessageToMessageEncoder<DataCarrier> {

	
	@Override
	protected void encode(ChannelHandlerContext ctx, DataCarrier msg, List<Object> out) throws Exception {
		int protocol = msg.getProtocol();
		byte protocolLen = msg.getProtocolLen();
		byte[] body = msg.getStructure();
		
		ByteBuf byteBuf = ctx.alloc().ioBuffer();
		byteBuf.writeInt(body.length + protocolLen); 
		writeProtocolLength(protocolLen, protocol, byteBuf);
		byteBuf.writeBytes(body);
		out.add(new DatagramPacket(byteBuf, msg.getSender()));
	}
	
	
	private void writeProtocolLength(byte protocolLen, int protocol, ByteBuf outBuffer) {
		if (protocolLen < 0) {
            throw new IllegalArgumentException(
                    "protocol num length (" + protocolLen + ") is less than zero");
        }
        switch (protocolLen) {
        case 1:
        	outBuffer.writeByte(protocol);
            break;
        case 2:
            outBuffer.writeShort(protocol);
            break;
        case 4:
            outBuffer.writeInt(protocol);
            break;
        default:
            throw new Error("should not reach here");
        }
	}
	

}
