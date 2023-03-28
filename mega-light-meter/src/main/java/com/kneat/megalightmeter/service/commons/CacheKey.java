package com.kneat.megalightmeter.service.commons;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

public interface CacheKey {

	default String getKey() {
		final List<Object> values = Arrays.stream(this.getClass().getDeclaredFields())
				.map(field -> {
					try {
						field.setAccessible(true);
						return field.get(this);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						return null;
					}
				})
				.filter(Objects::nonNull)
				.collect(Collectors.toList());

		return StringUtils.collectionToDelimitedString(values, "_");
	}
}
