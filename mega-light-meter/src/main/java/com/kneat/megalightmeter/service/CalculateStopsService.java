package com.kneat.megalightmeter.service;

import java.util.List;

import com.kneat.megalightmeter.model.CalculateStopsResponse;
import com.kneat.megalightmeter.model.StarShip;

/**
 * Service responsible in performing the calculation of required stops for the given distance
 */
public interface CalculateStopsService {

	/**
	 * Perform the calculation of required stops(for resupply) to cover the given distance.
	 *
	 * @param distanceInMGLT - Distance in MegaLights
	 * @param retrievedStarShips - List of StarShips
	 * @return A list containing the name and required stops for each StarShip.
	 */
	List<CalculateStopsResponse> calculate(Long distanceInMGLT, List<StarShip> retrievedStarShips);
}