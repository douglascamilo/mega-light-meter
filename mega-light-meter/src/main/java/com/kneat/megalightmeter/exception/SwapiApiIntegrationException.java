package com.kneat.megalightmeter.exception;

/**
 * Represents an error when trying to retrieve starships from Swapi.co
 */
public class SwapiApiIntegrationException extends KneatException {
	private static final long serialVersionUID = 2039042210896425831L;

	public SwapiApiIntegrationException() {
		super("message.swapi.api.error");
	}
}