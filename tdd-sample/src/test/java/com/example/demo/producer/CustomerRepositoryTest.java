package com.example.demo.producer;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class CustomerRepositoryTest {
	@Autowired CustomerRepository repository;

	@Test
	public void findByName() {
		final String commonName = "Jane";

		final Customer one = new Customer("1", commonName);
		final Customer two = new Customer("2", "John");
		final Customer three = new Customer("3", commonName);

		final Flux<Customer> setup = repository.deleteAll()
				.thenMany(repository.saveAll(Flux.just(one, two, three)))
				.thenMany(repository.findByName(commonName));

		final Predicate<Customer> customerPredicate =
				customer -> commonName.equalsIgnoreCase(customer.getName());

		StepVerifier.create(setup)
				.expectNextMatches(customerPredicate)
				.expectNextMatches(customerPredicate)
				.verifyComplete();
	}
}
