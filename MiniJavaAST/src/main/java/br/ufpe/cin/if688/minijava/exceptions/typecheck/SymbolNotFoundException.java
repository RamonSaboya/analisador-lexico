package br.ufpe.cin.if688.minijava.exceptions.typecheck;

public class SymbolNotFoundException extends Exception {

	private static final long serialVersionUID = -4385727317415758336L;

	public SymbolNotFoundException(String symbol, String at) {
		super("Cannot find symbol " + symbol + ". (" + at + ")");
	}

}
