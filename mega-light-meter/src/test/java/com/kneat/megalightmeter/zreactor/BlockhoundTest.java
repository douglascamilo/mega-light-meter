package com.kneat.megalightmeter.zreactor;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import reactor.blockhound.BlockHound;
import reactor.blockhound.integration.BlockHoundIntegration;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

public class BlockhoundTest {
	private static final AtomicBoolean BLOCKHOUND = new AtomicBoolean();

	@BeforeEach
	public void before() {
		BLOCKHOUND.set(true);

		final List<BlockHoundIntegration> integrations = new ArrayList<>();
		final ServiceLoader<BlockHoundIntegration> services = ServiceLoader.load(BlockHoundIntegration.class);
		services.forEach(integrations::add);

		integrations.add(builder -> builder.blockingMethodCallback(
				blockingMethod -> {
					if (BLOCKHOUND.get()) {
						throw new BlockingCallError(blockingMethod.toString());
					}
				}));

		BlockHound.install(integrations.toArray(new BlockHoundIntegration[0]));
	}

	@AfterEach
	public void after() {
		BLOCKHOUND.set(false);
	}

	@Test
	public void notOk() {
		StepVerifier
				.create(this.buildBlockingMono().subscribeOn(Schedulers.parallel()))
				.expectErrorMatches(BlockingCallError.class::isInstance)
				.verify();
	}

	@Test
	public void ok() {
		StepVerifier
				.create(this.buildBlockingMono().subscribeOn(Schedulers.elastic()))
				.expectNext(1)
				.verifyComplete();
	}

	private Mono<Integer> buildBlockingMono() {
		return Mono.just(1).doOnNext(it -> this.block());
	}

	@SneakyThrows
	private void block() {
		Thread.sleep(1000);
	}

	private static class BlockingCallError extends Error {
		private static final long serialVersionUID = 7869597707821968517L;

		public BlockingCallError(final String message) {
			super(message);
		}
	}
}
