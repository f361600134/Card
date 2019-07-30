package com.fatiny.cardloginplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync // 开启线程池
@EnableAutoConfiguration
@SpringBootApplication
public class CardLoginPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardLoginPlusApplication.class, args);
	}

}
