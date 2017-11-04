package br.ufpe.cin.if688.minijava.exceptions.symboltable;

public class ClassVariableAlreadyDefinedException extends Exception {

	private static final long serialVersionUID = 7780510284788787215L;

	public ClassVariableAlreadyDefinedException(String className, String variable) {
		super("Variable " + variable + " is already defined in class " + className + ".");
	}

}
