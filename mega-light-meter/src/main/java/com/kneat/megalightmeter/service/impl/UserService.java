package com.kneat.megalightmeter.service.impl;

import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.kneat.megalightmeter.enums.ServiceCacheEnum;
import com.kneat.megalightmeter.service.DefaultService;
import com.kneat.megalightmeter.service.commons.UserResponse;
import com.kneat.megalightmeter.service.commons.UserWrapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService extends DefaultService {

	public Mono<List<UserResponse>> getUser(final UserWrapper userWrapper) {
		return this.fluxWithCache(userWrapper, this.createUser(userWrapper))
				.collectList();
	}

	private Supplier<Flux<UserResponse>> createUser(final UserWrapper userWrapper) {
		return () -> {
			return Flux.range(1, 50)
					.map(value -> {
						return new UserResponse().setName(userWrapper.getName()).setEmail(String.valueOf(value));
					})
					.delayElements(Duration.ofMillis(100))
					;
		};
	}

	@Override
	protected ServiceCacheEnum getServiceCache() {
		return ServiceCacheEnum.USER;
	}
}
