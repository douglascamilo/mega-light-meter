package com.kneat.megalightmeter.zreactor;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class MapTest {

	@Test
	public void map() {
		final Flux<String> data = Flux.just("a", "b", "c")
				.log("ORIGINAL")
				.map(String::toUpperCase)
				.log("UPPERCASE");

		StepVerifier.create(data)
				.expectNext("A", "B", "C")
				.verifyComplete();
	}
}
