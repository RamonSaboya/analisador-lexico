package br.ufpe.cin.if688.minijava.exceptions.typecheck;

public class TypeMismatchException extends Exception {

	private static final long serialVersionUID = 5263160760154834795L;

	public TypeMismatchException(String expected, String actual, String at) {
		super(expected + " required, but " + actual + " found. (" + at + ")");
	}

}
