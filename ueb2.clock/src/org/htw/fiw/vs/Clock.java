package org.htw.fiw.vs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Clock {

	public static void main(String[] args) throws InterruptedException {
		
		int concurrentOps = 1;
		ExecutorService pool = Executors.newFixedThreadPool(concurrentOps);
		for (int i = 0; i < concurrentOps * 10; i++) {
			pool.submit(new TimeRunnable());
		}

		pool.shutdown();
		while (!pool.isShutdown()) {
			pool.awaitTermination(500, TimeUnit.MILLISECONDS);
		}
	}
}
