package com.kneat.megalightmeter.controller.advice;

public class ExceptionResponse {
	private String message;
	private String details;

	public ExceptionResponse() {}

	public ExceptionResponse(final String message, final String details) {
		this.message = message;
		this.details = details;
	}

	public final String getMessage() {
		return message;
	}

	public final void setMessage(final String message) {
		this.message = message;
	}

	public final String getDetails() {
		return details;
	}

	public final void setDetails(final String details) {
		this.details = details;
	}
}