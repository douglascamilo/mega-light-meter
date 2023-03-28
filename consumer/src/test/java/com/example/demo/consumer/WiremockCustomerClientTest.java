package com.example.demo.consumer;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import java.text.MessageFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.ConsumerApplication;
import com.github.tomakehurst.wiremock.client.WireMock;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@Import(ConsumerApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public class WiremockCustomerClientTest {
	@Autowired private CustomerClient client;
	@Autowired private Environment environment;

	@BeforeEach
	public void setupWiremock() {
		final String wiremockPort = environment.getProperty("wiremock.server.port");
		final String base = MessageFormat.format("localhost:{0}", wiremockPort);

		client.setBase(base);

		final String json = "[{\"id\":1, \"name\": \"Jane\"}, {\"id\": 2, \"name\": \"John\"}]";

		WireMock.stubFor(
				WireMock.get("/customers")
						.willReturn(WireMock.aResponse()
										.withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
										.withBody(json)));
	}

	@Test
	public void getAllCustomers() {
		final Flux<Customer> customers = client.getAllCustomers();

		StepVerifier.create(customers)
				.expectNext(new Customer(1, "Jane"))
				.expectNext(new Customer(2, "John"))
				.verifyComplete();
	}
}
