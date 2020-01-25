package com.kneat.megalightmeter.enums;

import java.util.Arrays;
import java.util.List;

import com.kneat.megalightmeter.converter.ConsumableConverter;
import com.kneat.megalightmeter.converter.DayToHourConsumableConverter;
import com.kneat.megalightmeter.converter.HourConsumableConverter;
import com.kneat.megalightmeter.converter.MonthToHourConsumableConverter;
import com.kneat.megalightmeter.converter.WeekToHourConsumableConverter;
import com.kneat.megalightmeter.converter.YearToHourConsumableConverter;
import com.kneat.megalightmeter.exception.InvalidConsumablesUnitException;

/**
 *  Enumeration that matches the consumable unit(s) to a {@linkplain ConsumableConverter} implementation's name.
 */
public enum ConsumablesUnitEnum {
	YEARS(YearToHourConsumableConverter.BEAN_NAME, "years", "year"),
	MONTHS(MonthToHourConsumableConverter.BEAN_NAME, "months", "month"),
	WEEKS(WeekToHourConsumableConverter.BEAN_NAME, "weeks", "week"),
	DAYS(DayToHourConsumableConverter.BEAN_NAME, "days", "day"),
	HOURS(HourConsumableConverter.BEAN_NAME, "hours", "hour"),
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
	 * Returns a {@linkplain ConsumablesUnitEnum} which matches with the given consumable unit.
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