package com.fatiny.core.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 游戏协议编码器
 * 
 * @author huachp
 */
public class GameProtocolEncoder extends MessageToByteEncoder<DataCarrier> {

	@Override
	protected void encode(ChannelHandlerContext ctx, DataCarrier msg, ByteBuf out) throws Exception {
		int protocol = msg.getProtocol();
		byte protocolLen = msg.getProtocolLen();
		byte[] body = msg.getStructure();
		
		writeProtocolLength(protocolLen, protocol, out);
		out.writeBytes(body);
	}
	
	
	private void writeProtocolLength(byte protocolLen, int protocol, ByteBuf outBuffer) {
		if (protocolLen < 0) {
            throw new IllegalArgumentException(
                    "protocol num length (" + protocolLen + ") is less than zero");
        }
        switch (protocolLen) {
        case 1:
        	outBuffer.writeByte((byte) protocol);
            break;
        case 2:
            outBuffer.writeShort((short) protocol);
            break;
        case 4:
            outBuffer.writeInt(protocol);
            break;
        default:
            throw new Error("should not reach here");
        }
	}

}
