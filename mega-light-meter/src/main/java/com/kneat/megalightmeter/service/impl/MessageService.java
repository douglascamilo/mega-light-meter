package com.kneat.megalightmeter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.kneat.megalightmeter.exception.KneatException;

/**
 * Encloses and handle with {@linkplain MessageSource} with locale supporting
 */
@Component
public class MessageService {
	@Autowired private MessageSource messageSource;

	public String getMessage(final KneatException exception) {
		return messageSource.getMessage(
				exception.getMessage(),
				exception.getMessageParams(),
				LocaleContextHolder.getLocale());
	}
}