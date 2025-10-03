package com.iam.gateway_service.externe;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class RiskServiceWebClient {

    private final WebClient riskServiceWebClientConfig;

    public RiskServiceWebClient(@Qualifier("riskServiceWebClientConfig") WebClient riskServiceWebClientConfig) {
        this.riskServiceWebClientConfig = riskServiceWebClientConfig;
    }

    public Map<String, Object> getRiskEvaluation(Map<String, Object> body) {
        return riskServiceWebClientConfig.post()
                .uri("/risk/score")
                .bodyValue(body)  // body debe contener username, deviceId, action, ip
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}
