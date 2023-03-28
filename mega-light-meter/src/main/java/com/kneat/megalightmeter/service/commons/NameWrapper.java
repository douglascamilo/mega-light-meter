package com.kneat.megalightmeter.service.commons;

import lombok.Value;

@Value(staticConstructor = "of")
public class NameWrapper implements CacheKey {
	private String name;
	private Integer age;
}
