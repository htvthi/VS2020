package rmi.helloworld;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	public void init() {

		try {
			final HelloWorld helloWorldService = new HelloWorldImpl();
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("HelloService", helloWorldService);

		} catch (Exception e) {
			System.out.println("HelloWorld err: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
