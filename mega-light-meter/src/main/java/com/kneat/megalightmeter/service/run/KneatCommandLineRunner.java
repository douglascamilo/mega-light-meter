package com.kneat.megalightmeter.service.run;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kneat.megalightmeter.facade.CalculateStopsFacade;
import com.kneat.megalightmeter.model.CalculateStopsResponse;
import com.kneat.megalightmeter.service.environment.EnvironmentService;

/**
 * A {@linkplain CommandLineRunner} means that Spring Boot will invoke it when the application
 * is starting. This class was implemented just to encloses the logic that decides which mode the application
 * is running(Standalone or HTTP).
 */
@Configuration
public class KneatCommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(KneatCommandLineRunner.class);

	@Bean
	public CommandLineRunner run(
			final CalculateStopsFacade calculateStopsFacade,
			final EnvironmentService environmentService)  {

		return args -> {
			if (args != null && args.length == 1) {
				LOGGER.info("Application running on standalone mode...");

				final Long givenDistance = Long.parseLong(args[0]);
				final List<CalculateStopsResponse> response =
						calculateStopsFacade.getAllNeededStopsByStarShip(givenDistance);
				LOGGER.info(response.toString());

				System.exit(0);
			}

			LOGGER.info("Application running on http mode...");
			LOGGER.info("Endpoint: http://localhost:" + environmentService.getServerPort() + "/calculate-stops/");
		};
	}
}