package com.knowmadmood.technicaltest.exceptions;

import java.util.List;

public class VivolibreGeneralException extends VivolibreBusinessException {

	private static final long serialVersionUID = 1L;

	public VivolibreGeneralException(String message, String errorCode) {
		super(message, errorCode);
	}

	public VivolibreGeneralException(String message, String errorCode, List<String> details) {
		super(message, errorCode, details);
	}
}
