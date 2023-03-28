package com.kneat.megalightmeter.zreactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class OnErrorMapTest {

	@Test
	public void onErrorMap() {
		final AtomicInteger counter = new AtomicInteger();
		final Flux<Object> resultsInError = Flux.error(new IllegalArgumentException("Oops!"));

		final Flux<Object> errorHandlingStream = resultsInError
				.log()
				.onErrorMap(IllegalArgumentException.class, ex -> new GenericException(ex.getMessage()))
				.log()
				.doOnError(GenericException.class, ge -> counter.getAndIncrement());

		StepVerifier.create(errorHandlingStream)
				.expectError()
				.verify();

		assertThat(counter.get()).isEqualTo(1);
	}

	class GenericException extends RuntimeException {
		private static final long serialVersionUID = -7967372590168010347L;

		public GenericException(final String message) {
			super(message);
		}
	}
}
