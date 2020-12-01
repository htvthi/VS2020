package org.htw.fiw.vs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Name {

	// Zugangskontrolle für Threads realisieren: damit nur ein Thread gleichzeitig
	// mit dem Objekt interagieren kann
	private final Lock lock = new ReentrantLock();
	private String first;
	private String last;

	public void set(String first, String last) {

		/*
		 * Nur ein Thread hat Zugriff auf die Setter und die Getter-Methode, um zu
		 * vermeiden, dass halbfertige Daten via Get gezogen werden können, während die
		 * Set-Methode noch ausgeführt wird
		 * 
		 */
		lock.lock();

		try {
			this.first = first;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.last = last;
			/*
			 * Wenn es eine Exception innerhalb des try-Blocks gibt, wollen wir trotzdem das
			 * Lock releasen, damit das Programm weiter läuft
			 */
		} finally {
			lock.unlock();

		}

	}

	public String get() {
		lock.lock();

		try {
			return this.first + " " + this.last;
		} finally {
			lock.unlock();

		}
	}

}
