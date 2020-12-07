package org.htw.fiw.vs.aufgabe3.interfaces;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface Calculator extends java.rmi.Remote, Serializable {

	String calculate(final String input) throws RemoteException;
}
