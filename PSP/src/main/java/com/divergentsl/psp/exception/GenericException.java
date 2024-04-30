package com.divergentsl.psp.exception;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final transient Object[] args;

	public GenericException(String message, Object[] args) {
		super(message);
		this.args = args;
	}

	public Object[] getArgs() {
		return args;
	}

}
