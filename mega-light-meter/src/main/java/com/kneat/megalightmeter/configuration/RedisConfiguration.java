package com.kneat.megalightmeter.configuration;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.kneat.megalightmeter.constants.ProfileConstants;
import com.kneat.megalightmeter.model.StarShip;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableCaching
@EnableConfigurationProperties({ CacheProperties.class })
@Profile(ProfileConstants.REDIS)
@Slf4j
public class RedisConfiguration {

	@Bean
	public RedisTemplate<String, StarShip> redisTemplate(final RedisConnectionFactory factory) {
		final RedisTemplate<String, StarShip> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
		template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

		return template;
	}

	@Bean
	public CacheManager cacheManager(final RedisConnectionFactory connectionFactory,
			final CacheProperties cacheProperties) {

		final Redis redisProperties = cacheProperties.getRedis();
		log.info("Redis: [ttl: {}s, cache-null-values: {}]",
				redisProperties.getTimeToLive().getSeconds(),
				redisProperties.isCacheNullValues());

		final RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(redisProperties.getTimeToLive())
				.serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

		return RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(cacheConfiguration)
				.build();
	}
}