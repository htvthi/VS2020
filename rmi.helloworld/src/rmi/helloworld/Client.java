package rmi.helloworld;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

	public void connectToServer() {
		String user = "Zoe";
		SecurityManager security = System.getSecurityManager();

		if (security != null) {
			security.checkAccept("localhost", 1099);
		}

		try {
			HelloWorld proxyObject = (HelloWorld) Naming.lookup("HelloService");
			String result = proxyObject.sayHello(user);
			System.out.println(result);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
