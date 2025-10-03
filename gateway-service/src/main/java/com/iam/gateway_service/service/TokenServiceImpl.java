package com.iam.gateway_service.service;

import com.iam.gateway_service.externe.TokenServiceWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenServiceWebClient tokenServiceWebClient;

    @Override
    public Boolean isTokenValid(String token) {
        return tokenServiceWebClient.isValidToken(token);
    }
}
