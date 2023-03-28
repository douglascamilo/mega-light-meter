package com.example.demo.customers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rc/customers")
@AllArgsConstructor
public class CustomerRestController {
	private final CustomerRepository repository;

	@GetMapping("/{id}")
	public Mono<Customer> byId(@PathVariable("id") final String id) {
		return repository.findById(id);
	}

	@GetMapping
	public Flux<Customer> all() {
		return repository.findAll();
	}

	@PostMapping
	public Mono<ResponseEntity<Customer>> create(@RequestBody final Customer customer) {
		return repository.save(customer)
				.map(entity -> ResponseEntity
						.created(URI.create("/rc/customers/" + entity.getId()))
						.body(entity));
	}
}
