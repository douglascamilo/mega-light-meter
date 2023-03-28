package com.kneat.megalightmeter.zreactor;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.ReplayProcessor;
import reactor.test.StepVerifier;

public class ReplayProcessorTest {

	@Test
	public void replayProcessor() {
		final int historySize = 2;
		final boolean unbounded = false;
		final ReplayProcessor<String> processor = ReplayProcessor.create(historySize, unbounded);

		this.produce(processor.sink());
		this.consume(processor);
	}

	private void produce(final FluxSink<String> sink) {
		sink.next("1");
		sink.next("2");
		sink.next("3");
		sink.complete();
	}

	private void consume(final Flux<String> publisher) {
		for (int i = 0; i < 5; i++) {
			StepVerifier.create(publisher)
					.expectNext("2")
					.expectNext("3")
					.verifyComplete();
		}
	}
}
