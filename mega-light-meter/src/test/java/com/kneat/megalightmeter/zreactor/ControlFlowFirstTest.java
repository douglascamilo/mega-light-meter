package com.kneat.megalightmeter.zreactor;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ControlFlowFirstTest {

	@Test
	public void first() {
		final Flux<Integer> slow = Flux.just(1, 2, 3).delayElements(Duration.ofMillis(10));
		final Flux<Integer> fast = Flux.just(4, 5, 6, 7).delayElements(Duration.ofMillis(2));
		final Flux<Integer> first = Flux.first(slow, fast)
				.log()
				;

		StepVerifier.create(first)
				.expectNext(4, 5, 6, 7)
				.verifyComplete();
	}
}
