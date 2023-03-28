package com.example.demo.routes;

import java.net.URI;

import org.springframework.http.server.PathContainer;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.support.ServerRequestWrapper;

public class LowerCaseUriServerRequestMapper extends ServerRequestWrapper {

	public LowerCaseUriServerRequestMapper(final ServerRequest delegate) {
		super(delegate);
	}

	@Override
	public URI uri() {
		return URI.create(super.uri().toString().toLowerCase());
	}

	@Override
	public String path() {
		return this.uri().getRawPath();
	}

	@Override
	public PathContainer pathContainer() {
		return PathContainer.parsePath(this.path());
	}
}
