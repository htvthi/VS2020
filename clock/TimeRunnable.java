package uebung2.clock;

import java.text.DateFormat;
import java.util.Date;

public class TimeRunnable implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(1000);															//Thread.sleep sind zwischen den Anfragen 1000 ms
			System.out.println(DateFormat.getTimeInstance().format(new Date()));		//Jeder Thread ist die momantane Uhrzeit, mit je 1000 ms Unterschied zwischen den Anfragen
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
