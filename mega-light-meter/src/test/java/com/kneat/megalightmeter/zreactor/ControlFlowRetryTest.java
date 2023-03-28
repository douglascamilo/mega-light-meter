package com.kneat.megalightmeter.zreactor;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.context.Context;
import reactor.util.retry.Retry;

@Slf4j
public class ControlFlowRetryTest {
	private static final String ERRORED_KEY = "ERRORED_KEY";

	@Test
	public void retry() {
		System.out.println("--- retry() ---");

		final Mono<String> producer = Mono.<String> create(sink -> {
			final AtomicBoolean errored = sink.currentContext().get(ERRORED_KEY);

			if (!errored.get()) {
				errored.set(true);
				sink.error(new RuntimeException("Nope!"));
			} else {
				sink.success("hello!");
			}
		})
		.subscriberContext(Context.of(ERRORED_KEY, new AtomicBoolean()));

		final Mono<String> retryOnError = producer
				.doOnError(throwable -> log.error("An error has occurred! {}", throwable.getMessage()))
				.doOnNext(valor -> log.info("Received value: {}", valor))
				.retry();

		StepVerifier.create(retryOnError)
				.expectNext("hello!")
				.verifyComplete();

		System.out.println("--- retry() ---");
		System.out.println();
	}

	@Test
	public void multipleRetries() {
		System.out.println("--- multipleRetries() ---");

		final AtomicInteger counter = new AtomicInteger(0);
		final int maxCounter = 5;

		final String realValue = "Successfully executed...";

		final Mono<String> producer = Mono
				.<String>create(sink ->
					{
						if (counter.getAndIncrement() == maxCounter) {
							sink.success(realValue);
						} else {
							final String message = MessageFormat.format("Attempt number - {0}", counter.get());
							sink.error(new RuntimeException(message));
						}
					})
				.doOnError(throwable -> log.error(throwable.getMessage()))
				.doOnNext(value -> log.info(value))
				.retryWhen(
					Retry.fixedDelay(maxCounter, Duration.ofMillis(300))
							.filter(RuntimeException.class::isInstance)
				)
				.subscribeOn(Schedulers.parallel());

		StepVerifier.create(producer)
				.expectNext(realValue)
				.verifyComplete();

		System.out.println("--- multipleRetries() ---");
		System.out.println();
	}
}
