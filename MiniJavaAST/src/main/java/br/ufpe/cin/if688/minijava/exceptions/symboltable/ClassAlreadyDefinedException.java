package br.ufpe.cin.if688.minijava.exceptions.symboltable;

public class ClassAlreadyDefinedException extends Exception {

	private static final long serialVersionUID = 6858193564106892326L;

	public ClassAlreadyDefinedException(String className) {
		super("Class " + className + " is already defined in program.");
	}

}
