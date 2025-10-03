package com.iam.auth_service.service;

import com.iam.auth_service.externe.TokenServiceWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenServiceWebClient tokenServiceWebClient;

    public String generateToken(String username) {
        return tokenServiceWebClient.getToken(username);
    }
}
