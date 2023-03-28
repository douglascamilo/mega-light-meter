package com.kneat.megalightmeter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Application startup point
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class MegaLightMeterApplication {

	public static void main(final String[] args) {
		SpringApplication.run(MegaLightMeterApplication.class, args);
	}

 	@Bean
 	public RestTemplate restTemplate(final RestTemplateBuilder builder) {
 		return builder.build();
 	}

 	@Bean
 	public WebClient webClient() {
 		return WebClient.builder()
 				.baseUrl("http://localhost:3001/test/")
 				.build();
 	}
}