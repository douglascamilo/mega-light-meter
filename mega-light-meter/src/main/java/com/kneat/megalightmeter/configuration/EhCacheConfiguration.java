package com.kneat.megalightmeter.configuration;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.kneat.megalightmeter.constants.ProfileConstants;
import com.kneat.megalightmeter.enums.ServiceCacheEnum;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
@Profile(ProfileConstants.EH_CACHE)
@Slf4j
public class EhCacheConfiguration {

	@Bean
	public CacheManager cacheManager() {
		final net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();

		Arrays.stream(ServiceCacheEnum.values())
				.map(ServiceCacheEnum::getName)
				.forEach(cacheName -> {
					log.info("Setting up cache '{}'", cacheName);
					configuration.addCache(new CacheConfiguration(cacheName, 100));
				});

		return new EhCacheCacheManager(net.sf.ehcache.CacheManager.create(configuration));
	}
}
