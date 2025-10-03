package com.iam.gateway_service.controller;


import com.iam.gateway_service.service.RateLimiterService;
import com.iam.gateway_service.service.RiskService;
import com.iam.gateway_service.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private final RateLimiterService rateLimiterService;
    private final RiskService riskService;
    private final TokenService tokenService;

    public GatewayController(RateLimiterService rateLimiterService, RiskService riskService, TokenService tokenService) {
        this.rateLimiterService = rateLimiterService;
        this.riskService = riskService;
        this.tokenService = tokenService;
    }

    @PostMapping("/forward")
    public ResponseEntity<?> forward(@RequestHeader("Authorization") String authHeader,
                                     @RequestBody Map<String, Object> body) {

        // 1️⃣ Rate Limiter
        if (!rateLimiterService.tryConsume()) {
            return ResponseEntity.status(429).body("Too Many Requests");
        }

        String token = authHeader.substring(7);

        // token validation
        Boolean valid = tokenService.isTokenValid(token);

        if (valid == null || !valid) {
            return ResponseEntity.status(401).body("Token invalid or revoked");
        }

        // risk-Service
        Map<String, Object> riskScore = riskService.getRiskEvaluation(body);

        // Decisión basada en riesgo TODO extraer en servicio
        String riskLevel = (String) riskScore.get("riskLevel");
        if ("HIGH".equalsIgnoreCase(riskLevel)) {
            //TODO create reponse
            return ResponseEntity.status(403).body("Request blocked due to high risk");
        }

        // 5️⃣ Forward a microservicio real TODO adjuntar verdadero microservicio
        return ResponseEntity.ok("Request processed successfully");
    }
}
