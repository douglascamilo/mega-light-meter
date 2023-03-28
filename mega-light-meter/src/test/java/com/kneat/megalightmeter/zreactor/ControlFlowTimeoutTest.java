package com.kneat.megalightmeter.zreactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ControlFlowTimeoutTest {

	@Test
	public void timeout() {
		final Flux<Integer> ids = Flux.just(1, 2, 3)
				.delayElements(Duration.ofSeconds(1))
				.timeout(Duration.ofMillis(500))
				.onErrorResume(this::given)
				.log();

		StepVerifier.create(ids)
				.expectNext(0)
				.verifyComplete();
	}

	private Flux<Integer> given(final Throwable t) {
		assertThat(t instanceof TimeoutException).isTrue();
		return Flux.just(0);
	}
}
