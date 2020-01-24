package com.kneat.megalightmeter.model;

/**
 * An object that handle the consumables string
 */
public class ConsumablesObject {
	private final Integer value;
	private final String unit;

	public ConsumablesObject(final String consumables) {
		final String[] consumableSplitted = consumables.split(" ");

		value = Integer.valueOf(consumableSplitted[0]);
		unit = consumableSplitted[1];
	}

	public final Integer getValue() {
		return value;
	}

	public final String getUnit() {
		return unit;
	}
}