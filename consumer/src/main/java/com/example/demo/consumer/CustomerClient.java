package com.example.demo.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@Component
public class CustomerClient {
	private String base = "localhost:8080";

	@Autowired private WebClient webClient;

	public Flux<Customer> getAllCustomers() {
		return webClient.get()
				.uri("http://" + base + "/customers")
				.retrieve()
				.bodyToFlux(Customer.class);
	}

	public void setBase(final String base) {
		this.base = base;
	}
}
