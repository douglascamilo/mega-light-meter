package com.kneat.megalightmeter.zreactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

@Slf4j
public class SchedulersHookTest {

	@Test
	public void onScheduleHook() {
		final AtomicInteger counter = new AtomicInteger();

		Schedulers.onScheduleHook("my-hook", runnable -> () -> {
			final String threadName = Thread.currentThread().getName();
			counter.incrementAndGet();

			log.info("Before execution: {}", threadName);
			runnable.run();
			log.info("After execution: {}", threadName);
		});

		final Flux<Integer> fluxInteger = Flux.just(1, 2, 3)
				.delayElements(Duration.ofMillis(1))
				.subscribeOn(Schedulers.immediate());

		StepVerifier.create(fluxInteger)
				.expectNext(1, 2, 3)
				.verifyComplete();

		assertThat(counter.get()).isEqualTo(3);
	}
}
