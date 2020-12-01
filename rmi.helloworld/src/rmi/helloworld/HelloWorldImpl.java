package rmi.helloworld;

import java.rmi.RemoteException;

public class HelloWorldImpl implements HelloWorld {

	public HelloWorldImpl() throws RemoteException {

	}

	@Override
	public String sayHello(String user) {

		return "Hello " + user;
	}

}
