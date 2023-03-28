package com.kneat.megalightmeter.zreactor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.test.StepVerifier;

@Slf4j
public class HooksOnOperatorDebugTest {

	@Test
	public void onOperatorDebug() {
		Hooks.onOperatorDebug();

		final AtomicReference<String> stackTrace = new AtomicReference<>();
		final Flux<Object> errorFlux = Flux.error(new IllegalArgumentException("Oops!"))
				.checkpoint()
				.delayElements(Duration.ofMillis(1));

		StepVerifier.create(errorFlux)
				.expectErrorMatches(ex -> {
					stackTrace.set(this.stackTraceToString(ex));
					return ex instanceof IllegalArgumentException;
				})
				.verify();

		log.info(stackTrace.get());
	}

	private String stackTraceToString(final Throwable throwable) {
		try {
			final StringWriter sw = new StringWriter();
			final PrintWriter pw = new PrintWriter(sw);

			throwable.printStackTrace(pw);
			return sw.toString();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}
