package org.htw.fiw.vs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Name {

	private final Lock lock = new ReentrantLock();
	private String first;
	private String last;

	public void set(String first, String last) {

		System.out.println("#set() try to acquire lock " + Thread.currentThread().getName());
		lock.lock();
		System.out.println("#set() lock aquired " + Thread.currentThread().getName());

		try {
			this.first = first;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.last = last;
		} finally {
			lock.unlock();
			System.out.println("#set() lock released " + Thread.currentThread().getName());

		}

	}

	public String get() {
		System.out.println("#get() try to acquire lock " + Thread.currentThread().getName());
		lock.lock();
		System.out.println("#get()lock acquired " + Thread.currentThread().getName());

		try {
			return this.first + " " + this.last;
		} finally {
			lock.unlock();
			System.out.println("#get() lock released " + Thread.currentThread().getName());
		}
	}

}
