package com.kneat.megalightmeter.exception;

import org.springframework.http.HttpStatus;

public class TestException extends KneatException {
	private final HttpStatus httpStatus;

	public TestException(final HttpStatus httpStatus, final String... messageParams) {
		super("generic.error", messageParams);
		this.httpStatus = httpStatus;
	}

	@Override
	public HttpStatus getStatusCode() {
		return httpStatus;
	}
}
