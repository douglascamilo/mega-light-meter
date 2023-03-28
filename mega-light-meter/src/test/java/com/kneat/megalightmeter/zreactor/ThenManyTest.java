package com.kneat.megalightmeter.zreactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ThenManyTest {

	@Test
	public void thenMany() {
		final AtomicInteger letters = new AtomicInteger();
		final AtomicInteger numbers = new AtomicInteger();

		final Flux<String> lettersPublisher = Flux.just("a", "b", "c", "d")
				.doOnNext(value -> letters.incrementAndGet());

		final Flux<Integer> numbersPublisher = Flux.just(1, 2, 3)
				.doOnNext(number -> numbers.incrementAndGet());

		final Flux<Integer> thisBeforeThat = lettersPublisher
				.log()
				.thenMany(numbersPublisher)
				.log();

		StepVerifier.create(thisBeforeThat)
				.expectNext(1, 2, 3)
				.verifyComplete();

		assertThat(letters.get()).isEqualTo(4);
		assertThat(numbers.get()).isEqualTo(3);
	}
}
