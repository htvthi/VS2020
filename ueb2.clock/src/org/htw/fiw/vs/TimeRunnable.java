package org.htw.fiw.vs;

import java.text.DateFormat;
import java.util.Date;

public class TimeRunnable implements Runnable {

	@Override
	public void run() {
		try {
			// Lässt den Thread, auf dem das Runnable ausgeführt wird, 1 Sekunde schlafen
			Thread.sleep(1000);
			// Aktuelle Uhrzeit wird ausgegeben
			System.out.println(DateFormat.getTimeInstance().format(new Date()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
