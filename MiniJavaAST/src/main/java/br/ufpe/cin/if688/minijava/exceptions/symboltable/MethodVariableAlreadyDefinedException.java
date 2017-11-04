package br.ufpe.cin.if688.minijava.exceptions.symboltable;

public class MethodVariableAlreadyDefinedException extends Exception {

	private static final long serialVersionUID = 1027421912401505473L;

	public MethodVariableAlreadyDefinedException(String method, String variable) {
		super("Variable " + variable + " is already defined in method " + method + ".");
	}

}
