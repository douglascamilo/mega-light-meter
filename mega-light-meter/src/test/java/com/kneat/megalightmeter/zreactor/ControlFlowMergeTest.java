package com.kneat.megalightmeter.zreactor;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ControlFlowMergeTest {

	@Test
	public void merge() {
		final Flux<Integer> fastest = Flux.just(5, 6);
		final Flux<Integer> secondFastest = Flux.just(1, 2).delayElements(Duration.ofMillis(2));
		final Flux<Integer> thirdFastest = Flux.just(3, 4).delayElements(Duration.ofMillis(20));

		final Flux<Integer> merge = Flux.merge(secondFastest, thirdFastest, fastest)
				.log();

		StepVerifier.create(merge)
				.expectNext(5, 6, 1, 2, 3, 4)
				.verifyComplete();
	}
}
