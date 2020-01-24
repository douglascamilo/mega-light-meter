package com.kneat.megalightmeter.exception;

public class InvalidConsumablesUnitException extends RuntimeException {
	private static final long serialVersionUID = -5371049601983498737L;
	private final String[] messageParams;

	public InvalidConsumablesUnitException(final String messageId, final String... messageParams) {
		super(messageId);
		this.messageParams = messageParams;
	}

	public final String[] getMessageParams() {
		return messageParams;
	}
}