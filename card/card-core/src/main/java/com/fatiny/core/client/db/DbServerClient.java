package com.fatiny.core.client.db;

import java.net.ConnectException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.fatiny.core.client.db.exception.DaoException;
import com.fatiny.core.client.db.iohandler.DbServerClientHandler;
import com.fatiny.core.net.KryoDecoder;
import com.fatiny.core.net.KryoEncoder;
import com.fatiny.core.server.db.message.DbServerMsg;
import com.fatiny.core.util.GameLog;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * 数据服客户端
 * 
 * @author huachp
 */
public class DbServerClient {
	
	/** 数据服客户端配置 */
	private DbServerClientConfig clientConfig;
	/** 服务器信息 */
	private CopyOnWriteArrayList<DbServerInfo> serverInfos = new CopyOnWriteArrayList<>();
	/** 故障检测处理器 */
	private DbServerDetectionHander detectionHandler;
	/** 路由分配处理器 */
	private DbServerRouteHandler routeHandler;
	
	/** 连接成功回调队列 */
	private LinkedList<Callback> connectCallbacks = new LinkedList<>();
	
	
	private DbServerClient() {
		
	}
	
	public static DbServerClient create() {
		DbServerClient client = new DbServerClient();
		client.loadConfig();
		return client;
	}
	
	
	public void loadConfig() {
		clientConfig = DbServerClientConfig.instance();
		clientConfig.loadConfig();
	}
	
	
	// 初始化调用
	public void init() {
		initServerInfo();  // 必须先初始化服务器配置信息
		initHandlers();
		initConnections();
	}
	
	
	public void initServerInfo() {
		if (!checkInit0()) {
			return;
		}
		List<Integer> dbServerIds = clientConfig.allServerIds();
		for (Integer dbServerId : dbServerIds) {
			String ip = clientConfig.getIp(dbServerId);
			int port = clientConfig.getPort(dbServerId);
			DbServerInfo serverInfo = new DbServerInfo(dbServerId, ip, port);
			serverInfos.add(serverInfo);
		}
	}
	
	
	public void initHandlers() {
		if (detectionHandler == null) {
			detectionHandler = new DbServerDetectionHander(this);
		}
		if (routeHandler == null) {
			routeHandler = new DbServerRouteHandler(this);
		}
	}
	
	
	private boolean checkInit0() {
		if (!serverInfos.isEmpty()) {
			GameLog.error("数据服客户端已进行过初始化, 请检查代码; {}", serverInfos);
			return false;
		}
		return true;
	}
	
	
	void checkAndAddNewServers() {
		List<Integer> dbServerIds = clientConfig.allServerIds();
		for (Integer dbServerId : dbServerIds) {
			String ip = clientConfig.getIp(dbServerId);
			int port = clientConfig.getPort(dbServerId);
			DbServerInfo serverInfo = getServerInfo(dbServerId);
			if (serverInfo == null) {
				serverInfo = new DbServerInfo(dbServerId, ip, port);
				serverInfos.add(serverInfo);
				initAndConnectToServer(serverInfo);
			}
		}
	}
	
	
	public void addCallback(Callback callback) {
		connectCallbacks.add(callback);
	}
	
	
	public Response request(DbServerMsg requestMsg) {
		long id = requestMsg.getId();
		DbServerInfo serverInfo = routeHandler.allocate(id);
		if (serverInfo == null || !serverInfo.isConnect()) {
			Response resp = new Response(requestMsg);
			resp.set(new DaoException("数据服不可用"));
			return resp;
		} 
		DbServerMsgHandler msgHandler = serverInfo.getMsgHandler();
		Response resp = msgHandler.addCallback(requestMsg);
		serverInfo.getChannel().writeAndFlush(requestMsg);
		return resp;
	}
	
	
	DbServerInfo getServerInfo(int dbServerId) {
		for (DbServerInfo serverInfo : serverInfos) {
			if (serverInfo.getServerId() == dbServerId) {
				return serverInfo;
			}
		}
		return null;
	}
	
	
	List<DbServerInfo> getServerList() {
		return serverInfos;
	}
	
	
	public void initConnections() {
		for (DbServerInfo serverInfo : serverInfos) {
			if (serverInfo.getChannel() == null) {  // 只有在null的情况下初始化
				initAndConnectToServer(serverInfo);
			}
		}
		detectionHandler.initService(); // 初始化检测线程 
	}
	
	
	void reconnect(DbServerInfo serverInfo) {
		if (!serverInfo.isActive()) {
			try {
				GameLog.info("尝试重连数据服, {}", serverInfo.getServerAddr());
				DbServerMsgHandler msgHandler = serverInfo.getMsgHandler();
				if (msgHandler == null) {
					GameLog.error("数据服客户端消息接收队列=null"); return;
				}
				connectToServer(serverInfo);
				GameLog.info("数据服重连成功, {}", serverInfo);
			} catch (Exception e) {
				GameLog.error("数据服重连失败, {}", serverInfo);
			}
		}
	}
	
	
	private void initAndConnectToServer(DbServerInfo serverInfo) {
		DefaultThreadFactory tf = new DefaultThreadFactory("DB_SERVER_CONNECTOR");
		NioEventLoopGroup bossGroup = new NioEventLoopGroup(1, tf);
		
		final DbServerMsgHandler msgHandler = new DbServerMsgHandler(); 
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(bossGroup)
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
							
//							pipeline.addFirst("flushBatch", new FlushConsolidationHandler());
							// inbound
							pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(16 * 1024 * 1024, 0, 4, 0, 4));
							pipeline.addLast("kryoDecoder", new KryoDecoder());
							pipeline.addLast("handler", new DbServerClientHandler(msgHandler, detectionHandler));
							// outbound
							pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
							pipeline.addLast("kryoEncoder", new KryoEncoder());
						}
						
					});
			
			serverInfo.init(bootstrap, msgHandler); // 先初始化Bootstrap, msgHandler
			connectToServer(serverInfo);
			GameLog.info("数据服客户端启动成功, {}", serverInfo);
		} catch (ConnectException e) {
			GameLog.error("数据服初始连接失败, {}", serverInfo);
		} catch (Exception e) {
			GameLog.error("数据服客户端连接初始化过程出现异常", e);
			throw new RuntimeException(e);
		}
	}

	
	private void connectToServer(DbServerInfo serverInfo) throws Exception {
		Bootstrap bootstrap = serverInfo.getBootstrap();
		Channel channel = bootstrap.connect(serverInfo.getIp(), serverInfo.getPort()).sync().channel();
		serverInfo.channelOpen(channel);  // 缓存新建连接
		
		for (int i = 0; i < connectCallbacks.size(); i++) {  // 连接成功后处理回调
			Callback callback = connectCallbacks.poll();
			callback.onCall();
		}
	}
	
	
}
