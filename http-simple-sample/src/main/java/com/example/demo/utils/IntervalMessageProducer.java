package com.example.demo.utils;

import java.time.Duration;

import reactor.core.publisher.Flux;

public class IntervalMessageProducer {

	public static Flux<String> produce() {
		return Flux.range(1, 5)
				.delayElements(Duration.ofMillis(1500))
				.map(value -> "Emitted value: " + value);
	}

}
