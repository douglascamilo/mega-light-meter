package com.example.demo.routes;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class NestedFunctionEndpointConfiguration {

	@Bean
	RouterFunction<ServerResponse> nested(final NestedHandler nestedHandler) {
		final RequestPredicate jsonRP = accept(APPLICATION_JSON);
		final RequestPredicate sseRP = accept(TEXT_EVENT_STREAM);

		return route()
				.nest(path("/nested"), builder -> builder
						.nest(jsonRP, nestedBuilder -> nestedBuilder
							.GET("/{pv}", nestedHandler::pathVariable)
							.GET("", nestedHandler::noPathVariable))
						.add(route(sseRP, nestedHandler::sse))
				)
				.build();
	}
}
