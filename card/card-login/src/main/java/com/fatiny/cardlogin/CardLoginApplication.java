package com.fatiny.cardlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.fatiny.cardlogin.common.excutor.TaskThreadPoolConfig;

/**
 * SpringBootApplication 包含 <br>
 * #@Configuration：表示将该类作用springboot配置文件类。<br>
 * #@EnableAutoConfiguration:表示程序启动时，自动加载springboot默认的配置。<br>
 * #@ComponentScan:表示程序启动是，自动扫描当前包及子包下所有类。<br>
 * 
 * @auth Jeremy
 * @date 2018年10月8日上午10:04:56
 */
@EnableAsync // 开启线程池
@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties({ TaskThreadPoolConfig.class }) // 开启配置属性支持
@EnableJpaRepositories
public class CardLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardLoginApplication.class, args);
	}

}
