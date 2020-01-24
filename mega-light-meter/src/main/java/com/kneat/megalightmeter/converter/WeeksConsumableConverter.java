package com.kneat.megalightmeter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This implementation converts consumable in weeks to hours.
 */
@Component(WeeksConsumableConverter.BEAN_NAME)
public class WeeksConsumableConverter implements ConsumableConverter {
	public static final String BEAN_NAME = "WeeksConsumableConverter";

	@Autowired DaysConsumableConverter daysConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer converter(final Integer consumables) {
		final int consumableInDays = consumables * 7;
		return daysConverter.converter(consumableInDays);
	}
}