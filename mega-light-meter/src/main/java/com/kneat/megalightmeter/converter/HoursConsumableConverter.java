package com.kneat.megalightmeter.converter;

import org.springframework.stereotype.Component;

/**
 * This implementation does not need to perform any calculation. Only returns the given parameter.
 */
@Component(HoursConsumableConverter.BEAN_NAME)
public class HoursConsumableConverter implements ConsumableConverter {
	public static final String BEAN_NAME = "HoursConsumableConverter";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer converter(final Integer consumables) {
		return consumables;
	}
}