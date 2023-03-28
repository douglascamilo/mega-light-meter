package com.kneat.megalightmeter.zreactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

@Slf4j
public class HotStreamTest2 {

	@Test
	public void hot() throws InterruptedException {
		final int factor = 10;
		log.info("START");

		final CountDownLatch countDownLatch = new CountDownLatch(2);

		final Flux<Integer> live = Flux.range(0, 10)
				.delayElements(Duration.ofMillis(factor))
				.share();

		final List<Integer> one = new ArrayList<>();
		final List<Integer> two = new ArrayList<>();

		live.doFinally(this.signalTypeConsumer(countDownLatch)).subscribe(one::add);
		Thread.sleep(factor * 2);

		live.doFinally(this.signalTypeConsumer(countDownLatch)).subscribe(two::add);
		countDownLatch.await(5, TimeUnit.SECONDS);

		log.info("First Subscriber: {}", one.toString());
		log.info("Second Subscriber: {}", two.toString());
		log.info("STOP");

		assertThat(one.size()).isGreaterThan(two.size());
	}

	private Consumer<SignalType> signalTypeConsumer(final CountDownLatch countDownLatch) {
		return signal -> {
			if (signal == SignalType.ON_COMPLETE) {
				try {
					countDownLatch.countDown();
					log.info("await()...");
				} catch (final Exception e) {
					throw new RuntimeException(e);
				}
			}
		};
	}
}
