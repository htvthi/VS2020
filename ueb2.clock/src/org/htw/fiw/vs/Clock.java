package org.htw.fiw.vs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Clock {

	public static void main(String[] args) throws InterruptedException {
		
		//Concurrentops = parallel laufende Operationen; 
		int concurrentOps = 5;
		//auf einem Thread kann immer nur 1 Operation durchlaufen werden
		ExecutorService pool = Executors.newFixedThreadPool(concurrentOps);
		for (int i = 0; i < concurrentOps * 10; i++) {
			pool.submit(new TimeRunnable());
			System.out.println("neue Arbeit wird vorgemerkt");
		}

		// Ausschalten der Pool an Threads -> sorgt dafür das keine Arbeit mehr vorgemerkt werden kann
		pool.shutdown();
		// Solange er noch nicht ausgeschalten ist, warte noch eine halbe Sekunde auf die Beendigung
		while (!pool.isShutdown()) {
			pool.awaitTermination(500, TimeUnit.MILLISECONDS);
			System.out.println("Warte auf pool-Shutdown");
		}
	}
}
