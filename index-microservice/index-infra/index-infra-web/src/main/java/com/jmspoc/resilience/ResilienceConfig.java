package com.jmspoc.resilience;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

@Configuration
public class ResilienceConfig {

    private static final String contexResilient4JName = "loader";

    @Bean
    public Retry retry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(10)
                .waitDuration(Duration.ofMillis(1000))
                .retryExceptions(IOException.class, TimeoutException.class, RuntimeException.class)
                .failAfterMaxAttempts(true)
                .build();

        RetryRegistry registry = RetryRegistry.of(config);

        return registry.retry(contexResilient4JName);
    }

    @Bean
    public TimeLimiter timeLimiter() {
        long ttl = 3000;
        TimeLimiterConfig config = TimeLimiterConfig
                .custom()
                .timeoutDuration(Duration.ofMillis(ttl))
                .build();
        return TimeLimiter.of(config);
    }


    @Bean
    public CircuitBreaker circuitBreaker() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(20)
                .slidingWindowSize(5)
                .slidingWindowType(COUNT_BASED)
                .build();

        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        return registry.circuitBreaker(contexResilient4JName);
    }

    @Bean
    public RateLimiter rateLimiter() {
        RateLimiterConfig config = RateLimiterConfig.custom().limitForPeriod(200).build();
        RateLimiterRegistry registry = RateLimiterRegistry.of(config);
        return registry.rateLimiter(contexResilient4JName);
    }

}
