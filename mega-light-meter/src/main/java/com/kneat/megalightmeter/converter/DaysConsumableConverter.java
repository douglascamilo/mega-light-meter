package com.kneat.megalightmeter.converter;

import org.springframework.stereotype.Component;

/**
 * This implementation converts consumable in days to hours.
 */
@Component(DaysConsumableConverter.BEAN_NAME)
public class DaysConsumableConverter implements ConsumableConverter {
	public static final String BEAN_NAME = "DaysConsumableConverter";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer converter(final Integer consumables) {
		return consumables * 24;
	}
}