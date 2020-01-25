package com.kneat.megalightmeter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This implementation converts consumable in weeks to hours.
 */
@Component(WeekToHourConsumableConverter.BEAN_NAME)
public class WeekToHourConsumableConverter implements ConsumableConverter {
	public static final String BEAN_NAME = "WeekToHourConsumableConverter";

	@Autowired DayToHourConsumableConverter dayToHourConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer converter(final Integer consumables) {
		final int consumableInDays = consumables * 7;
		return dayToHourConverter.converter(consumableInDays);
	}
}