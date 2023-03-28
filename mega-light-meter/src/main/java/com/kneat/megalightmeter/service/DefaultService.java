package com.kneat.megalightmeter.service;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.kneat.megalightmeter.enums.ServiceCacheEnum;
import com.kneat.megalightmeter.service.commons.CacheKey;

import reactor.cache.CacheFlux;
import reactor.cache.CacheMono;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

public abstract class DefaultService {
	@Autowired protected CacheManagerService cacheManagerService;

	protected <T> Mono<T> monoWithCache(final CacheKey key, final Supplier<Mono<T>> operationSupplier) {
		return CacheMono.lookup(this.<T>cacheReader(), key)
				.onCacheMissResume(operationSupplier)
				.andWriteWith(this::cacheWriter);
	}

	protected <T> Flux<T> fluxWithCache(final CacheKey key, final Supplier<Flux<T>> operationSupplier) {
		return CacheFlux.lookup(this.<T>cacheListReader(), key)
				.onCacheMissResume(operationSupplier)
				.andWriteWith(this::cacheListWriter);
	}

	protected <T> Function<CacheKey, Mono<Signal<? extends T>>> cacheReader() {
		return key -> Mono
				.fromSupplier(() -> cacheManagerService.<T>get(this.getServiceCache(), key.getKey()))
				.map(Signal::next);
	}

	protected <T> Function<CacheKey, Mono<List<Signal<T>>>> cacheListReader() {
		return key -> {
			final List<T> cachedData = cacheManagerService.getList(this.getServiceCache(), key.getKey());

			return Flux.fromIterable(cachedData)
					.materialize()
					.collectList()
					.handle((values, sink) -> {
						if (values.isEmpty() || values.size() == 1) {
							final Signal<T> signal = values.get(0);

							if (signal.isOnComplete()) {
								sink.complete();
								return;
							}
						}

						sink.next(values);
					});
		};
	}

	protected <T> Mono<Void> cacheWriter(final CacheKey key, final Signal<? extends T> signal) {
		return Mono.fromRunnable(() -> {
			if (signal.isOnNext()) {
				cacheManagerService.put(this.getServiceCache(), key.getKey(), signal.get());
			}
		});
	}

	protected <T> Mono<Void> cacheListWriter(final CacheKey key, final List<Signal<T>> signal) {
		return Mono.fromRunnable(() -> {
			if (signal != null) {
				final List<T> allValues = signal.stream()
						.filter(Signal::isOnNext)
						.map(Signal::get)
						.filter(Objects::nonNull)
						.collect(Collectors.toList());

				cacheManagerService.put(this.getServiceCache(), key.getKey(), allValues);
			}
		});
	}

	protected abstract ServiceCacheEnum getServiceCache();
}
