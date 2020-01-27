package com.kneat.megalightmeter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Application startup point
 */
@SpringBootApplication
@EnableCaching
public class MegaLightMeterApplication {

	public static void main(final String[] args) {
		SpringApplication.run(MegaLightMeterApplication.class, args);
	}

 	@Bean
 	public RestTemplate restTemplate(final RestTemplateBuilder builder) {
 		return builder.build();
 	}
}