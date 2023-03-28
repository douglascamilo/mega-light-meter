package com.example.demo.filters;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class LowercaseWebConfiguration {

	@Bean
	RouterFunction<ServerResponse> routerFunctionFilters() {
		final String uuidKey = UUID.class.getName();

		return route()
				.GET("/hi/{name}", this::handle)
				.GET("/hello/{name}", this::handle)
				.filter((request, next) -> {
					log.info(".filter(): before");
					final Mono<ServerResponse> reply = next.handle(request);
					log.info(".filter(): after");

					return reply;
				})
				.before(request -> {
					log.info(".before()");
					request.attributes().put(uuidKey, UUID.randomUUID());
					return request;
				})
				.after((serverRequest, serverResponse) -> {
					log.info(".after()");
					log.info("UUID: {}", serverRequest.attributes().get(uuidKey));
					System.out.println();
					return serverResponse;
				})
				.onError(NullPointerException.class, (e, request) -> badRequest().build())
				.build();
	}

	private Mono<ServerResponse> handle(final ServerRequest serverRequest) {
		return ServerResponse.ok()
				.bodyValue(String.format("Hello, %s!", serverRequest.pathVariable("name")));
	}
}
