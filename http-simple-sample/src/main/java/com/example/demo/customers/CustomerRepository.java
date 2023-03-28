package com.example.demo.customers;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import lombok.SneakyThrows;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CustomerRepository {
	private final Map<String, Customer> data = new ConcurrentHashMap<>();

	Mono<Customer> findById(final String id) {
		return Mono.just(data.get(id));
	}

	Mono<Customer> save(final Customer customer) {
		final String uuid = UUID.randomUUID().toString();
		data.put(uuid, new Customer(uuid, customer.getName()));
		return this.findById(uuid);
	}

	@SneakyThrows
	Flux<Customer> findAll() {
		return Flux.fromIterable(data.values());
	}
}
