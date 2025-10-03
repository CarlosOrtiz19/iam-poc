package com.iam.gateway_service.config;


import com.iam.gateway_service.service.RateLimiterService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateLimiterTest {

    @Test
    void tryConsume_shouldAllowUpToLimit_thenDeny() {
        RateLimiterService rateLimiter = new RateLimiterService();

        // Should allow 10 requests
        for (int i = 0; i < 10; i++) {
            assertThat(rateLimiter.tryConsume()).isTrue();
        }

        // 11th request should be denied
        assertThat(rateLimiter.tryConsume()).isFalse();
    }

}