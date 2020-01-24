package com.kneat.megalightmeter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This implementation converts consumable in months to hours.
 */
@Component(MonthsConsumableConverter.BEAN_NAME)
public class MonthsConsumableConverter implements ConsumableConverter {
	public static final String BEAN_NAME = "MonthsConsumableConverter";

	@Autowired private DaysConsumableConverter daysConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer converter(final Integer consumables) {
		final int consumableInDays = consumables * 30;
		return daysConverter.converter(consumableInDays);
	}
}