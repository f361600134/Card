package com.fatiny.game.game.module.common.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fatiny.game.game.module.common.GameContext;

import lombok.extern.slf4j.Slf4j;

/**
 * 延迟保存定时器
 */
@Slf4j
@DisallowConcurrentExecution
public class AutoSaveJob implements Job {
	
	public AutoSaveJob() {
		
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			log.debug("保存游戏数据");
			GameContext.instance().gameData().executeBatchSave();
		} catch (Exception e) {
			log.error("延迟保存用户数据错误", e);
		}
	}

}
