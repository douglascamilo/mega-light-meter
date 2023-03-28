package com.kneat.megalightmeter.zreactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class HotStreamTest3 {
	private final List<Integer> one = new ArrayList<>();
	private final List<Integer> two = new ArrayList<>();
	private final List<Integer> three = new ArrayList<>();

	@Test
	public void publish() {
		final Flux<Integer> pileOn = Flux.just(1, 2, 3)
				.publish()
				.autoConnect(3)
				.subscribeOn(Schedulers.immediate())
				.log("PUBLISHER")
				;

		pileOn.subscribe(one::add);
		assertThat(one).isEmpty();

		pileOn.subscribe(two::add);
		assertThat(two).isEmpty();

		pileOn.subscribe(three::add);
		assertThat(one.size()).isEqualTo(3);
		assertThat(two.size()).isEqualTo(3);
		assertThat(three.size()).isEqualTo(3);

		log.info("Subscriber one: {}", one.toString());
		log.info("Subscriber two: {}", two.toString());
		log.info("Subscriber three: {}", three.toString());
	}
}
