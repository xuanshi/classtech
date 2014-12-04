package com.classtech.service;

public class ClassTechServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ClassTechServiceException(String message) {
		super(message);
	}

	public ClassTechServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
