package com.fatiny.core.akka.remote;

import com.fatiny.core.akka.iohandler.ActorServerHandler;
import com.fatiny.core.net.KryoDecoder;
import com.fatiny.core.net.KryoEncoder;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.flush.FlushConsolidationHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 远程Actor服务
 */
@Slf4j
public class RemoteActorServer {
	
	private ActorServerConfig serverConf;
	private String systemName;
	private ActorSystem actorSystem;
	private Class<?> typedActorCls;
	private Object typeActorImpl;
	private ServerRouteActors routeActors;
	
	public RemoteActorServer() {
		
	}
	
	public static RemoteActorServer create(String name, Class<?> typedActorCls, Object typedActor) {
		RemoteActorServer actorServer = new RemoteActorServer();
		actorServer.systemName = name;
		actorServer.typedActorCls = typedActorCls;
		actorServer.typeActorImpl = typedActor;
		actorServer.init();
		return actorServer;
	}
	
	private void init() {
		serverConf = ActorServerConfig.instance();
		serverConf.loadConfig();
		actorSystem = ActorSystem.create(systemName); // 初始化ActorSystem
		startActors();
	}
	
	private void startActors() {
		TypedActorMethods actorMethods = TypedActorMethods.create(typedActorCls, typeActorImpl);
		int actorCount = serverConf.getActorCount();
		routeActors = new ServerRouteActors(actorCount);
		for (int i = 0; i < actorCount; i++) {
			Props props = Props.create(RemoteActor.class, actorMethods);
			ActorRef actorRef = actorSystem.actorOf(props);
			routeActors.addActor(i, actorRef);
		}
	}
	
	public void bootstrap() {
		NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
		NioEventLoopGroup workerGroup = new NioEventLoopGroup(4);
		
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
							
							pipeline.addFirst("flushBatch", new FlushConsolidationHandler());
							
							// inbound
							pipeline.addLast("lengthDecoder", new LengthFieldBasedFrameDecoder(16 * 1024 * 1024, 0, 4, 0, 4));
							pipeline.addLast("kryoDecoder", new KryoDecoder());
							pipeline.addLast("serverHandler", new ActorServerHandler(routeActors));
							
							// outbound
							pipeline.addLast("lengthEncoder", new LengthFieldPrepender(4));
							pipeline.addLast("kryoEncoder", new KryoEncoder());
						}
						
					});
			
			String ip = serverConf.getIp();
			int port = serverConf.getPort();
			
			log.info("远程actor server启动中... ip:{}, port:{}", ip, port);
			bootstrap.bind(ip, port).sync();
			log.info("远程actor server启动成功, ip:{}, port:{}", ip, port);
		} catch (Exception e) {
			log.error("", e);
			// log
			throw new RuntimeException("Netty启动过程出现异常, 服务器关闭, 请检查");
		}
		
	}
	
	
}
