package com.kneat.megalightmeter.service.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Service for getting values from {@code application.properties} file.
 */
@Component
public class EnvironmentService {
	@Autowired private Environment environment;

	/**
	 * Returns the value of the property <b>server.port</b>.
	 *
	 * @return String containing the respective value.
	 */
	public String getServerPort() {
		return environment.getProperty("server.port");
	}
}