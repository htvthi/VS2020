package rmi.helloworld;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

	public void connectToServer() {
		String user = "World";
		SecurityManager security = System.getSecurityManager();

		if (security != null) {
			/*
			 * checkAccept() sorgt dafür dass der Thread, auf dem der Code gerade ausgeführt
			 * wird, die Erlaubnis erhält, zu Localhost Port 1099 eine Verbindung aufzubauen
			 */
			security.checkAccept("localhost", 1099);
		}

		try {
			// Erzeugen eines proxy-Objekts -> schaut in dem Rmi-Register nach einem Objekt,
			// das mit dem übergebenen Namen identifiziert werden kann
			HelloWorld proxyObject = (HelloWorld) Naming.lookup("HelloService");
			//
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
