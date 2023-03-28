package com.kneat.megalightmeter.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.kneat.megalightmeter.annotations.Testezinho;

@Aspect
@Component
public class TestezinhoAspect {

	@Around("@annotation(testez)")
	public Object around(
			final ProceedingJoinPoint joinPoint,
			final Testezinho testez) throws Throwable {

		System.out.println("logou inicio");
		System.out.println(joinPoint.getArgs());

		final Object resultado = joinPoint.proceed();
		System.out.println("logou fim");

		return resultado;
	}
}
