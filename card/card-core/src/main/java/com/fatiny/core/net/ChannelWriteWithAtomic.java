package com.fatiny.core.net;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * channel writer
 * 
 */
public class ChannelWriteWithAtomic {
	
	private final Channel channel;
	private final AtomicBoolean needFlush = new AtomicBoolean(false);
	private final AtomicInteger needFlushCount = new AtomicInteger(0);
	private Runnable flushTask = new FlushTask();

	public ChannelWriteWithAtomic(final Channel channel) {
		this.channel = channel;
	}
	
	public void write(Object value) {
		final ChannelFuture future = channel.write(value);
        future.addListener(new GenericFutureListener<Future<? super Void>>() {
			@Override
			public void operationComplete(Future<? super Void> future) throws Exception {
				// flush end
				if (future.isSuccess() && needFlushCount.getAndSet(0) > 0) {
					if (!needFlush.getAndSet(true)) {
						channel.pipeline().lastContext().executor().execute(flushTask);
					}
				}
			}
		});
		
		if (!needFlush.getAndSet(true)) {
			channel.pipeline().lastContext().executor().execute(flushTask);
		} else {
			needFlushCount.incrementAndGet();
		}
    }
	
	public void disconnect() {
		channel.disconnect();
	}
	
	public boolean isActive() {
		return channel.isActive();
	}
	
	
	class FlushTask implements Runnable {

		@Override
		public void run() {
			if (needFlush.getAndSet(false)) {
	            channel.flush();
	        }
		}
		
	}

}
