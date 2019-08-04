package com.fatiny.core.client.actor;

import java.net.ConnectException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.fatiny.core.akka.remote.ActorMessage;
import com.fatiny.core.client.actor.iohandler.ActorClientHandler;
import com.fatiny.core.net.KryoDecoder;
import com.fatiny.core.net.KryoEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.flush.FlushConsolidationHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * Actor路由客户端
 */
@Slf4j
public class ActorRouteClient {
	
	/** Actor远程配置 */
	private ActorConfig clientConfig;
	/** Actor IO 线程池 */
	private NioEventLoopGroup eventLoopGroup;
	/** 服务器信息 */
	private CopyOnWriteArrayList<ActorServerInfo> serverInfos = new CopyOnWriteArrayList<>();
	/** 故障检测处理器 */
	private ActorDetectionHander detectionHandler;
	/** 路由分配处理器 */
	private ActorRouteHandler routeHandler;
	/** 对端服务是否是有状态的 */
	private boolean stateful;
	
	
	public static ActorRouteClient create(String actorName, boolean stateful) {
		ActorRouteClient routeClient = new ActorRouteClient();
		routeClient.loadConfig(actorName);
		routeClient.stateful = stateful;
		return routeClient;
	}
	
	public ActorRouteClient executorGroup(NioEventLoopGroup eventLoopGroup) {
		this.eventLoopGroup = eventLoopGroup;
		return this;
	}
	
	public void init() {
		if (!checkInit0()) { // 初始化状态判断
			return;
		}
		// 以下调用有序
		initEventLoopGroup(); // 1.初始化线程池
		initServerInfo();  // 2.初始化服务器配置信息
		initHandlers();
		initConnections();
	}
	
	private void loadConfig(String actorName) {
		String formatName = actorName.toLowerCase();
		ActorGroupConfig groupConfig = ActorGroupConfig.instance();
		this.clientConfig = groupConfig.getConfig(formatName);
	}
	
	private void initEventLoopGroup() {
		if (eventLoopGroup == null) {
			DefaultThreadFactory tf = new DefaultThreadFactory("ACTOR_SERVER_CONNECTOR");
			int executorCount = Runtime.getRuntime().availableProcessors() * 2;
			eventLoopGroup = new NioEventLoopGroup(executorCount, tf);
		}
	}
	
	private void initHandlers() {
		if (detectionHandler == null) {
			detectionHandler = new ActorDetectionHander(this);
		}
		if (routeHandler == null) {
			routeHandler = new ActorRouteHandler(this);
		}
	}
	
	private void initServerInfo() {
		List<Integer> serverIds = clientConfig.allServerIds();
		for (Integer serverId : serverIds) {
			String ip = clientConfig.getIp(serverId);
			int port = clientConfig.getPort(serverId);
			ActorServerInfo serverInfo = new ActorServerInfo(serverId, ip, port);
			serverInfos.add(serverInfo);
		}
	}
	
	private boolean checkInit0() {
		if (!serverInfos.isEmpty()) {
			log.error(
					"Actor连接客户端已进行过初始化, 请检查代码; {}", serverInfos);
			return false;
		}
		return true;
	}
	
	private void initConnections() {
		for (ActorServerInfo serverInfo : serverInfos) {
			if (serverInfo.getChannel() == null) {
				initAndConnectToServer(serverInfo);
			}
		}
		detectionHandler.initService(); // 初始化定时故障检测线程
	}
	
	public void reloadConfig() {
		String actorName = clientConfig.getActorName();
		loadConfig(actorName);
		checkAndAddNewServers();
	}
	
	
	void checkAndAddNewServers() {
		List<Integer> dbServerIds = clientConfig.allServerIds();
		for (Integer serverId : dbServerIds) {
			String ip = clientConfig.getIp(serverId);
			int port = clientConfig.getPort(serverId);
			ActorServerInfo serverInfo = getServerInfo(serverId);
			if (serverInfo == null) {
				serverInfo = new ActorServerInfo(serverId, ip, port);
				initAndConnectToServer(serverInfo);
				serverInfos.add(serverInfo);
			}
		}
	}
	
	
	ActorServerInfo getServerInfo(int serverId) {
		for (ActorServerInfo serverInfo : serverInfos) {
			if (serverInfo.getServerId() == serverId) {
				return serverInfo;
			}
		}
		return null;
	}
	
	
	NioEventLoopGroup getEventLoopGroup() {
		return eventLoopGroup;
	}
	

	public ActorConfig getActorConfig() {
		return clientConfig;
	}
	
	
	public List<ActorServerInfo> getServerList() {
		return serverInfos;
	}
	
	
	public void request(int actorId, Object message, ActorCallback callback) {
		ActorServerInfo serverInfo = routeHandler.allocate(actorId);
		if (!serverInfo.isConnect()) {
			log.error("远程Actor服务不可用"); return;
		}
		ActorMsgHandler msgHandler = serverInfo.getMsgHandler();
		ActorMessage actorMsg = ActorMessage.create(actorId, message);
		msgHandler.addCallback(actorMsg, callback);
		serverInfo.getChannel().writeAndFlush(actorMsg);
	}
	

	public void reconnect(ActorServerInfo serverInfo) {
		if (!serverInfo.isActive()) {
			try {
				log.info("尝试重连远程Actor服务, {}", serverInfo.getServerAddr());
				ActorMsgHandler msgHandler = serverInfo.getMsgHandler();
				if (msgHandler == null) {
					log.error("Actor Client接收端消息队列=null"); return;
				}
				connectToServer(serverInfo);
				log.info("远程Actor服务重连成功, {}", serverInfo);
			} catch (Exception e) {
				log.error("远程Actor服务重连失败, {}", serverInfo);
			}
		}
	}
	
	
	private void initAndConnectToServer(ActorServerInfo serverInfo) {
		try {
			final ActorMsgHandler msgHandler = new ActorMsgHandler();
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup)
					.channel(NioSocketChannel.class)
					.option(ChannelOption.SO_REUSEADDR, true)
					.option(ChannelOption.SO_RCVBUF, 128 * 1024)
					.option(ChannelOption.SO_SNDBUF, 128 * 1024)
					.option(ChannelOption.SO_KEEPALIVE, true)
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<Channel>() {
						@Override
						protected void initChannel(Channel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							EventLoop readExecutor = eventLoopGroup.next();  // read Worker
							EventLoop writeExecutor = eventLoopGroup.next(); // write Worker
							
							pipeline.addFirst(writeExecutor, "flushBatch", new FlushConsolidationHandler());
							// inbound
							pipeline.addLast(readExecutor, "frameDecoder", new LengthFieldBasedFrameDecoder(16 * 1024 * 1024, 0, 4, 0, 4));
							pipeline.addLast(readExecutor, "kryoDecoder", new KryoDecoder());
							pipeline.addLast(readExecutor, "handler", new ActorClientHandler(msgHandler, detectionHandler));
							// outbound
							pipeline.addLast(writeExecutor, "frameEncoder", new LengthFieldPrepender(4));
							pipeline.addLast(writeExecutor, "kryoEncoder", new KryoEncoder());
						}
						
					});
			
			serverInfo.init(bootstrap, msgHandler); // 先初始化Bootstrap, msgHandler
			connectToServer(serverInfo);  // 开始连接
			log.info("Actor客户端启动成功, {}", serverInfo);
		} catch (ConnectException e) {
			log.error("远程Actor服务连接失败, {}", serverInfo);
		} catch (Exception e) {
			log.error("Actor路由客户端连接初始化过程出现异常", e);
			throw new RuntimeException(e);
		}
	}
	
	
	private void connectToServer(ActorServerInfo serverInfo) throws Exception  {
		Bootstrap bootstrap = serverInfo.getBootstrap();
		Channel channel = bootstrap.connect(serverInfo.getIp(), serverInfo.getPort()).sync().channel();
		serverInfo.serverStateful(stateful); // 标识有状态服务还是无状态服务
		serverInfo.channelOpen(channel);  // 缓存新建连接
	}
	
}
