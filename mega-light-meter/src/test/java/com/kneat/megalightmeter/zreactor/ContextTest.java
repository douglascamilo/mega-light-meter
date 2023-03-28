package com.kneat.megalightmeter.zreactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.context.Context;

@Slf4j
public class ContextTest {

	@Test
	public void context() throws InterruptedException {
		final ConcurrentHashMap<String, AtomicInteger> observedContextValues = new ConcurrentHashMap<>();
		final Integer max = 18;
		final String key = "value1";

		final CountDownLatch cdl = new CountDownLatch(max);

		Flux.range(0, max)
				.delayElements(Duration.ofMillis(1))
				.doOnEach(signal -> {
					final Context currentContext = signal.getContext();

					if (signal.isOnNext()) {
						final ValueWrapper wrapper = currentContext.get(key);
						log.info("Context actual value: {}", wrapper.getValue().getAndIncrement());

						observedContextValues
								.computeIfAbsent(key, k -> new AtomicInteger(0))
								.incrementAndGet();
					} else {
						log.info("Another SignalType was emmited: {}", signal.getType());
					}
				})
				.subscriberContext(context -> Context.of(key, new ValueWrapper()))
				.subscribe(integer -> cdl.countDown());

		cdl.await();
		assertThat(observedContextValues.get(key).get()).isEqualTo(max);
	}

	@Value
	private static class ValueWrapper {
		private AtomicInteger value = new AtomicInteger();
	}
}
