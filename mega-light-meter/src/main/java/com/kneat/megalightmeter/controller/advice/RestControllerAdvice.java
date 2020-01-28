package com.kneat.megalightmeter.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.kneat.megalightmeter.exception.KneatException;
import com.kneat.megalightmeter.service.impl.MessageService;

@ControllerAdvice
public class RestControllerAdvice {
	@Autowired MessageService messageService;

	@ExceptionHandler({
		KneatException.class
	})
	public ResponseEntity<ExceptionResponse> handle(final KneatException exception, final WebRequest request) {
		final ExceptionResponse bodyResponse = this.buildBodyResponse(exception, request);

		return ResponseEntity
				.status(exception.getStatusCode())
				.body(bodyResponse);
	}

	private ExceptionResponse buildBodyResponse(final KneatException exception, final WebRequest request) {
		return new ExceptionResponse(
				messageService.getMessage(exception),
				request.getDescription(false));
	}
}