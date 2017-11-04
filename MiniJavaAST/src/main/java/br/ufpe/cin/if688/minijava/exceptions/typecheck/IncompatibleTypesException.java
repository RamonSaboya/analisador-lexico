package br.ufpe.cin.if688.minijava.exceptions.typecheck;

public class IncompatibleTypesException extends Exception {

	private static final long serialVersionUID = 7098988053733531589L;

	public IncompatibleTypesException(String expected, String actual, String at) {
		super("Incompatible types: " + actual + " cannot be converted to " + expected + ". (" + at + ")");
	}

	public IncompatibleTypesException(String expected, String actual, String method, String at) {
		super(
				"Incompatible types: " + actual + " cannot be converted to " + expected + ". Some messages may been simplified in method " + method + ". (" + at + ")");
	}

}
