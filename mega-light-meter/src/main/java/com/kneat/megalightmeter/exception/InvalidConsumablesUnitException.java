package com.kneat.megalightmeter.exception;

import com.kneat.megalightmeter.enums.ConsumablesUnitEnum;

/**
 * This exception is thrown when occurs an error trying get an
 * {@linkplain ConsumablesUnitEnum} from an invalid consumable unit.
 */
public class InvalidConsumablesUnitException extends KneatException {
	private static final long serialVersionUID = 4611572827745459472L;

	public InvalidConsumablesUnitException(final String... messageParams) {
		super("invalid.consumables.unit", messageParams);
	}
}