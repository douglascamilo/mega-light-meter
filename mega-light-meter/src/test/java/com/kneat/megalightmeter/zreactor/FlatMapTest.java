package com.kneat.megalightmeter.zreactor;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FlatMapTest {

	@Test
	public void flatMap() {
		final Flux<Integer> data = Flux
				.just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
				.flatMap(this::delayReplyFor)
				.log();

		StepVerifier.create(data)
				.expectNext(3, 2, 1)
				.verifyComplete();
	}

	private Flux<Integer> delayReplyFor(final Pair pair) {
		return Flux.just(pair.id)
				.delayElements(Duration.ofMillis(pair.delay));
	}

	@AllArgsConstructor
	static class Pair {
		private final int id;
		private final long delay;
	}
}
