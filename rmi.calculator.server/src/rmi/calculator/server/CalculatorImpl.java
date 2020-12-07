package rmi.calculator.server;

import java.rmi.RemoteException;

import rmi.calculator.interfaces.Calculator;

public class CalculatorImpl implements Calculator {

	public enum Operator {

		ADD("+"), SUB("-"), MUL("*"), DIV("/");

		public final String symbol;

		Operator(String symbol) {

			this.symbol = symbol;
		}

		public static Operator parseOperator(String input) {

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

	@Override
	public String calculate(String input) {

		String[] splittedInput = input.trim().split(" ");

		if (splittedInput.length != 3) {
			throw new IllegalArgumentException("erwartet: Eingaben in Form von '55 + 3.55'");
		}

		double leftOperand = Double.parseDouble(splittedInput[0]);
		double rightOperand = Double.parseDouble(splittedInput[2]);
		Operator operator = Operator.parseOperator(splittedInput[1]);

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
	}

}
