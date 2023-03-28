package com.kneat.megalightmeter.service.impl;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.kneat.megalightmeter.enums.ServiceCacheEnum;
import com.kneat.megalightmeter.service.DefaultService;
import com.kneat.megalightmeter.service.commons.GreetingWrapper;
import com.kneat.megalightmeter.service.commons.NameWrapper;

import reactor.core.publisher.Mono;

@Service
public class GreetingService extends DefaultService {

	public Mono<GreetingWrapper> getGreeting(final NameWrapper nameWrapper) {
		return this.monoWithCache(nameWrapper, this.createGreeting(nameWrapper));
	}

	private Supplier<Mono<GreetingWrapper>> createGreeting(final NameWrapper nameWrapper) {
		return () -> {
					final String name = nameWrapper.getName();

					if (name.contains("Douglas")) {
//						return Mono.error(RuntimeException::new);
					}

					final GreetingWrapper greeting = new GreetingWrapper()
							.setText(MessageFormat.format("Hello, {0}!", name))
							.setToday(LocalDate.now());

					return Mono.just(greeting)
//							.delayElement(Duration.ofSeconds(1))
							;
				};
	}

	@Override
	protected ServiceCacheEnum getServiceCache() {
		return ServiceCacheEnum.GREETINGS;
	}
}
