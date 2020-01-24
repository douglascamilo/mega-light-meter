package com.kneat.megalightmeter.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.kneat.megalightmeter.model.StarShipResource;

/**
 * This class encloses the complexity of handling with http requests.
 */
@Component
public class SwapiApiHelper {
	@Autowired private RestTemplate restTemplate;

	public <T> T retrieveStarShipsFrom(final String url, final Class<T> responseType) {
		final HttpEntity<StarShipResource> httpHeaders = this.createRequestHeader();

		return restTemplate
				.exchange(url, HttpMethod.GET, httpHeaders, responseType)
				.getBody();
	}

	private HttpEntity<StarShipResource> createRequestHeader() {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent", "douglas-kneat-test");

		return new HttpEntity<>(headers);
	}
}