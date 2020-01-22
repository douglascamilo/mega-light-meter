package com.kneat.megalightmeter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Application startup point
 */
@SpringBootApplication
public class MegaLightMeterApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(MegaLightMeterApplication.class);

	public static void main(final String[] args) {
		SpringApplication.run(MegaLightMeterApplication.class, args);
	}
 	
 	@Bean
 	public RestTemplate restTemplate(final RestTemplateBuilder builder) {
 		return builder.build();
 	}
 	
 	@Bean
 	public CommandLineRunner run(final RestTemplate restTemplate) {
 		return args -> {
			LOGGER.info("Application running...");
		};
 	}
}