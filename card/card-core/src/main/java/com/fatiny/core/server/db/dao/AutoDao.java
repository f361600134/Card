package com.fatiny.core.server.db.dao;


import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fatiny.core.data.PoRegister;

/**
 * AutoDao - dao自动匹配
 *
 * @author chao
 * @version 1.0 - 2014-03-20
 */
public class AutoDao {
	private final static Logger logger = LoggerFactory.getLogger(AutoDao.class);
	public static void scan(String path) {
		logger.info("扫描dao依赖的所有业务实体类");
		Set<Class<?>> classes = PoRegister.scan(path);
		
		for (Class cls : classes) {
			new Dao(cls);
			logger.info("po:{}", cls);
		}
	}

}
