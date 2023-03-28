package com.kneat.megalightmeter.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextResponses {
	private final List<TextResponse> responses = new ArrayList<>();

	public final List<TextResponse> getResponses() {
		return Collections.unmodifiableList(responses);
	}

	public TextResponses addResponse(final TextResponse response) {
		responses.add(response);
		return this;
	}
}
