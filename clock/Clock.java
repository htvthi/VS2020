package uebung2.clock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Clock {

	public static void main(String[] args) throws InterruptedException {
		int concurrentOps = 1;														// wenn der Wert concurrentOps von 1 auf 5 verändert wird, werden statt 1nem Wert 5 ausgegeben
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
