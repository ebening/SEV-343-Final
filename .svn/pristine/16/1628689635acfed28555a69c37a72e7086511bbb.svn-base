package com.adinfi.catalogs.bos.exception.catalogs;

public class LoginException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_ERROR = -100;
	int errorCode = DEFAULT_ERROR;
	String errorMessage;

	public LoginException() {
		super();
	}

	public LoginException(Throwable throwable) {
		super(throwable);
	}

	public LoginException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public LoginException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
		this.errorMessage = errorMessage;
	}

	public LoginException(int errorCode, String errorMessage,
			Throwable throwable) {
		super(errorMessage, throwable);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
