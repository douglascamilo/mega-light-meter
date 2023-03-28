package com.kneat.megalightmeter.zreactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Signal;
import reactor.core.publisher.SignalType;
import reactor.test.StepVerifier;

@Slf4j
public class DoOnTest {

	@Test
	public void doOn() {
		final List<Signal<Integer>> signals = new ArrayList<>();
		final List<Integer> nextValues = new ArrayList<>();
		final List<Subscription> subscriptions = new ArrayList<>();
		final List<Throwable> exceptions = new ArrayList<>();
		final List<SignalType> finallySignals = new ArrayList<>();

		final Flux<Integer> on = Flux.<Integer> create(this.emitter())
				.doOnNext(nextValues::add)
				.doOnEach(signals::add)
				.doOnSubscribe(subscriptions::add)
				.doOnError(IllegalArgumentException.class, exceptions::add)
				.doFinally(finallySignals::add);

		StepVerifier.create(on)
				.expectNext(1, 2, 3)
				.expectError(IllegalArgumentException.class)
				.verify();

		System.out.println();
		log.info("### SIGNALS ###");
		signals.forEach(signal -> log.info(signal.toString()));
		assertThat(signals).hasSize(4);

		System.out.println();
		log.info("### FINALLY SIGNALS ###");
		finallySignals.forEach(signal -> log.info(signal.toString()));
		assertThat(finallySignals).hasSize(1);

		System.out.println();
		log.info("### SUBSCRIPTIONS ###");
		subscriptions.forEach(subscription -> log.info(subscription.toString()));
		assertThat(subscriptions).hasSize(1);

		System.out.println();
		log.info("### EXCEPTIONS ###");
		exceptions.forEach(exception -> log.info(exception.toString()));
		assertThat(exceptions).hasSize(1);
		assertThat(exceptions.get(0) instanceof IllegalArgumentException).isTrue();

		System.out.println();
		log.info("### NEXT VALUES ###");
		nextValues.forEach(value -> log.info(value.toString()));
		assertThat(nextValues).contains(1, 2, 3);
	}

	private Consumer<? super FluxSink<Integer>> emitter() {
		return sink -> {
					sink.next(1);
					sink.next(2);
					sink.next(3);
					sink.error(new IllegalArgumentException("Oops!"));
//					sink.complete();
				};
	}
}
