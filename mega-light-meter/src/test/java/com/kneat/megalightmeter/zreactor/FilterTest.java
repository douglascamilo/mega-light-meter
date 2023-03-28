package com.kneat.megalightmeter.zreactor;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Slf4j
public class FilterTest {

	@Test
	public void filter() {
		final Flux<Integer> filter = Flux.range(0, 1000)
				.take(5)
				.doOnNext(value -> log.info("TAKE - doOnNext({})", value))
				.filter(value -> value % 2 == 0)
				.doOnNext(value -> log.info("FILTER - doOnNext({})", value))
				.doOnComplete(() -> log.info("Process completed!"));

		StepVerifier.create(filter)
				.expectNext(0)
				.expectNext(2)
				.expectNext(4)
				.verifyComplete();
	}
}
