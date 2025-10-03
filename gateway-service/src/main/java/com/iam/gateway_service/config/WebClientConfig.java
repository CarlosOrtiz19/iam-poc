package com.iam.gateway_service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    @Qualifier("tokenServiceWebClientConfig")
    public WebClient tokenServiceWebClientConfig() {
        return WebClient.builder()
                .baseUrl("http://localhost:8082") // Token-Service
                .build();
    }

    @Bean
    @Qualifier("riskServiceWebClientConfig")
    public WebClient riskServiceWebClientConfig() {
        return WebClient.builder()
                .baseUrl("http://localhost:8083") // Risk-Service
                .build();
    }
}
