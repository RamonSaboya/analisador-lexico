package br.ufpe.cin.if688.minijava.exceptions.typecheck;

public class BadUnaryOperandException extends Exception {

	private static final long serialVersionUID = -6107564244689885049L;

	public BadUnaryOperandException(String expected, String actual, String operator) {
		super("Bad operand type " + actual + " for unary operator '" + operator + "'. Expected " + expected + " expression.");
	}

}
