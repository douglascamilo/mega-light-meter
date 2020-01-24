package com.kneat.megalightmeter.facade;

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
	 * @return Formatted String containing all the star ships and their respective
	 *         how many stops are necessary to resupply, for the given distance.
	 */
	String getAllNeededStopsByStarShip(Long distanceInMGLT);
}