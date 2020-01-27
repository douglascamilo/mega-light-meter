package com.kneat.megalightmeter.converter;

/**
 * Defines the contract for a specific consumable unit converter.
 */
public interface ConsumableConverter {

	/**
	 * Perform the conversion to consumable in hours.
	 *
	 * @param consumables in a given unit
	 * @return consumable converted to hours
	 */
	Integer converter(Integer consumables);
}