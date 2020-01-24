package com.kneat.megalightmeter.model;

import java.util.Optional;

import org.apache.logging.log4j.util.Strings;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * Representation of a Star Ship, returned by Swapi API.
 */
public class StarShip {
	private String name;
	@JsonAlias("MGLT")
	private String megaLight;
	private String consumables;
	private String url;

	public final String getName() {
		return name;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	public final String getMegaLight() {
		return megaLight;
	}

	public final void setMegaLight(final String megaLight) {
		this.megaLight = megaLight;
	}

	public final String getConsumables() {
		return consumables;
	}

	public final void setConsumables(final String consumables) {
		this.consumables = consumables;
	}

	public final String getUrl() {
		return url;
	}

	public final void setUrl(final String url) {
		this.url = url;
	}

	public boolean hasConsumablesAndMegaLightValues() {
		return this.isConsumablesValid() && this.isMegaLightValid();
	}

	private boolean isMegaLightValid() {
		return this.isNotUnknown(megaLight);
	}

	private boolean isConsumablesValid() {
		return this.isNotUnknown(consumables);
	}

	private boolean isNotUnknown(final String value) {
		return !Optional.ofNullable(Strings.trimToNull(value))
				.orElse("unknown")
				.toLowerCase()
				.equals("unknown");
	}

	@Override
	public String toString() {
		return "StarShip [name=" + name + ", megaLight=" + megaLight + ", consumables=" + consumables + ", url=" + url
				+ "]";
	}
}