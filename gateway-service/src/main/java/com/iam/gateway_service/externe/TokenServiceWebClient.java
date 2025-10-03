package com.iam.gateway_service.externe;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class TokenServiceWebClient {

    private final WebClient tokenServiceWebClientConfig;

    public TokenServiceWebClient(@Qualifier("tokenServiceWebClientConfig") WebClient tokenServiceWebClientConfig) {
        this.tokenServiceWebClientConfig = tokenServiceWebClientConfig;
    }

    public Boolean isValidToken(String token) {
        log.info("calling token service");
        return tokenServiceWebClientConfig.post()
                .uri("/token/validate")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(e -> {
                    log.error("Error al validar token: " + e.getMessage());
                    return Mono.just(false);
                })
                .block();
    }


}
