package com.kneat.megalightmeter.service.commons;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserResponse {
	private String name;
	private String email;
}
