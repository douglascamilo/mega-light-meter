package com.example.demo.routes;

import static com.example.demo.routes.CaseInsensitiveRequestPredicate.i;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CustomRoutesPredicates {

	private final HandlerFunction<ServerResponse> handler = request ->
			ServerResponse.ok().bodyValue("Hello, " + request.queryParam("name").orElse("world") + "!");

	@Bean
	RouterFunction<ServerResponse> customRequestPredicates() {
		final RequestPredicate aPeculiarRequestPredicate = GET("/test")
				.and(accept(MediaType.APPLICATION_JSON))
				.and(this::isRequestForAValidUid);

		final RequestPredicate caseInsensitiveRequestPredicate = i(GET("/greetings"));

		return route()
				.add(route(aPeculiarRequestPredicate, handler))
				.add(route(caseInsensitiveRequestPredicate, handler))
				.build();
	}

	private boolean isRequestForAValidUid(final ServerRequest request) {
		final List<String> goodUids = Arrays.asList("1", "2", "3");
		return request.queryParam("uid")
				.map(goodUids::contains)
				.orElse(false);
	}
}
