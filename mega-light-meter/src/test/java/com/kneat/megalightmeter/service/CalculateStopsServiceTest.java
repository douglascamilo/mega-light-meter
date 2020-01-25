package com.kneat.megalightmeter.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kneat.megalightmeter.model.CalculateStopsResponse;
import com.kneat.megalightmeter.model.StarShip;

/**
 * Unit tests for {@linkplain CalculateStopsService} class.
 */
@SpringBootTest
public class CalculateStopsServiceTest {
	@Autowired CalculateStopsService service;

	@Test
	public void calculateStopsForConsumablesInHours() {
		final long totalDistance = 900000L;
		final StarShip starShip = new StarShip("10", "26280 hours");

		final CalculateStopsResponse response = this.performCalculation(totalDistance, starShip).iterator().next();

		assertTrue(response.getStops().intValue() == 3);
	}

	@Test
	public void calculateStopsForConsumablesInDays() {
		final long totalDistance = 900000L;
		final StarShip starShip = new StarShip("10", "1095 days");

		final CalculateStopsResponse response = this.performCalculation(totalDistance, starShip).iterator().next();

		assertTrue(response.getStops().intValue() == 3);
	}

	@Test
	public void calculateStopsForConsumablesInWeeks() {
		final long totalDistance = 900000L;
		final StarShip starShip = new StarShip("10", "157 weeks");

		final CalculateStopsResponse response = this.performCalculation(totalDistance, starShip).iterator().next();

		assertTrue(response.getStops().intValue() == 3);
	}

	@Test
	public void calculateStopsForConsumablesInMonths() {
		final long totalDistance = 900000L;
		final StarShip starShip = new StarShip("10", "40 months");

		final CalculateStopsResponse response = this.performCalculation(totalDistance, starShip).iterator().next();

		assertTrue(response.getStops().intValue() == 3);
	}

	@Test
	public void calculateStopsForConsumablesInYears() {
		final long totalDistance = 90000000000L;
		final StarShip starShip = new StarShip("40", "6 years");

		final CalculateStopsResponse response = this.performCalculation(totalDistance, starShip).iterator().next();

		System.out.println(response);
		assertTrue(response.getStops().intValue() == 42808);
	}

	private List<CalculateStopsResponse> performCalculation(
			final long totalDistanceInMGLT, final StarShip starShip) {

		final List<StarShip> retrievedStarShips = Arrays.asList(starShip);

		return service.calculate(totalDistanceInMGLT, retrievedStarShips);
	}
}