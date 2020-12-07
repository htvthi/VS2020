package org.htw.fiw.vs.aufgabe3.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import org.htw.fiw.vs.aufgabe3.interfaces.Calculator;

public class Server {

	public void startUp() {

		try {
			// Erzeugen des originalen Objekts
			final Calculator calculatorService = new CalculatorImpl();
			// Erzeugen eines Proxy-Objekts, das über den Port der später zu erzeugenden
			// Registry, exportiert wird
			Calculator stub = (Calculator) UnicastRemoteObject.exportObject(calculatorService, 0);
			Registry registry = LocateRegistry.createRegistry(1099);
			// register wird an den Dienst "CalculatorService" und an das stub-Objekt
			// angebunden
			registry.rebind("CalculatorService", stub);

		} catch (Exception e) {
			System.out.println("Calculator Service err: " + e.getMessage());
			e.printStackTrace();

		}
	}

	public static void main(String[] args) {

		// Erzeugen einer Instanz von Server
		Server server = new Server();
		server.startUp();

		// Erzeugen einer Scanner-Instanz,Warten auf Userinput
		Scanner myScanner = new Scanner(System.in);
		myScanner.nextLine();

	}

}
