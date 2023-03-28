package com.kneat.megalightmeter.zreactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;
import reactor.test.StepVerifier;

@Slf4j
public class TransformTest {

	@Test
	public void transform() {
		final AtomicBoolean finished = new AtomicBoolean();

		final Flux<String> letters = Flux.just("A", "B", "C")
				.transform(flux -> flux.doFinally(signal -> finished.set(signal == SignalType.ON_COMPLETE)))
				.doOnSubscribe(subs -> log.info("doOnSubscribe() - invoked!"))
				.doOnNext(log::info);

		StepVerifier.create(letters)
				.expectNextCount(3)
				.verifyComplete();

		assertThat(finished.get()).isTrue();
	}
}
