package com.kneat.megalightmeter.zreactor;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class OnErrorReturnTest {

	private final Flux<Integer> resultsInError = Flux.just(1, 2, 3).flatMap(counter -> {
		if (counter == 2) {
			return Flux.error(new IllegalArgumentException("Oops!"));
		}

		return Flux.just(counter);
	});

	@Test
	public void onErrorReturn() {
		final Flux<Integer> integerFlux = resultsInError.onErrorReturn(0)
				.log();

		StepVerifier.create(integerFlux)
				.expectNext(1, 0)
				.verifyComplete();
	}
}
