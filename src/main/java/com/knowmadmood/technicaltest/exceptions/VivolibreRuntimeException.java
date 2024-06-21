package com.knowmadmood.technicaltest.exceptions;

import lombok.Getter;

@Getter
public class VivolibreRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String errorCode;

	public VivolibreRuntimeException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
