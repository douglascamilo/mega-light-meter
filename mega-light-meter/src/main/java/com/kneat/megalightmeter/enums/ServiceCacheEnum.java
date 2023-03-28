package com.kneat.megalightmeter.enums;

public enum ServiceCacheEnum {
	GREETINGS("greetings"),
	USER("user"),
	;

	private final String name;

	private ServiceCacheEnum(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
