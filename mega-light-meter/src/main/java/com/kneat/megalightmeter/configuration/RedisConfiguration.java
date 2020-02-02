package com.kneat.megalightmeter.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.kneat.megalightmeter.model.StarShip;

@Configuration
@EnableCaching
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
}