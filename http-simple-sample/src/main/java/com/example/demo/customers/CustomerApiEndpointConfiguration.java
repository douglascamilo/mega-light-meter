package com.example.demo.customers;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CustomerApiEndpointConfiguration {

	@Bean
	RouterFunction<ServerResponse> customerApis(final CustomerHandler handler) {
		return route()
				.nest(path("/fn/customers"), builder -> builder
						.GET("/{id}", handler::handleFindCustomerById)
						.GET("", handler::handleFindAll)
						.POST("", handler::handleCreateCustomer)
				)
				.build();
	}
}
