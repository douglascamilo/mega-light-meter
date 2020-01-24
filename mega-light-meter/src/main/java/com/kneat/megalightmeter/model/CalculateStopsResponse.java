package com.kneat.megalightmeter.model;

public class CalculateStopsResponse {
	private final String name;
	private final Integer stops;

	public CalculateStopsResponse(final String name, final Integer stops) {
		this.name = name;
		this.stops = stops;
	}

	public final String getName() {
		return name;
	}

	public final Integer getStops() {
		return stops;
	}

	@Override
	public String toString() {
		return "CalculateStopsResponse [name=" + name + ", stops=" + stops + "]";
	}
}