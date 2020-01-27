 package com.kneat.megalightmeter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.kneat.megalightmeter.helper.SwapiApiHelper;
import com.kneat.megalightmeter.model.StarShip;
import com.kneat.megalightmeter.model.StarShipResource;
import com.kneat.megalightmeter.service.SwapiApiService;

/**
 * @see {@linkplain SwapiApiService}
 */
@Component
public class SwapiApiServiceImpl implements SwapiApiService {
	private final Logger LOGGER = LoggerFactory.getLogger(SwapiApiServiceImpl.class);
	@Autowired private SwapiApiHelper helper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable("starships")
	public List<StarShip> retrieveAllStarShips() {
		final List<StarShip> starShips = new ArrayList<>();
		String url = "https://swapi.co/api/starships";

		while(Objects.nonNull(url)) {
			LOGGER.info("Retrieving data from: " + url);

			final StarShipResource resource = helper.retrieveStarShipsFrom(url);

			url = resource.getNext();
			starShips.addAll(resource.onlyValidStarShips());
		}

		return starShips;
	}
}