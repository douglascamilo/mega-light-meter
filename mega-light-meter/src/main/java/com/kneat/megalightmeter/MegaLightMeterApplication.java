package com.kneat.megalightmeter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.kneat.megalightmeter.facade.CalculateStopsFacade;
import com.kneat.megalightmeter.model.CalculateStopsResponse;

/**
 * Application startup point
 */
@SpringBootApplication
@EnableCaching
public class MegaLightMeterApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(MegaLightMeterApplication.class);

	public static void main(final String[] args) {
		SpringApplication.run(MegaLightMeterApplication.class, args);
	}

 	@Bean
 	public RestTemplate restTemplate(final RestTemplateBuilder builder) {
 		return builder.build();
 	}

 	@Bean
 	public CommandLineRunner run(final CalculateStopsFacade calculateStopsFacade) {
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
 		};
 	}
}