package com.kneat.megalightmeter.enums;

import java.util.Arrays;
import java.util.List;

import com.kneat.megalightmeter.converter.ConsumableConverter;
import com.kneat.megalightmeter.converter.DaysConsumableConverter;
import com.kneat.megalightmeter.converter.HoursConsumableConverter;
import com.kneat.megalightmeter.converter.MonthsConsumableConverter;
import com.kneat.megalightmeter.converter.WeeksConsumableConverter;
import com.kneat.megalightmeter.converter.YearsConsumableConverter;
import com.kneat.megalightmeter.exception.InvalidConsumablesUnitException;

/**
 *  Enumeration that matches the consumable unit(s) to a {@linkplain ConsumableConverter} implementation's name.
 */
public enum ConsumablesUnitEnum {
	YEARS(YearsConsumableConverter.BEAN_NAME, "years", "year"),
	MONTHS(MonthsConsumableConverter.BEAN_NAME, "months", "month"),
	WEEKS(WeeksConsumableConverter.BEAN_NAME, "weeks", "week"),
	DAYS(DaysConsumableConverter.BEAN_NAME, "days", "day"),
	HOURS(HoursConsumableConverter.BEAN_NAME, "hours", "hour"),
	;

	private final List<String> consumablesUnit;
	private final String beanName;

	private ConsumablesUnitEnum(final String beanName, final String... consumablesUnit) {
		this.beanName = beanName;
		this.consumablesUnit = Arrays.asList(consumablesUnit);
	}

	public final String getBeanName() {
		return beanName;
	}

	public final List<String> getConsumablesUnit() {
		return consumablesUnit;
	}

	/**
	 * Returns a {@linkplain ConsumablesUnitEnum} which matches with the given unit.
	 *
	 * @param unit measure unit for consumables
	 * @return Cons
	 * @throws InvalidConsumablesUnitException
	 */
	public static ConsumablesUnitEnum getEnumBy(final String unit) throws InvalidConsumablesUnitException {
		return Arrays.asList(values())
				.stream()
				.filter(itemEnum -> itemEnum.consumablesUnit.contains(unit))
				.findFirst()
				.orElseThrow(() -> {
					throw new InvalidConsumablesUnitException("invalid.consumables.unit", unit);
				});
	}
}