package org.htw.fiw.vs.aufgabe3.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import org.htw.fiw.vs.aufgabe3.interfaces.Calculator;

public class Client {

	public void connectToServer() {

		try {

			Registry registry = LocateRegistry.getRegistry();

			// Erzeugen eines Proxy-Objekts -> schaut in dem RMI-Register nach einem Objekt,
			// das mit dem übergebenen Namen identifiziert werden kann

			Calculator proxyObject = (Calculator) registry.lookup("CalculatorService");
			promptUserForCalculation(proxyObject);

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void promptUserForCalculation(Calculator calculator) {

		// Erzeugen einer Scanner-Instanz
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Mit Server verbunden. Bitte gebe deine mathematische Berechnung ein(z.B. '4 + 12')");

		while (true) {

			try {
				String input = myScanner.nextLine();
				// calculate-Klasse wird aufgerufen mit User-Input als Parameter
				// (Calculator-Klasse Impl)
				String result = calculator.calculate(input);
				System.out.println("Das Ergebnis der Berechnung ist: " + result);

			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {

		/*
		 * Erzeugen einer Client-Instanz mit Server verbinden
		 */
		Client client = new Client();
		client.connectToServer();
	}

}
