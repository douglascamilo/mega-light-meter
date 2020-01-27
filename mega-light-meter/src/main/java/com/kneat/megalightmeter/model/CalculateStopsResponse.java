package com.kneat.megalightmeter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Representation of the data returned by the rest controller.
 */
public class CalculateStopsResponse {
	private final String name;
	private final Long stops;
	@JsonIgnore
	private final String urlResource;

	public CalculateStopsResponse(final String name, final Long stops, final String urlResource) {
		this.name = name;
		this.stops = stops;
		this.urlResource = urlResource;
	}

	public final String getName() {
		return name;
	}

	public final Long getStops() {
		return stops;
	}

	public final String getUrlResource() {
		return urlResource;
	}

	@Override
	public String toString() {
		return "\n" + name + " -> " + stops + " stops";
	}
}