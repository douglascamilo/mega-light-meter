package com.example.demo.producer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CustomerTest {

	@Test
	public void create() {
		final Customer customer = new Customer("123", "foo");

		assertThat(customer.getId()).isEqualTo("123");
		assertThat(customer.getName()).isEqualToIgnoringCase("foo");
	}
}
