package com.kneat.megalightmeter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This implementation converts consumable in years to hours.
 */
@Component(YearsConsumableConverter.BEAN_NAME)
public class YearsConsumableConverter implements ConsumableConverter {
	public static final String BEAN_NAME = "YearsConsumableConverter";

	@Autowired DaysConsumableConverter daysConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer converter(final Integer consumables) {
		final Integer consumablesInDays = consumables * 365;
		return daysConverter.converter(consumablesInDays);
	}
}