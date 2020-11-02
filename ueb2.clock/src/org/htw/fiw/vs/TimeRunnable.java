package org.htw.fiw.vs;

import java.text.DateFormat;
import java.util.Date;

public class TimeRunnable implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println(DateFormat.getTimeInstance().format(new Date()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
