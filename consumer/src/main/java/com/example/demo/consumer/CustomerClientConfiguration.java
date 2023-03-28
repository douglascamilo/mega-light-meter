package com.example.demo.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CustomerClientConfiguration {

	@Bean
	public WebClient webClient(final WebClient.Builder builder) {
		return builder.build();
	}
}
