package com.fatiny.core.net;

import java.util.List;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * kryo对象反序列化解码器
 * 
 * @author huachp
 */
public class KryoDecoder extends ByteToMessageDecoder {

	private Kryo kryo = new Kryo();
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		Input input = new Input(new ByteBufInputStream(in));
		Object message = kryo.readClassAndObject(input);
		input.close();
		out.add(message);
	}

}
