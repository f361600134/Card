package com.fatiny.game.util.po;

import com.alibaba.druid.pool.DruidDataSource;

public class DbPoTool {


	public static void main(String[] args) {
		try {
			DruidDataSource gameDataSource = new DruidDataSource();
			gameDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/lszj_game?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
			gameDataSource.setUsername("root");
			gameDataSource.setPassword("123456");
			gameDataSource.setInitialSize(1);
			//gameDataSource.addConnectionProperty(ConfigFilter.CONFIG_DECRYPT, "true");// 密码是否加密
			gameDataSource.init();

			PoPrinter.print(gameDataSource
					, "./temps/po"
					, "com.fatiny.game.po");

			System.out.println("-----------------------------------------");
			
//			DruidDataSource loginDataSource = new DruidDataSource();
//			loginDataSource.setUrl("jdbc:mysql://10.6.8.100:3306/login_ftwar?useUnicode=true&characterEncoding=utf-8");
//			loginDataSource.setUsername("root");
//			loginDataSource.setPassword("hzdbserver");
//			loginDataSource.setInitialSize(1);
//			loginDataSource.addConnectionProperty(ConfigFilter.CONFIG_DECRYPT, "true");// 密码是否加密
//			loginDataSource.init();
//			
//			PoPrinter.print(loginDataSource
//					, "./temps/po"
//					, "com.good.po");
//			
//			System.out.println("-----------------------------------------");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
