package rmi.calculator.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import rmi.calculator.interfaces.Calculator;

public class Server {

	public void startUp() {

		try {
			final Calculator calculatorService = new CalculatorImpl();
			Calculator stub = (Calculator) UnicastRemoteObject.exportObject(calculatorService, 0);
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("CalculatorService", stub);

		} catch (Exception e) {
			System.out.println("Calculator Service err: " + e.getMessage());
			e.printStackTrace();

		}
	}

	public static void main(String[] args) {

		Server server = new Server();
		server.startUp();

		Scanner myScanner = new Scanner(System.in);
		myScanner.nextLine();

	}

}
