package com.kneat.megalightmeter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kneat.megalightmeter.facade.CalculateStopsFacade;
import com.kneat.megalightmeter.model.CalculateStopsResponse;

@RestController
public class CalculateStopsController {
	private final Logger LOGGER = LoggerFactory.getLogger(CalculateStopsController.class);

	@Autowired private CalculateStopsFacade facade;

	@GetMapping("/calculate-stops/{distance}")
	public ResponseEntity<List<CalculateStopsResponse>> calculateStops(@PathVariable("distance") final Long distanceInMGLT) {
		final List<CalculateStopsResponse> response = facade.getAllNeededStopsByStarShip(distanceInMGLT);
		return ResponseEntity.ok(response);
	}
}