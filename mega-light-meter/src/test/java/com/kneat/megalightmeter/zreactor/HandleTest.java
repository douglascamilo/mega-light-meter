package com.kneat.megalightmeter.zreactor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.context.Context;

@Slf4j
public class HandleTest {

	@Test
	public void handle() {
		log.info("### ERROR Flow ###");
//		final Flux<Integer> errorFlux = this.handle(5, 4)
//				.log("ERROR");
//
//		StepVerifier.create(errorFlux)
//				.expectNext(0, 1, 2, 3)
//				.expectError(IllegalArgumentException.class)
//				.verify();

		System.out.println();

		log.info("### SUCCESS Flow ###");
		final Flux<Integer> successFlux = this.handle(3, 3)
				.log("SUCCESS");

		StepVerifier.create(successFlux)
				.expectNext(0, 1, 2)
				.verifyComplete();
	}

	private Flux<Integer> handle(final int max, final int numberToError) {
		return Flux.range(0, max)
				.<Integer> handle((value, sink) -> {
					final Context currentContext = sink.currentContext();

					final List<Integer> upTo = new ArrayList<>();

					for (int i = 0; i < numberToError; i++) {
						upTo.add(i);
					}

					if (upTo.contains(value)) {
						sink.next(value);
						return;
					}

					if (value == numberToError) {
						final String message = MessageFormat.format("No value ({0}) for you!!!", value);
						sink.error(new IllegalArgumentException(message));
						return;
					}

					sink.complete();
				})
				.subscriberContext(Context.of("key1", "value1"))
				;
	}
}
