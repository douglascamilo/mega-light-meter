package com.kneat.megalightmeter.exception;

import org.springframework.http.HttpStatus;

/**
 * Abstract implementation for application exceptions. By default, this
 * Exception returns <b>500(Internal Server Error)</b> status
 * code. If it isn't the expected behavior, just override the method
 * {@link #getStatusCode()}.
 */
public abstract class KneatException extends RuntimeException {
	private static final long serialVersionUID = 2000896977769533798L;
	private final String[] messageParams;

	public KneatException(final String messageId, final String[] messageParams) {
		super(messageId);
		this.messageParams = messageParams;
	}

	public String[] getMessageParams() {
		return messageParams;
	}

	public HttpStatus getStatusCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}