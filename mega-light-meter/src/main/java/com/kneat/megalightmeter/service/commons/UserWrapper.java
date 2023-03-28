package com.kneat.megalightmeter.service.commons;

import lombok.Value;

@Value(staticConstructor = "of")
public class UserWrapper implements CacheKey {
	private String name;
	private String email;
}
