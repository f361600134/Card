package com.fatiny.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 * 
 * @author huachp
 */
public class GameLog {
	
	private static final Logger logger = LoggerFactory.getLogger(GameLog.class.getName());
	
	private static boolean needLog4jLog;
//	private static Logger stdLog; // 标准输出
//	private static Logger errLog; // 错误输出
//	private static Logger fightLog; // 战斗日志
	
	
	public static void info(String infoMsg, Object... params) {
		logger.info(infoMsg, params);
	}

	public static void debug(String debugMsg, Object... params) {
		logger.debug(debugMsg, params);
	}

	public static void error(String errMsg, Object... params) {
		logger.error(errMsg, params);
	}

	public static void error(String errMsg, Throwable cause) {
		logger.error(errMsg, cause);
	}
	
	public static void fightLog(String logMsg, Object... params) {
		logger.debug(logMsg, params);
	}
	

	public static void init() throws Exception {
//		String selectorName = "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector";
//		System.setProperty(Constants.LOG4J_CONTEXT_SELECTOR, selectorName);
//		
//		File file = new File("config/log4j2.xml");
//		FileInputStream log4j2XmlStream = new FileInputStream(file);
//		ConfigurationSource source = new ConfigurationSource(log4j2XmlStream);
//		XmlConfiguration configuration = new XmlConfiguration(source);
//
//		Configurator.initialize(configuration);
//
//		stdLog = LogManager.getLogger("standardLog");
//		errLog = LogManager.getLogger("errorLog");
//		fightLog = LogManager.getLogger("fightLog"); 
//		needLog4jLog = true;
	}
	
}
