package com.kneat.megalightmeter.helper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.kneat.megalightmeter.model.StarShipResource;

/**
 * This class encloses the complexity of handling http requests.
 */
@Component
public class SwapiApiHelper {
//	@Autowired private RestTemplate restTemplate;

	/**
	 * Retrieves all Starships from the given {@code url}.
	 *
	 * @param url - Target url.
	 * @return {@linkplain StarShipResource} containing Starships data and resource url for the next page.
	 */
	public StarShipResource retrieveStarShipsFrom(final String url) {
		final HttpEntity<StarShipResource> httpHeaders = this.createRequestHeader();
		return null;

//		return restTemplate
//				.exchange(url, HttpMethod.GET, httpHeaders, StarShipResource.class)
//				.getBody();
	}

	private HttpEntity<StarShipResource> createRequestHeader() {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent", "douglas-kneat-test");

		return new HttpEntity<>(headers);
	}
}