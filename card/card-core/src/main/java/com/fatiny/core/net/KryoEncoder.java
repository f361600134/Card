package com.fatiny.core.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * kryo对象序列化编码器
 * 
 * @author huachp
 */
public class KryoEncoder extends MessageToByteEncoder<Object> {

	private Kryo kryo = new Kryo();
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		Output output = new Output(new ByteBufOutputStream(out));
		kryo.writeClassAndObject(output, msg);
		output.flush();
		output.close();
	}

}
