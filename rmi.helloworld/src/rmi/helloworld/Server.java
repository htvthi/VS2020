package rmi.helloworld;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	public void init() {

		/*
		 * Instanz von HelloWorldImpl wird erzeugt, die das Interface HelloWorld
		 * implementiert Register wird erzeugt, das man über den Port 1099 aufrufen kann
		 * HelloWorld Objekt wird unter dem Namen HelloService im Register eingetragen
		 */
		try {
			// Erzeugen einer HelloWorld Impl-Instanz, welche das HelloWorld Interface
			// erfüllt
			final HelloWorld helloWorldService = new HelloWorldImpl();
			// Erzeugen eines lokalen RMI Registers mit dem Port 1099
			Registry registry = LocateRegistry.createRegistry(1099);
			// Bindet die HelloWorldService Instanz an einen Dienst, der via rmi aufgerufen
			// werden kann
			registry.rebind("HelloService", helloWorldService);

		} catch (Exception e) {
			System.out.println("HelloWorld err: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
