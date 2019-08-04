package com.fatiny.game.base;

import java.util.Properties;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时器
 * 
 * @author huachp
 */
@Slf4j
public class GameScheduler {
	
	static final String CONFIG_PATH = "src/main/resources/main/quartz.properties";
	static final String JOB_GROUP_NAME = "group-jobs";
	static final String TRIGGER_GROUP_NAME = "group-triggers";
	
	private String confPath;
	private Scheduler sched;
	
	public GameScheduler() {
		
	}
	
	public GameScheduler(String path) {
		this.confPath = path;
		init();
	}
	
	private void init() {
		try {
			SchedulerFactory sf = new StdSchedulerFactory(confPath);
			
			sched = sf.getScheduler();
			sched.start();
		} catch (Exception e) {
			log.error("定时器启动失败", e);
		}
	}
	
	public void close() {
		try {
			if (sched != null) {
				sched.shutdown();
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	public boolean isStarted() {
		try {
			return sched != null && sched.isStarted();
		} catch (Exception e) {
			log.error("", e);
			return false;
		}
	}
	
	
	/**
	 * 暂停任务
	 * 
	 * @param name
	 * @throws SchedulerException
	 */
	public void pauseJob(String name) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(name, JOB_GROUP_NAME);
		sched.pauseJob(jobKey);
		log.info("暂停任务:job_name={}", name);
	}
	
	/**
	 * 恢复任务
	 * 
	 * @param name
	 * @throws SchedulerException
	 */
	public void resumeJob(String name) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(name, JOB_GROUP_NAME);
		sched.resumeJob(jobKey);
		log.info("恢复任务:job_name={}", name);
	}
	
	/**
	 * 添加定时任务
	 * 
	 * @param name     定时任务名字
	 * @param jobClass 定时任务逻辑
	 * @param time     时间格式
	 * @throws Exception
	 */
	public void addJob(String name, Class<? extends Job> jobClass, String time) throws Exception {
		JobDetail jobDetail = JobBuilder.newJob(jobClass)
				.withIdentity(name, JOB_GROUP_NAME).build(); // 创建一个任务
		
		CronTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(name, TRIGGER_GROUP_NAME) // 定时器唯一标识
				.withSchedule(CronScheduleBuilder.cronSchedule(time)) // crontab表达式
				.forJob(name, JOB_GROUP_NAME).build(); // 指定任务

		sched.scheduleJob(jobDetail, trigger);
		
		log.info("添加定时任务:job_name={}, time={}", name , time);
	}

	
	public static GameScheduler create() {
		GameScheduler gameSchd = new GameScheduler(CONFIG_PATH);
		return gameSchd;
	}
	
	
	public static GameScheduler createNoConfig() {
		try {
			GameScheduler gameSchd = new GameScheduler();
			Properties properties = new Properties();
			properties.setProperty(StdSchedulerFactory.PROP_SCHED_INSTANCE_NAME, "DefaultQuartzScheduler");
			properties.setProperty(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, "org.quartz.simpl.SimpleThreadPool");
			properties.setProperty(StdSchedulerFactory.PROP_SCHED_SCHEDULER_THREADS_INHERIT_CONTEXT_CLASS_LOADER_OF_INITIALIZING_THREAD, "true");
			properties.setProperty(StdSchedulerFactory.PROP_JOB_STORE_CLASS, "org.quartz.simpl.RAMJobStore");
			properties.setProperty("org.quartz.threadPool.threadCount", "5");
			properties.setProperty("org.quartz.jobStore.misfireThreshold", "60000");
			
			SchedulerFactory sf = new StdSchedulerFactory(properties);
			gameSchd.sched = sf.getScheduler();
			return gameSchd;
		} catch (Exception e) {
			log.error("初始化定时器出错", e);
			return null;
		}
	}
	
	
}
