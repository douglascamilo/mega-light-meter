package com.kneat.megalightmeter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.kneat.megalightmeter.enums.ConsumablesUnitEnum;
import com.kneat.megalightmeter.model.ConsumablesObject;

/**
 * Encloses the logic in converting consumable value from a given unit(days, weeks, months, etc) to hours.
 */
@Component
public class ConsumableToHoursConverter {
	@Autowired private ApplicationContext applicationContext;

	/**
	 * Converts the consumable value to hours.
	 *
	 * @param consumablesObject consumable data
	 * @return consumable value in hours
	 */
	public Integer convert(final ConsumablesObject consumablesObject) {
		final ConsumableConverter converter = this.getConsumableConverter(consumablesObject.getUnit());
		return converter.converter(consumablesObject.getValue());
	}

	private ConsumableConverter getConsumableConverter(final String consumablesUnit) {
		final ConsumablesUnitEnum consumablesUnitEnum = ConsumablesUnitEnum.getEnumBy(consumablesUnit);
		return applicationContext.getBean(consumablesUnitEnum.getBeanName(), ConsumableConverter.class);
	}
}