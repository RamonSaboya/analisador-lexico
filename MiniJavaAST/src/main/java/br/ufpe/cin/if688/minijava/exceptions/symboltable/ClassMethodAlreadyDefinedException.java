package br.ufpe.cin.if688.minijava.exceptions.symboltable;

public class ClassMethodAlreadyDefinedException extends Exception {

	private static final long serialVersionUID = 8017794030554810988L;

	public ClassMethodAlreadyDefinedException(String className, String method) {
		super("Method " + method + " is already defined in class " + className + ".");
	}

}
