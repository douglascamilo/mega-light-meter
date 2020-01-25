package com.kneat.megalightmeter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This implementation converts consumable in years to hours.
 */
@Component(YearToHourConsumableConverter.BEAN_NAME)
public class YearToHourConsumableConverter implements ConsumableConverter {
	public static final String BEAN_NAME = "YearToHourConsumableConverter";

	@Autowired private DayToHourConsumableConverter dayToHourConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer converter(final Integer consumables) {
		final Integer consumablesInDays = consumables * 365;
		return dayToHourConverter.converter(consumablesInDays);
	}
}