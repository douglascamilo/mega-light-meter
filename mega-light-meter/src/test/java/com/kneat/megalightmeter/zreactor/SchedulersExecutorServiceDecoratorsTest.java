package com.kneat.megalightmeter.zreactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactoryBean;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

@Slf4j
public class SchedulersExecutorServiceDecoratorsTest {
	private final AtomicInteger methodInvocationsCount = new AtomicInteger();
	private final String rsb = "rsb";

	@BeforeEach
	public void beforeEach() {
		Schedulers.resetFactory();
		Schedulers.addExecutorServiceDecorator(rsb,
				(scheduler, scheduledExecutorService) -> this.decorate(scheduledExecutorService));
	}

	@AfterEach
	public void afterEach() {
		Schedulers.resetFactory();
		Schedulers.removeExecutorServiceDecorator(rsb);
	}

	@Test
	public void changeDefaultDecorator() {
		System.out.println();
		final Flux<Integer> integerFlux = Flux.just(1).delayElements(Duration.ofMillis(1));

		StepVerifier.create(integerFlux)
				.thenAwait(Duration.ofMillis(5000))
				.expectNextCount(1)
				.verifyComplete();

		assertThat(methodInvocationsCount).isEqualTo(1);
	}

	private ScheduledExecutorService decorate(final ScheduledExecutorService executorService) {
		try {
			final ProxyFactoryBean pfb = new ProxyFactoryBean();
			pfb.setProxyInterfaces(new Class[] { ScheduledExecutorService.class });
			pfb.addAdvice((MethodInterceptor) methodInvocation -> {
				final String methodName = methodInvocation.getMethod().getName().toLowerCase();
				methodInvocationsCount.incrementAndGet();

				log.info("MethodName: ({}) incrementing...", methodName);
				return methodInvocation.proceed();
			});

			pfb.setSingleton(true);
			pfb.setTarget(executorService);

			return (ScheduledExecutorService) pfb.getObject();
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}
}
