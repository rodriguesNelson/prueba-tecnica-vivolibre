package com.knowmadmood.technicaltest.exceptions;

import java.util.List;
import lombok.Getter;

@Getter
public class VivolibreBusinessException extends VivolibreRuntimeException {

	private static final long serialVersionUID = 1L;

	private final List<String> details;

	public VivolibreBusinessException(String message, String errorCode) {
		super(message, errorCode);
		this.details = null;
	}

	public VivolibreBusinessException(String message, String errorCode, List<String> details) {
		super(message, errorCode);
		this.details = details;
	}
}
