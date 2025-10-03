package com.iam.auth_service.controller;

import com.iam.auth_service.model.LoginRequest;
import com.iam.auth_service.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private TokenService tokenService;
    private static final String SECRET_KEY = "mi-super-clave-dummy-para-jwt-1234567890!!"; // En prod: usar un secrets manager

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        // dummy check
        if ("admin".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

            String token = tokenService.generateToken(request.getUsername());

            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }
}
