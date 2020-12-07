package org.htw.fiw.vs.aufgabe3.server;

import java.rmi.RemoteException;

import org.htw.fiw.vs.aufgabe3.interfaces.Calculator;

//CalculatorImpl implementiert Interface Calculator
public class CalculatorImpl implements Calculator {

	// Erzeugen einer Enum-Klasse "Operator" mit Grundrechenarten
	public enum Operator {

		ADD("+"), SUB("-"), MUL("*"), DIV("/");

		//
		public final String symbol;

		Operator(String symbol) {

			this.symbol = symbol;
		}

		// Überprüfen, welcher Operator der Nutzer eingegeben hat;
		public static Operator parseOperator(String input) {
			// String-Switch über den Input, basierend auf dem übergegebenen Operator-Symbol
			// wird der entsprechende Operator zurückgeliefert
			switch (input) {
			case "+":
				return ADD;
			case "-":
				return SUB;
			case "*":
				return MUL;
			case "/":
				return DIV;
			default:
				throw new IllegalArgumentException("Du kannst nur mit den Operatoren +, -, *, /, arbeiten");
			}
		}

	}

	public CalculatorImpl() throws RemoteException {

	}

	// calculate-Methode mit User-input als Parameter
	@Override
	public String calculate(String input) {

		// Erzeugen eines Arrays: führende und anhängende Leerzeichen werden entfernt;
		// einzelne Teil-Strings werden im Array gespeichert
		String[] splittedInput = input.trim().split(" ");

		// Länge des Arrays wird geprüft, darf nicht länger als 3 sein (Operand +
		// Operator + Operand)
		if (splittedInput.length != 3) {
			return "Eingabe fehlerhaft -> erwartete Eingabe: Eingaben in Form von '55 + 3.55'";
		}
		// Umwandeln der ersten Zahl, die vom Nutzer eingegeben wurde, in ein Double
		double leftOperand = Double.parseDouble(splittedInput[0]);
		// Umwandeln der dritten Zahl, die vom Nutzer eingegeben wurde, in ein Double
		double rightOperand = Double.parseDouble(splittedInput[2]);
		// Erzeugen einer Instanz von Operator -> Aufrufen der parseOperator-Methode mit
		// Operator, den Nutzer eingegeben hat
		try {
			Operator operator = Operator.parseOperator(splittedInput[1]);
			// eigentliche Berechnung wird durchgeführt und als String zurückgegeben
			switch (operator) {
			case ADD:
				return String.valueOf(leftOperand + rightOperand);
			case DIV:
				return String.valueOf(leftOperand / rightOperand);
			case MUL:
				return String.valueOf(leftOperand * rightOperand);
			case SUB:
				return String.valueOf(leftOperand - rightOperand);
			}
			// this can't be reached
			return null;

		} catch (IllegalArgumentException exception) {
			return exception.getMessage();
		}

	}

}
