package rmi.calculator.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import rmi.calculator.interfaces.Calculator;

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

		Scanner myScanner = new Scanner(System.in);
		System.out.println("connected to server. Please input your mathematical calculation");

		while (true) {

			try {
				String input = myScanner.nextLine();
				String result = calculator.calculate(input);
				System.out.println("The result of the calculation is " + result);

			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {

		Client client = new Client();
		client.connectToServer();
	}

}
