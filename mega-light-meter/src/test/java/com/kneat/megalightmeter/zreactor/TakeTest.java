package com.kneat.megalightmeter.zreactor;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class TakeTest {

	@Test
	public void take() {
		final Integer count = 10;
		final Flux<Integer> take = this.range()
				.take(count)
				.log("take");

		StepVerifier.create(take)
				.expectNextCount(count)
				.verifyComplete();
	}

	@Test
	public void takeUntil() {
		final Integer count = 50;
		final Flux<Integer> take = this.range()
				.takeUntil(index -> index == count)
				.log("takeUntil");

		StepVerifier.create(take)
				.expectNextCount(count)
				.verifyComplete();
	}

	@Test
	public void takeLast() {
		final int last = 15;
		final Flux<Integer> take = this.range()
				.takeLast(last)
				.log("takeLast");

		StepVerifier.create(take)
				.expectNextCount(last)
				.verifyComplete();
	}

	@Test
	public void takeWhile() {
		final int limit = 25;
		final Flux<Integer> take = this.range()
				.takeWhile(index -> index <= limit)
				.log("takeWhile");

		StepVerifier.create(take)
				.expectNextCount(limit)
				.verifyComplete();
	}

	public Flux<Integer> range() {
		return Flux.range(1, 1000);
	}
}
