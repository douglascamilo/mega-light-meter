package com.kneat.megalightmeter.converter;

import org.springframework.stereotype.Component;

/**
 * This implementation converts consumable in days to hours.
 */
@Component(DayToHourConsumableConverter.BEAN_NAME)
public class DayToHourConsumableConverter implements ConsumableConverter {
	public static final String BEAN_NAME = "DayToHourConsumableConverter";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer converter(final Integer consumables) {
		return consumables * 24;
	}
}