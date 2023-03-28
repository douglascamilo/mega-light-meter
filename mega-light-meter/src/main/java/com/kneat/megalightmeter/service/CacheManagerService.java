package com.kneat.megalightmeter.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.kneat.megalightmeter.enums.ServiceCacheEnum;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CacheManagerService {
	@Autowired protected CacheManager cacheManager;

	@PostConstruct
	public void init() {
		log.info("CacheManager -> {}", cacheManager.getClass().getSimpleName());
		log.info("CacheNames -> {}", cacheManager.getCacheNames());
	}

	@SuppressWarnings("unchecked")
	public <T> T get(final ServiceCacheEnum serviceCache, final String key) {
		try {
			final ValueWrapper valueWrapper = cacheManager.getCache(serviceCache.getName()).get(key);
			return (T) Optional.ofNullable(valueWrapper)
					.map(ValueWrapper::get)
					.orElse(null);
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getList(final ServiceCacheEnum serviceCache, final String key) {
		try {
			final ValueWrapper valueWrapper = cacheManager.getCache(serviceCache.getName()).get(key);

			return (List<T>) Optional.ofNullable(valueWrapper)
					.map(ValueWrapper::get)
					.orElseGet(Collections::emptyList);
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
		}

		return Collections.emptyList();
	}

	public <T> void put(final ServiceCacheEnum serviceCache, final String key, final T value) {
		try {
			cacheManager.getCache(serviceCache.getName()).put(key, value);
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public Mono<Void> clearCache() {
		return Mono.fromRunnable(() -> {
			cacheManager.getCacheNames()
					.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
		});
	}
}
