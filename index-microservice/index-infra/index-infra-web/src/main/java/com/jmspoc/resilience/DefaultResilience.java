package com.jmspoc.resilience;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class DefaultResilience
        implements ResilientService {

    private final TimeLimiter timeLimiter;
    private final CircuitBreaker circuitBreaker;
    private final RateLimiter rateLimiter;
    private final Retry retry;

    public void process(Supplier<Void> method) {
        var circuitBreakerSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, method);
        var rateSupplier = RateLimiter.decorateSupplier(rateLimiter, circuitBreakerSupplier);
        Supplier<Void> retrySupplier = () -> retry.executeSupplier(rateSupplier);
        //Callable<Void> chainedCallable = CircuitBreaker.decorateCallable(circuitBreaker, callable);
        circuitBreakerSupplier.get();
    }

    private CompletableFuture<Void> timeoutSupplier(Supplier<Void> supplier) {
        return CompletableFuture.supplyAsync(supplier);
    }

}
