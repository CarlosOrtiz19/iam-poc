package com.iam.token_service.controller;

import com.iam.token_service.model.TokenRequest;
import com.iam.token_service.service.TokenManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/token")
@Slf4j
public class TokenController {
    private final TokenManager tokenManager;

    public TokenController(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateToken(@RequestBody TokenRequest request) {
        // Create JWT
        String token = tokenManager.generateToken(request.getUsername());

        // save TokenRecord in DB TODO
        log.info("TokenRecord guardado para " + request.getUsername());

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            tokenManager.validateToken(token);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping("/renew")
    public ResponseEntity<?> renew(@RequestParam String token) {
        Claims claims = tokenManager.validateToken(token);
        String newToken = tokenManager.generateToken(claims.getSubject());
        return ResponseEntity.ok(Map.of("token", newToken));
    }
}
