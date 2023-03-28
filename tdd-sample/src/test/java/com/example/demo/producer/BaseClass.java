package com.example.demo.producer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.server.RouterFunction;

import com.example.demo.TddSampleApplication;

import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server.port=0")
public class BaseClass {
	@LocalServerPort private int port;
	@MockBean private CustomerRepository repository;
	@Autowired private RouterFunction<?>[] routerFunctions;

	@BeforeAll
	public void before() {
		Mockito.when(repository.findAll())
				.thenReturn(Flux.just(new Customer("1", "Jane"), new Customer("2", "John")));

		RestAssuredWebTestClient.standaloneSetup((Object[]) routerFunctions);
	}

	@Configuration
	@Import(TddSampleApplication.class)
	public static class TestConfiguration {

	}
}
