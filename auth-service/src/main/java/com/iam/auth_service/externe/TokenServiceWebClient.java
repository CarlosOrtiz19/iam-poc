package com.iam.auth_service.externe;

import com.iam.auth_service.model.TokenRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
@Slf4j
public class TokenServiceWebClient {

    private final WebClient webClient;

    public String getToken(String username) {
        log.info("calling token service");
        return webClient.post()
                .uri("/token/generate")
                .bodyValue(new TokenRequest(username))
                .retrieve()
                .bodyToMono(String.class)
                .block(); // bloqueo simple, en producción usar reactivo o async
    }


}
