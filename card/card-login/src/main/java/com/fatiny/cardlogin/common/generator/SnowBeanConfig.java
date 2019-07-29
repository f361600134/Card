package com.fatiny.cardlogin.common.generator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowBeanConfig {
	
	@Value("${define.serverId}")
	private int serverId;
	
	@Bean
	public SnowflakeGenerator getIdWorker() {
		return new SnowflakeGenerator(serverId);
	} 

}
