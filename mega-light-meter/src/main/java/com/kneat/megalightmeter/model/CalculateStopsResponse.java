package com.kneat.megalightmeter.model;

public class CalculateStopsResponse {
	private final String name;
	private final Integer stops;
	private final String urlResource;

	public CalculateStopsResponse(final String name, final Integer stops, final String urlResource) {
		this.name = name;
		this.stops = stops;
		this.urlResource = urlResource;
	}

	public final String getName() {
		return name;
	}

	public final Integer getStops() {
		return stops;
	}

	public final String getUrlResource() {
		return urlResource;
	}

	@Override
	public String toString() {
		return "CalculateStopsResponse [name=" + name + ", stops=" + stops + ", urlResource=" + urlResource + "]";
	}
}