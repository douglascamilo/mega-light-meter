package com.kneat.megalightmeter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.kneat.megalightmeter.controller.vo.TestValid;
import com.kneat.megalightmeter.exception.SwapiApiIntegrationException;
import com.kneat.megalightmeter.facade.CalculateStopsFacade;
import com.kneat.megalightmeter.facade.impl.TestFacade;
import com.kneat.megalightmeter.model.CalculateStopsResponse;
import com.kneat.megalightmeter.model.TextResponses;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * REST API that returns a list of starships and their respectives needed stops for resupply by the given distance.
 */
@RestController
public class CalculateStopsController implements ICalculateStopsController {
	@Autowired private CalculateStopsFacade facade;
	@Autowired private TestFacade testFacade;

	@Override
	public ResponseEntity<List<CalculateStopsResponse>> calculateStops(
			final Long distanceInMGLT) throws SwapiApiIntegrationException {

		final List<CalculateStopsResponse> response = facade.getAllNeededStopsByStarShip(distanceInMGLT);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<TextResponses> test() {
		final TextResponses response = testFacade.performSomeAction("my name is Douglas");

		return ResponseEntity.ok(response);
	}

	@Override
	public Mono<ResponseEntity<TextResponses>> test1() {
		final TextResponses textResponses = new TextResponses();

		return testFacade.getText("text")
				.map(textResponses::addResponse)
				.map(ResponseEntity::ok)
				.subscribeOn(Schedulers.boundedElastic());
	}

	@Override
	public ResponseEntity<Void> testValid(final TestValid testValid) {
		return ResponseEntity.ok(null);
	}
}