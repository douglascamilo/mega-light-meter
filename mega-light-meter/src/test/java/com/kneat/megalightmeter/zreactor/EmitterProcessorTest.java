package com.kneat.megalightmeter.zreactor;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.test.StepVerifier;

public class EmitterProcessorTest {

	@Test
	public void emitterProcessor() {
		final EmitterProcessor<String> processor = EmitterProcessor.create();
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
		StepVerifier.create(publisher)
				.expectNext("1")
				.expectNext("2")
				.expectNext("3")
				.verifyComplete();
	}
}
