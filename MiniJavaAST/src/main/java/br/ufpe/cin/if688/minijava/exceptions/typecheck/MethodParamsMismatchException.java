package br.ufpe.cin.if688.minijava.exceptions.typecheck;

public class MethodParamsMismatchException extends Exception {

	private static final long serialVersionUID = 4226086517951952032L;

	public MethodParamsMismatchException(String className, String method) {
		super("Method " + method + " in class " + className + " cannot be applied to given types. Actual and formal argument lists differ in length.");
	}

}
