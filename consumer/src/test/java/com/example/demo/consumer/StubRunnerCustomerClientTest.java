package com.example.demo.consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.ConsumerApplication;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ConsumerApplication.class)
@DirtiesContext
@AutoConfigureStubRunner(
		ids = "com.example:tdd-sample:+",
		stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class StubRunnerCustomerClientTest {
	@StubRunnerPort("com.example:tdd-sample") private int portOfProducerService;
	@Autowired private CustomerClient client;

	@Test
	public void getAllCustomers() {
		if (portOfProducerService != 0) {
			final String base = "localhost:" + portOfProducerService;
			client.setBase(base);
		}

		final Flux<Customer> customers = client.getAllCustomers()
				.log();

		StepVerifier.create(customers)
				.expectNext(new Customer(1, "Jane"))
				.expectNext(new Customer(2, "John"))
				.verifyComplete();
	}
}
