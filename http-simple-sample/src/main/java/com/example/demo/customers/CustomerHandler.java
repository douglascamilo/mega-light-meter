package com.example.demo.customers;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CustomerHandler {
	private final CustomerRepository repository;

	public Mono<ServerResponse> handleFindCustomerById(final ServerRequest request) {
		final Mono<Customer> customer = repository.findById(request.pathVariable("id"));
		return ServerResponse.ok().body(customer, Customer.class);
	}

	public Mono<ServerResponse> handleFindAll(final ServerRequest request) {
		final Flux<Customer> customers = repository.findAll();
		return ServerResponse.ok().body(customers, Customer.class);
	}

	public Mono<ServerResponse> handleCreateCustomer(final ServerRequest request) {
		return request.bodyToMono(Customer.class)
				.flatMap(repository::save)
				.flatMap(saved -> ServerResponse
						.created(URI.create("/fn/customers/" + saved.getId()))
						.build());
	}
}
