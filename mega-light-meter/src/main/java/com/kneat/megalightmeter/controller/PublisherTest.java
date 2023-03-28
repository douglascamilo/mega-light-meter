package com.kneat.megalightmeter.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class PublisherTest extends Mono<String> {
	private final Integer id;

	private PublisherTest(final Integer id) {
		this.id = id;
	}

	@Override
	public void subscribe(final CoreSubscriber<? super String> subscriber) {
		try {
			Thread.sleep(2000);

			final String resultado = String.valueOf(id) + " - " +
					LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

			subscriber.onNext(resultado);
			subscriber.onComplete();
		} catch (final Exception e) {}
	}

	public Mono<String> emParalelo() {
		return this.subscribeOn(Schedulers.elastic());
	}

	public static Mono<String> criarParalelo(final Integer id) {
		return new PublisherTest(id).emParalelo();
	}

	public static Mono<String> criarSequencial(final Integer id) {
		return new PublisherTest(id);
	}
}
