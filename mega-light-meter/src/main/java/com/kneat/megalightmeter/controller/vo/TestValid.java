package com.kneat.megalightmeter.controller.vo;

import javax.validation.constraints.NotNull;

public class TestValid {
	@NotNull private String name;

	public final String getName() {
		return name;
	}

	public final void setName(final String name) {
		this.name = name;
	}
}
