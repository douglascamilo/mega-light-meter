package com.kneat.megalightmeter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired private SwapiApiHelper helper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StarShip> retrieveAllStarShips() {
		final List<StarShip> starShips = new ArrayList<>();
		String url = "https://swapi.co/api/starships";

		while(Objects.nonNull(url)) {
			final StarShipResource resource = helper.retrieveStarShipsFrom(url);

			url = resource.getNext();
			starShips.addAll(resource.onlyValidStarShips());
		}

		return starShips;
	}
}