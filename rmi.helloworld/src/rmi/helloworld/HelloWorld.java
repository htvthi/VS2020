package rmi.helloworld;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface HelloWorld extends java.rmi.Remote, Serializable {

	String sayHello(String user) throws RemoteException;

}
