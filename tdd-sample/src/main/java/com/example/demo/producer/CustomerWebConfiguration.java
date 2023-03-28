package com.example.demo.producer;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CustomerWebConfiguration {

	@Bean
	public RouterFunction<ServerResponse> routes(final CustomerRepository repository) {
		return route(
				GET("/customers"),
				request -> ok().body(repository.findAll(), Customer.class));
	}
}
