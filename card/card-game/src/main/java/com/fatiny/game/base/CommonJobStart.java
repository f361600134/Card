package com.fatiny.game.base;

import com.fatiny.game.game.module.common.GameContext;
import com.fatiny.game.game.module.common.job.AutoSaveJob;

import lombok.extern.slf4j.Slf4j;

/**
 * 通用定时器启动类
 */
@Slf4j
public class CommonJobStart {
	
	public static final String AUTO_SAVE_JOB_NAME = "db-save-event";
	public static final String MINUTE_CONTAB = "0 * * * * ?";
	
	public static void prepareToRun() {
		try {
			GameContext gameCtx = GameContext.instance();
			GameScheduler gameSchd = gameCtx.getSched();
			if (gameSchd != null && gameSchd.isStarted()) {
				gameSchd.addJob(AUTO_SAVE_JOB_NAME, AutoSaveJob.class, MINUTE_CONTAB);
			}
		} catch (Exception e) {
			log.error("通用定时器启动出错", e);
		}
	}
	
}
