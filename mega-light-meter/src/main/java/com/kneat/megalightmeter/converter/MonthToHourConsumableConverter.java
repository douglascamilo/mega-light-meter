package com.kneat.megalightmeter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This implementation converts consumable in months to hours.
 */
@Component(MonthToHourConsumableConverter.BEAN_NAME)
public class MonthToHourConsumableConverter implements ConsumableConverter {
	public static final String BEAN_NAME = "MonthToHourConsumableConverter";

	@Autowired private DayToHourConsumableConverter dayToHourConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer converter(final Integer consumables) {
		final int consumableInDays = consumables * 30;
		return dayToHourConverter.converter(consumableInDays);
	}
}