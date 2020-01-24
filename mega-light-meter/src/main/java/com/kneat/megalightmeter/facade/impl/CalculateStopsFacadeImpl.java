package com.kneat.megalightmeter.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kneat.megalightmeter.facade.CalculateStopsFacade;
import com.kneat.megalightmeter.model.CalculateStopsResponse;
import com.kneat.megalightmeter.model.StarShip;
import com.kneat.megalightmeter.service.CalculateStopsService;
import com.kneat.megalightmeter.service.SwapiApiService;

/**
 * @see {@linkplain CalculateStopsFacade}
 */
@Service
public class CalculateStopsFacadeImpl implements CalculateStopsFacade {
	@Autowired private SwapiApiService swapiService;
	@Autowired private CalculateStopsService calculateStopsService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAllNeededStopsByStarShip(final Long distanceInMGLT) {
		final List<StarShip> retrievedStarShips = swapiService.retrieveAllStarShips();
		final List<CalculateStopsResponse> response =
				calculateStopsService.calculate(distanceInMGLT, retrievedStarShips);

		return response.toString();
	}
}