package com.kneat.megalightmeter.facade;

import java.util.List;

import com.kneat.megalightmeter.exception.SwapiApiIntegrationException;
import com.kneat.megalightmeter.model.CalculateStopsResponse;

/**
 * This class encloses the business logic of getting all star ships from Swapi
 * API and calculates all the necessary stops for each star ship for the given
 * distance(in Mega Lights).
 */
public interface CalculateStopsFacade {

	/**
	 * Returns all necessary stops organized by star ship.
	 *
	 * @param distanceInMGLT - Distance in MegaLights
	 * @return List of {@linkplain CalculateStopsResponse} containing all the star ships and their respective
	 *         how many stops are necessary to resupply, for the given distance.
	 * @throws SwapiApiIntegrationException
	 */
	List<CalculateStopsResponse> getAllNeededStopsByStarShip(Long distanceInMGLT) throws SwapiApiIntegrationException;
}