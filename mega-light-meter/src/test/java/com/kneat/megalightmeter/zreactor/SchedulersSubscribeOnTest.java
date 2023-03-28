package com.kneat.megalightmeter.zreactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

@Slf4j
public class SchedulersSubscribeOnTest {

	@Test
	public void subscribeOn() {
		final String rsbThreadName = this.getClass().getSimpleName();
		final ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();

		final ExecutorService executor = Executors.newFixedThreadPool(3, runnable -> {
			final Runnable wrapper = () -> {
				final String key = Thread.currentThread().getName();
				final AtomicInteger result = map.computeIfAbsent(key, s -> new AtomicInteger());
				result.incrementAndGet();

				runnable.run();
			};

			return new Thread(wrapper, rsbThreadName);
		});

		final Scheduler scheduler = Schedulers.fromExecutorService(executor);

		final Flux<Integer> integerMono = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
				.subscribeOn(scheduler)
				.doOnNext(number -> log.info("Executed thread: {}", Thread.currentThread().getName()))
				.doFinally(signal -> map.forEach((key, value) -> log.info("{} = {}", key, value)));

		StepVerifier.create(integerMono)
				.expectNextCount(12)
				.verifyComplete();

		final AtomicInteger atomicInteger = map.get(rsbThreadName);
		assertThat(atomicInteger.get()).isEqualTo(1);
	}
}
