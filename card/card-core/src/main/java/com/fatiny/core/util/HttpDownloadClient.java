package com.fatiny.core.util;

import java.net.URI;

import com.fatiny.core.net.HttpDownloadHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpDownloadClient {
	
	
	/**
	 * 下载http资源 向服务器下载直接填写要下载的文件的相对路径 （↑↑↑建议只使用字母和数字对特殊字符对字符进行部分过滤可能导致异常↑↑↑）
	 * 向互联网下载输入完整路径
	 * 
	 * @param host  目的主机ip或域名
	 * @param port  目标主机端口
	 * @param url  文件路径
	 * @param local  本地存储路径
	 * @throws Exception
	 */
	public void connect(String host, int port, String url, String local, DownloadDoneListener listener) {
		final EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChildChannelHandler(local, listener));

			// Start the client.
			ChannelFuture f = b.connect(host, port).sync();

			URI uri = new URI(url);
			DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
					uri.toASCIIString());

			// 构建http请求
			request.headers().set(HttpHeaderNames.HOST.toString(), host);
			request.headers().set(HttpHeaderNames.CONNECTION.toString(), HttpHeaderValues.KEEP_ALIVE);
			request.headers().set(HttpHeaderNames.CONTENT_LENGTH.toString(), request.content().readableBytes());
			// 发送http请求
			f.channel().write(request);
			f.channel().flush();
			f.channel().closeFuture().addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					workerGroup.shutdownGracefully();
				}
			});
		} catch (Exception ex) {
			log.error("http下载出错", ex);
		}
	}

	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
		String local;
		DownloadDoneListener doneListener;
		
		public ChildChannelHandler(String local, DownloadDoneListener doneListener) {
			this.local = local;
			this.doneListener = doneListener;
		}

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			// 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
			ch.pipeline().addLast(new HttpResponseDecoder());
			// 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
			ch.pipeline().addLast(new HttpRequestEncoder());
			ch.pipeline().addLast(new ChunkedWriteHandler());
			ch.pipeline().addLast(new HttpDownloadHandler(local, doneListener));
		}

	}
	
	public interface DownloadDoneListener {
		
		public static final DownloadDoneListener EMPTY = new DownloadDoneListener() {
			@Override
			public void downloadComplete() {}
		};
		
		void downloadComplete();
	}
	

	public static void main(String[] args) throws Exception {
		HttpDownloadClient client = new HttpDownloadClient();
		client.connect("shootcdn.good321.net", 80, "https://shootcdn.good321.net/res/server/compressed.zip", "resources/res.zip", DownloadDoneListener.EMPTY);
	}
}
