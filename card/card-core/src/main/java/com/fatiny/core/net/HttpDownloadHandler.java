package com.fatiny.core.net;

import java.io.File;
import java.io.FileOutputStream;

import com.fatiny.core.util.HttpDownloadClient.DownloadDoneListener;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.internal.SystemPropertyUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpDownloadHandler extends ChannelInboundHandlerAdapter {
	private boolean readingChunks = false; // 分块读取开关
	private FileOutputStream fOutputStream = null;// 文件输出流
	private File localfile = null;// 下载文件的本地对象
	private String local = null;// 待下载文件名
	private int succCode;// 状态码
	private DownloadDoneListener doneListener;

	public HttpDownloadHandler(String local, DownloadDoneListener doneListener) {
		this.local = local;
		this.doneListener = doneListener;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof HttpResponse) {// response头信息
			HttpResponse response = (HttpResponse) msg;
			succCode = response.status().code();
			if (succCode == 200) {
				setDownLoadFile();// 设置下载文件
				readingChunks = true;
			}
			log.debug("CONTENT_TYPE:{}", response.headers().get(HttpHeaderNames.CONTENT_TYPE));
		}
		if (msg instanceof HttpContent) {// response体信息
			HttpContent chunk = (HttpContent) msg;
			if (chunk instanceof LastHttpContent) {
				readingChunks = false;
			}

			ByteBuf buffer = chunk.content();
			byte[] dst = new byte[buffer.readableBytes()];
			if (succCode == 200) {
				while (buffer.isReadable()) {
					buffer.readBytes(dst);
					fOutputStream.write(dst);
					buffer.release();
				}
				if (null != fOutputStream) {
					fOutputStream.flush();
				}
			}

		}
		if (!readingChunks) {
			if (null != fOutputStream) {
				log.info("Download done->{}", localfile.getAbsolutePath());
				fOutputStream.flush();
				fOutputStream.close();
				localfile = null;
				fOutputStream = null;
				doneListener.downloadComplete();
			}
			ctx.channel().close();
		}
	}

	/**
	 * 配置本地参数，准备下载
	 */
	private void setDownLoadFile() throws Exception {
		if (null == fOutputStream) {
			local = SystemPropertyUtil.get("user.dir") + File.separator + local;
			localfile = new File(local);
			File parentDir = localfile.getParentFile();
			if (parentDir != null && !parentDir.exists()) {
				parentDir.mkdirs();
			}
			if (!localfile.exists()) {
				localfile.createNewFile();
			}
			fOutputStream = new FileOutputStream(localfile);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.error("管道异常：{}", cause.getMessage(), cause);
		ctx.channel().close();
	}
	
}