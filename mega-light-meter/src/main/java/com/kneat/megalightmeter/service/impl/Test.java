package com.kneat.megalightmeter.service.impl;

import java.time.LocalDate;

import org.springframework.util.StringUtils;

public class Test {

	public static void main(final String[] args) {
//		final String result = StringUtils.arrayToDelimitedString(
//				new Object[] { new Wrapper("Douglas", 37) },
//				"_");
//
//		System.out.println(result);

		getFieldValues(new Wrapper("Douglas", 37));
		getFieldValues(new Wrapper("Douglas", 37));
		getFieldValues(new Wrapper("Douglas", 38));
		getFieldValues("oi");
		getFieldValues(new Integer(1));
	}

	private static void getFieldValues(final Object... param) {
		final String log = StringUtils.arrayToDelimitedString(new Object[] { param }, "_");
		System.out.println(param.getClass().getSimpleName() + ": " + log);

		System.out.println(param.getClass().getSimpleName() + ": " + param.getClass().getFields().length);
		System.out.println(param.getClass().getSimpleName() + ": " + param.getClass().getDeclaredFields().length);
		System.out.println("----");

//		System.out.println(param.getClass().getSimpleName() + ": ");
//		if (param.getClass().getFields().length > 0) {
//			Arrays.asList(param.getClass().getFields())
//					.forEach(field -> {
//						try {
//							System.out.println(field.get(param));
//						} catch (IllegalArgumentException | IllegalAccessException e) {
//							e.printStackTrace();
//						}
//					});
//		} else if (param.getClass().getDeclaredFields().length > 0) {
//			Arrays.asList(param.getClass().getDeclaredFields())
//					.forEach(field -> {
//						field.setAccessible(true);
//						try {
//							System.out.println(field.get(param));
//						} catch (IllegalArgumentException | IllegalAccessException e) {
//							e.printStackTrace();
//						}
//					});
//		}

		System.out.println("----");
	}

	static class Wrapper {
		private final String name;
		private final Integer age;
		private final LocalDate today = LocalDate.now();

		public Wrapper(final String name, final Integer age) {
			this.name = name;
			this.age = age;
		}

		public final String getName() {
			return name;
		}

		public final Integer getAge() {
			return age;
		}

		public final LocalDate getToday() {
			return today;
		}
	}
}
