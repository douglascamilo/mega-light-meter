package com.kneat.megalightmeter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateStopsController {
	private final Logger LOGGER = LoggerFactory.getLogger(CalculateStopsController.class);

	@GetMapping("/calculate-stops/{distance}")
	public String calculateStops(@PathVariable("distance") final Long distanceInMGLT) {
		final String message = "Distance in MGLT: " + distanceInMGLT;
		LOGGER.info(message);
		
		return message;
	}
}