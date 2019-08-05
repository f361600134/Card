package com.fatiny.game.game.module.common.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisallowConcurrentExecution
public class LogoutJob implements Job {
	
	public LogoutJob() {
		
	}
	
	@Override
	public void execute(JobExecutionContext arg0) {
		try {
//			LogoutManager.handleLogout();
			log.info("this is a quartz");
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
