package com.kneat.megalightmeter.zreactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.FluxSink;

@Slf4j
public class HotStreamTest1 {

	@Test
	public void hot() {
		final List<Integer> first = new ArrayList<>();
		final List<Integer> second = new ArrayList<>();
		final List<Integer> third = new ArrayList<>();
		final List<Integer> fourth = new ArrayList<>();

		final EmitterProcessor<Integer> emitter = EmitterProcessor.create(1);
		final FluxSink<Integer> sink = emitter.sink();

		emitter.subscribe(first::add);
		sink.next(1);
		sink.next(2);

		emitter.subscribe(second::add);
		sink.next(3);
		sink.next(4);
		sink.next(5);

		emitter.subscribe(third::add);
		sink.next(6);
		sink.next(7);
		sink.next(8);
		sink.next(9);

		emitter.subscribe(fourth::add);
		sink.next(10);
		sink.complete();

		log.info("FIRST SUBSCRIBER - {}", first.toString());
		log.info("SECOND SUBSCRIBER - {}", second.toString());
		log.info("THIRD SUBSCRIBER - {}", third.toString());
		log.info("FOURTH SUBSCRIBER - {}", fourth.toString());

		assertThat(first.size()).isGreaterThan(second.size());
		assertThat(second.size()).isGreaterThan(third.size());
		assertThat(third.size()).isGreaterThan(fourth.size());
	}
}
