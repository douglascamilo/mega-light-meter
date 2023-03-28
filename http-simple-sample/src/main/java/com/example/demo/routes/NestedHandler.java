package com.example.demo.routes;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.utils.IntervalMessageProducer;

import reactor.core.publisher.Mono;

@Component
public class NestedHandler {

	Mono<ServerResponse> sse(final ServerRequest r) {
		return ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(IntervalMessageProducer.produce(), String.class);
	}

	Mono<ServerResponse> pathVariable(final ServerRequest r) {
		return ok()
				.bodyValue(this.greet(Optional.of(r.pathVariable("pv"))));
	}

	Mono<ServerResponse> noPathVariable(final ServerRequest r) {
		return ok()
				.bodyValue(this.greet(Optional.ofNullable(null)));
	}

	private Map<String, String> greet(final Optional<String> name) {
		final String finalName = name.orElse("world");
		return Collections.singletonMap("message", "Hello " + finalName);
	}
}
