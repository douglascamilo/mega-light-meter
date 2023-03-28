package com.kneat.megalightmeter.zreactor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class CheckpointTest {

	@Test
	public void checkpoint() {
		final AtomicReference<String> stackTrace = new AtomicReference<>();

		final Flux<Object> checkpoint = Flux.error(new IllegalArgumentException("Oops!"))
				.delayElements(Duration.ofMillis(1))
				.checkpoint()
				.log();

		StepVerifier.create(checkpoint)
				.expectErrorMatches(ex -> {
					stackTrace.set(this.stackTraceToString(ex));
					return ex instanceof IllegalArgumentException;
				})
				.verify();
	}

	private String stackTraceToString(final Throwable ex) {
		try {
			final StringWriter stringWriter = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(stringWriter);

			ex.printStackTrace(printWriter);
			return stringWriter.toString();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}
