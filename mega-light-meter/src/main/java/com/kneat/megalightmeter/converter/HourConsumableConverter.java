package com.kneat.megalightmeter.converter;

import org.springframework.stereotype.Component;

/**
 * This implementation does not need to perform any calculation. Only returns the given parameter.
 */
@Component(HourConsumableConverter.BEAN_NAME)
public class HourConsumableConverter implements ConsumableConverter {
	public static final String BEAN_NAME = "HourConsumableConverter";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer converter(final Integer consumables) {
		return consumables;
	}
}