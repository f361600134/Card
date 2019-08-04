package com.fatiny.game.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.fatiny.core.server.main.GameSession;
import com.fatiny.core.server.main.dispatcher.Dispatcher;

import lombok.extern.slf4j.Slf4j;

/**
 * 协议分发工作者
 */
@Slf4j
public class DispatchExecutor {
	
	private List<DispatchWorker> workers;
	
	public DispatchExecutor() {
		
	}
	
	public void initWorkers() {
		int workerCount = Runtime.getRuntime().availableProcessors() * 2;	
		workers = new ArrayList<>(workerCount);
		
		for (int i = 0; i < workerCount; i++) {
			DispatchWorker worker = new DispatchWorker();
			worker.setName("DISPATCH_WORKER_" + (i + 1));
			worker.start();
			workers.add(worker);
		}
	}
	
	
	public void addTask(GameSession session, Dispatcher dispatcher, Packet packet) {
		try {
			DispatchTask task = new DispatchTask(session, dispatcher, packet);
			int idx = session.getId() % workers.size();
			workers.get(idx).addTask(task);
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	
	
	class DispatchWorker extends Thread {
		
		BlockingQueue<DispatchTask> taskQueue = new LinkedBlockingQueue<>();
		
		DispatchWorker() {
			
		}
		
		void addTask(DispatchTask task) throws Exception {
			this.taskQueue.put(task);
		}
		
		@Override
		public void run() {
			for (;;) {
				try {
					DispatchTask task = taskQueue.take();
					task.invoke();
				} catch (Exception e) {
					log.error("协议执行过程出错", e);
				}
			}
		}
		
	}
	
	
	class DispatchTask {

		private GameSession session;
		private Packet packet;
		private Dispatcher dispatcher;

		DispatchTask(GameSession session, Dispatcher dispatcher, Packet packet) {
			this.session = session;
			this.dispatcher = dispatcher;
			this.packet = packet;
		}

		void invoke() throws Exception {
			dispatcher.invoke(session, packet.cmd(), packet.data());
		}

	}
	
	
	public static DispatchExecutor create() {
		DispatchExecutor executor = new DispatchExecutor();
		executor.initWorkers();
		return executor;
	}

	
}
