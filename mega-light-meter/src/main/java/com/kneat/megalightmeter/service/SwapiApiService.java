package com.kneat.megalightmeter.service;

import java.util.List;

import com.kneat.megalightmeter.exception.SwapiApiIntegrationException;
import com.kneat.megalightmeter.model.StarShip;

/**
 * Responsible for the integration with Swapi API.
 */
public interface SwapiApiService {

	/**
	 * Retrieves all the star ships with their details.
	 *
	 * @return List of {@linkplain StarShip}
	 * @throws SwapiApiIntegrationException
	 */
	List<StarShip> retrieveAllStarShips() throws SwapiApiIntegrationException;
}