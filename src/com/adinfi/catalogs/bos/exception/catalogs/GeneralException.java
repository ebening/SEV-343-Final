package com.adinfi.catalogs.bos.exception.catalogs;

public class GeneralException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_ERROR = -100;
	int errorCode = DEFAULT_ERROR;
	String errorMessage;

	public GeneralException() {
		super();
	}

	public GeneralException(Throwable throwable) {
		super(throwable);
	}

	public GeneralException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public GeneralException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
		this.errorMessage = errorMessage;
	}

	public GeneralException(int errorCode, String errorMessage,
			Throwable throwable) {
		super(errorMessage, throwable);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
