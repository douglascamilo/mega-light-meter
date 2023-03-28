package com.kneat.megalightmeter.zreactor;

import java.text.MessageFormat;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

public class ControlFlowZipTest {

	@Test
	public void zip() {
		final Flux<Integer> first = Flux.just(1, 2, 3);
		final Flux<String> second = Flux.just("a", "b", "c");

		final Flux<String> zip = Flux.zip(first, second)
				.map(this::from)
				.log();

		StepVerifier.create(zip)
				.expectNext("1:a")
				.expectNext("2:b")
				.expectNext("3:c")
				.verifyComplete();
	}

	public String from(final Tuple2<Integer, String> tuple) {
		return MessageFormat.format("{0}:{1}", tuple.getT1(), tuple.getT2());
	}
}
