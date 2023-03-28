package com.kneat.megalightmeter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kneat.megalightmeter.controller.vo.TestValid;
import com.kneat.megalightmeter.exception.SwapiApiIntegrationException;
import com.kneat.megalightmeter.model.CalculateStopsResponse;
import com.kneat.megalightmeter.model.TextResponses;

import reactor.core.publisher.Mono;

public interface ICalculateStopsController {

	@GetMapping("/calculate-stops/{distance}")
	public ResponseEntity<List<CalculateStopsResponse>> calculateStops(
			@PathVariable("distance") final Long distanceInMGLT) throws SwapiApiIntegrationException;

	@GetMapping("/test")
	public ResponseEntity<TextResponses> test();

	@GetMapping("/test1")
	public Mono<ResponseEntity<TextResponses>> test1();

	@PostMapping("/testValid")
	public ResponseEntity<Void> testValid(@Valid @RequestBody final TestValid testValid);
}
