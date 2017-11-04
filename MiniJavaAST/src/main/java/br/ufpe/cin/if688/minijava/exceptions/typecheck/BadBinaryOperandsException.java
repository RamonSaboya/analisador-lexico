package br.ufpe.cin.if688.minijava.exceptions.typecheck;

public class BadBinaryOperandsException extends Exception {

	private static final long serialVersionUID = -1726418362432165587L;

	public BadBinaryOperandsException(String firstExpected, String secondExpected, String firstActual, String secondActual, String operator) {
		super("Bad operand types " + firstActual + " and " + secondActual + " for binary operator '" + operator + "'. Expected " + firstExpected + " and "
				+ secondExpected + " expressions.");
	}

}
