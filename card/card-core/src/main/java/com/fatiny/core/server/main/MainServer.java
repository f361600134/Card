package com.fatiny.core.server.main;

import com.fatiny.core.bootstrap.AbstractServer;
import com.fatiny.core.bootstrap.IStartup;
import com.fatiny.core.bootstrap.ServerConfig;
import com.fatiny.core.bootstrap.ServerManager;
import com.fatiny.core.net.GameProtocolEncoder;
import com.fatiny.core.net.IServerHandler;
import com.fatiny.core.net.IdleDetectionHandler;
import com.fatiny.core.server.main.iohandler.MainServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 游戏服主服务
 */
@Slf4j
public class MainServer extends AbstractServer {
	
	private IStartup startup;
	private IServerHandler serverHandler;
	private int connectionIdleTime;
	
	public MainServer() {
		
	}
	
	
	public void init() {
		try {
			ServerConfig config = ServerManager.getConfig();
			String className = config.getInitClass();
			Class<?> cls = Thread.currentThread().getContextClassLoader().loadClass(className);
			startup = (IStartup) cls.newInstance();
			serverHandler = startup.startup();
			connectionIdleTime = config.getConnectionIdleTime();
		} catch (Exception e) {
			log.error("", e);
			throw new RuntimeException("启动过程出现异常, 服务器关闭, 请检查配置");
		}
	}
	
	
	public void bootstrap() {
		DefaultThreadFactory bossTf = new DefaultThreadFactory("MAIN_SERVER_BOSS");
		NioEventLoopGroup bossGroup = new NioEventLoopGroup(1, bossTf);
		int threadCount = Runtime.getRuntime().availableProcessors() * 2;  // CPU核数 * 2
		DefaultThreadFactory workerTf = new DefaultThreadFactory("MAIN_SERVER_WORKER");
		NioEventLoopGroup workerGroup = new NioEventLoopGroup(threadCount, workerTf);
		
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_REUSEADDR, true)
					.childOption(ChannelOption.SO_RCVBUF, 128 * 1024)
					.childOption(ChannelOption.SO_SNDBUF, 128 * 1024)
					.childOption(ChannelOption.SO_KEEPALIVE, true)
					.childOption(ChannelOption.TCP_NODELAY, true)
					.childHandler(new ChannelInitializer<Channel>() {

						@Override
						protected void initChannel(Channel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							// idle connection detection
							pipeline.addLast("idleState", new IdleStateHandler(connectionIdleTime, 0, 0)); // 默认30秒
							pipeline.addLast("idleDetection", new IdleDetectionHandler());
							// inbound
							pipeline.addLast("lengthDecoder", new LengthFieldBasedFrameDecoder(8 * 1024, 0, 4, 0, 4)); 
							pipeline.addLast("serverHandler", new MainServerHandler(serverHandler));
							// outbound
							pipeline.addLast("lengthEncoder", new LengthFieldPrepender(4));
							pipeline.addLast("protocolEncoder", new GameProtocolEncoder());
						}
						
					});
			
			bootstrap.bind(port).sync();
			// log
			log.info("逻辑服启动成功, 绑定0.0.0.0, port:{}, 指定ip:{}", port, ip);
		} catch (Exception e) {
			log.info("", e);
			throw new RuntimeException("Netty启动出现异常, 服务器关闭, 请检查");
		}
		
	}

	
	@Override
	public void stop() {
		
	}

	
	@Override
	protected boolean start() {
		try {
			// 初始化
			init();
			// 启动主服务
			bootstrap();
			// 服务器开启
			serverHandler.serverStatus(true);
			
			return true;
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public void startInstruct() {
		//后台指令
		this.startup.startInstruct();
	}
	

}
