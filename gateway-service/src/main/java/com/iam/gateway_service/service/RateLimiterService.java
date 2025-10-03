package com.iam.gateway_service.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {
    private final Bucket bucket;

    public RateLimiterService() {
        Bandwidth limit = Bandwidth.classic(
                5, // 5 requests
                Refill.intervally(5, Duration.ofMinutes(1)) // refill 10 every minute
        );
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    public boolean tryConsume() {
        return bucket.tryConsume(1);
    }
}
