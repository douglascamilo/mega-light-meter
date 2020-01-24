package com.kneat.megalightmeter.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representation of the returned JSON from /starships resource.
 */
public class StarShipResource {
	private String next;
	private List<StarShip> results = new ArrayList<>();

	public final String getNext() {
		return next;
	}

	public final void setNext(final String next) {
		this.next = next;
	}

	public final List<StarShip> getResults() {
		return results;
	}

	public final void setResults(final List<StarShip> results) {
		this.results = results;
	}

	/**
	 * Returns only the star ships which have valid values for Consumables and MegaLight.
	 *
	 * @return List of valid {@linkplain StarShip}
	 */
	public List<StarShip> onlyValidStarShips() {
		return this.results.stream()
				.filter(StarShip::hasConsumablesAndMegaLightValues)
				.collect(Collectors.toList());
	}
}