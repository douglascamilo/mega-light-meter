package com.kneat.megalightmeter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kneat.megalightmeter.facade.CalculateStopsFacade;
import com.kneat.megalightmeter.model.CalculateStopsResponse;

/**
 * REST API that returns a list of starships and their respectives needed stops for resupply by the given distance.
 */
@RestController
public class CalculateStopsController {
	@Autowired private CalculateStopsFacade facade;

	@GetMapping("/calculate-stops/{distance}")
	public ResponseEntity<List<CalculateStopsResponse>> calculateStops(
			@PathVariable("distance") final Long distanceInMGLT) {

		final List<CalculateStopsResponse> response = facade.getAllNeededStopsByStarShip(distanceInMGLT);
		return ResponseEntity.ok(response);
	}
}