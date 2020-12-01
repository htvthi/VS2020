package org.htw.fiw.vs;

public class Main {

	public static void main(String[] args) {
		final Name name = new Name();
		Thread getter = new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (i < 20) {
					System.out.println(name.get());
					i++;
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		getter.start();

		Thread setter = new Thread(new Runnable() {
			@Override
			public void run() {
				name.set("Donald", "Trump");
				name.set("Biden", "Joe");
			}
		});
		setter.start();

		Thread.yield();
	}
}
