package com.kneat.megalightmeter.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.kneat.megalightmeter.annotations.Testezinho;
import com.kneat.megalightmeter.exception.TestException;
import com.kneat.megalightmeter.model.TextResponse;
import com.kneat.megalightmeter.model.TextResponses;

import reactor.core.publisher.Mono;

@Service
public class TestFacade {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestFacade.class);

	@Autowired private WebClient webClient;

	@Testezinho
	public TextResponses performSomeAction(final String someText) {
		this.addValor(someText);
		LOGGER.info("Initial context: {}", this.getValor());

		final Mono<TextResponse> monoTextOne = this.getText("text");
		final Mono<TextResponse> monoTextTwo = this.getText("text2");
//		final Mono<TextResponse> monoTextOne = this.getTextBlock("text");
//		final Mono<TextResponse> monoTextTwo = this.getTextBlock("text2");

		final TextResponses textResponses = new TextResponses();

		Mono.zip(monoTextOne, monoTextTwo)
				.map(results -> {
					textResponses.addResponse(results.getT1());
					textResponses.addResponse(results.getT2());

					return textResponses;
				})
				.block();

//		Flux.merge(Arrays.asList(monoTextOne, monoTextTwo))
//				.doOnNext(textResponses::addResponse)
//				.blockLast();

		LOGGER.info("Final context: {}", this.getValor());
		return textResponses;
	}

	private Mono<TextResponse> getTextBlock(final String path) {
		return Mono.fromSupplier(()-> {
			return webClient.get()
					.uri(path)
					.retrieve()
					.bodyToMono(TextResponse.class)
					.doOnNext(response -> {
						LOGGER.info("Context: ", this.getValor());
						LOGGER.info("{}", response.getText());
					})
					.block();
		});
	}

	public Mono<TextResponse> getText(final String path) {
		return webClient.get()
				.uri(path)
				.retrieve()
				.bodyToMono(TextResponse.class)
				.onErrorMap(WebClientResponseException.class,
						error -> new TestException(error.getStatusCode(), error.getMessage()))
				;
	}

	private void addValor(final String valor) {
		MDC.put("teste", valor);
	}

	private String getValor() {
		return MDC.get("teste");
	}
}
