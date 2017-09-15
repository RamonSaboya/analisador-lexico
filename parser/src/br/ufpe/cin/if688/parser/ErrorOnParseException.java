package br.ufpe.cin.if688.parser;

public class ErrorOnParseException extends Exception {

	private static final long serialVersionUID = 5626971921971638722L;

	public ErrorOnParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorOnParseException(Throwable cause) {
		super(cause);
	}

	public ErrorOnParseException(String message) {
		super(message);
	}

	public ErrorOnParseException() {
		super();
	}

}
