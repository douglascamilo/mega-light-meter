package com.kneat.megalightmeter.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kneat.megalightmeter.service.CacheManagerService;
import com.kneat.megalightmeter.service.commons.GreetingWrapper;
import com.kneat.megalightmeter.service.commons.NameWrapper;
import com.kneat.megalightmeter.service.commons.UserResponse;
import com.kneat.megalightmeter.service.commons.UserWrapper;
import com.kneat.megalightmeter.service.impl.GreetingService;
import com.kneat.megalightmeter.service.impl.UserService;

import reactor.core.publisher.Mono;

@RestController
public class CacheController {
	@Autowired private GreetingService greetingService;
	@Autowired private UserService userService;
	@Autowired private CacheManagerService cacheManagerService;

	@PostConstruct
	public void init() {
//		BlockHound.install();
	}

	@GetMapping("/greeting/{name}/{age}")
	public Mono<ResponseEntity<GreetingWrapper>> greeting(@PathVariable final String name,
			@PathVariable final Integer age) {

		return greetingService.getGreeting(NameWrapper.of(name, age))
				.map(ResponseEntity::ok);
	}

	@GetMapping("/user/{name}/{email}")
	public Mono<ResponseEntity<List<UserResponse>>> user(@PathVariable final String name,
			@PathVariable final String email) {

		return userService.getUser(UserWrapper.of(name, email))
				.map(ResponseEntity::ok);
	}

	@GetMapping("/clear-cache")
	public Mono<ResponseEntity<Void>> clearCache() {
		return cacheManagerService.clearCache()
				.map(ResponseEntity::ok);
	}
}
