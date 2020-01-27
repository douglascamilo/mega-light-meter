package com.kneat.megalightmeter.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kneat.megalightmeter.converter.ConsumableToHoursConverter;
import com.kneat.megalightmeter.model.CalculateStopsResponse;
import com.kneat.megalightmeter.model.StarShip;
import com.kneat.megalightmeter.service.CalculateStopsService;

/**
 * Default implementation of the {@linkplain CalculateStopsService} contract
 */
@Component
public class CalculateStopsServiceImpl implements CalculateStopsService {
	@Autowired private ConsumableToHoursConverter consumableConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CalculateStopsResponse> calculate(
			final Long totalDistanceInMGLT, final List<StarShip> retrievedStarships) {

		return retrievedStarships.stream()
				.map(starship -> this.calculateStopsByStarship(totalDistanceInMGLT, starship))
				.collect(Collectors.toList());
	}

	private CalculateStopsResponse calculateStopsByStarship(final Long totalDistanceInMGLT, final StarShip starShip) {
		final int consumableInHours = consumableConverter.convert(starShip.getConsumablesObject());
		final long totalStopsToResupply = this.calculateStopsForResupply(
				totalDistanceInMGLT, consumableInHours, starShip.getMegaLightInteger());

		return new CalculateStopsResponse(starShip.getName(), totalStopsToResupply, starShip.getUrl());
	}

	private long calculateStopsForResupply(final long totalDistanceInMGLT, final int starshipConsumableInHours,
			final int starshipSpeed) {

		return ((totalDistanceInMGLT / starshipSpeed) / starshipConsumableInHours);
	}
}