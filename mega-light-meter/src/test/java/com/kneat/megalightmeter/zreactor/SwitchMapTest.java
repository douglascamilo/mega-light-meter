package com.kneat.megalightmeter.zreactor;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class SwitchMapTest {

	@Test
	public void switchMapWithLookaheads() {
		final Flux<String> source = Flux
				.just("re", "rea", "reac", "react", "reactive")
				.delayElements(Duration.ofMillis(500))
				.log("typed")
				.switchMap(this::lookup)
				.log("prediction");

		StepVerifier.create(source)
//				.expectNext("re -> reactive")
//				.expectNext("rea -> reactive")
//				.expectNext("reac -> reactive")
//				.expectNext("react -> reactive")
				.expectNext("reactive -> reactive")
				.verifyComplete();
	}

	private Flux<String> lookup(final String word) {
		return Flux.just(word + " -> reactive")
				.delayElements(Duration.ofMillis(600));
	}
}
