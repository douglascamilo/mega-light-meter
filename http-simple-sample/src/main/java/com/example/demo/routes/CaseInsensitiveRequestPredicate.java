package com.example.demo.routes;

import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.ServerRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class CaseInsensitiveRequestPredicate implements RequestPredicate {
	private final RequestPredicate target;

	public static RequestPredicate i(final RequestPredicate predicate) {
		return new CaseInsensitiveRequestPredicate(predicate);
	}

	@Override
	public boolean test(final ServerRequest request) {
		return target.test(new LowerCaseUriServerRequestMapper(request));
	}

	@Override
	public String toString() {
		return target.toString();
	}
}
