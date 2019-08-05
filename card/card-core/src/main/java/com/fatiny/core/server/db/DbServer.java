package com.fatiny.core.server.db;

import com.fatiny.core.bootstrap.AbstractServer;
import com.fatiny.core.net.KryoDecoder;
import com.fatiny.core.net.KryoEncoder;
import com.fatiny.core.server.db.dao.AutoDao;
import com.fatiny.core.server.db.executor.MysqlExecutor;
import com.fatiny.core.server.db.executor.RedisExecutor;
import com.fatiny.core.server.db.iohandler.DbServerHandler;
import com.fatiny.core.server.db.manager.DbManager;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据服 主服务
 */
@Slf4j
public class DbServer extends AbstractServer {
	
	private DbServerConfig config;
	private MysqlExecutor mysqlExecutor;
	private RedisExecutor redisExecutor;
	
	
	public DbServer() {
		
	}
	
	
	public void loadConfig() {
		config = DbServerConfig.getInstance();
		config.loadConfig();
	}
	
	
	public void initExecutors() {
		log.info("启动数据服核心业务线程");
		int executorCount = Runtime.getRuntime().availableProcessors() * 4;
		mysqlExecutor = new MysqlExecutor(executorCount);
		redisExecutor = new RedisExecutor(executorCount);
		redisExecutor.hold(mysqlExecutor);
	}
	
	
	public void bootstrap() {
		DefaultThreadFactory bossTf = new DefaultThreadFactory("DB_SERVER_BOSS");
		NioEventLoopGroup bossGroup = new NioEventLoopGroup(1, bossTf);
		DefaultThreadFactory workerTf = new DefaultThreadFactory("DB_SERVER_WORKER");
		NioEventLoopGroup workerGroup = new NioEventLoopGroup(4, workerTf);
		
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
							// inbound
							pipeline.addLast("lengthDecoder", new LengthFieldBasedFrameDecoder(16 * 1024 * 1024, 0, 4, 0, 4));
							pipeline.addLast("kryoDecoder", new KryoDecoder());
							pipeline.addLast("serverHandler", new DbServerHandler(mysqlExecutor, redisExecutor));
							// outbound
							pipeline.addLast("lengthEncoder", new LengthFieldPrepender(4));
							pipeline.addLast("kryoEncoder", new KryoEncoder());
						}
						
					});
			
			bootstrap.bind(ip, port).sync();
			// log
			log.info("数据服启动成功, ip:{}, port:{}", ip, port);
		} catch (Exception e) {
			log.error("", e);
			// log
			throw new RuntimeException("Netty启动过程出现异常, 服务器关闭, 请检查");
		}
		
		
	}


	@Override
	protected boolean start() {
		try {
			// 初始化数据服配置
			loadConfig();
			// 初始化数据服核心业务线程
			initExecutors();
			// 初始化数据库连接池
			DbManager.init();
			// 启动主服务
			bootstrap();
			
			// 初始化所有实体类信息
			AutoDao.scan("com.fatiny.game.game");
			
			return true;
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public void stop() {
		
	}


	@Override
	public void startInstruct() {
	}
	

	
}
