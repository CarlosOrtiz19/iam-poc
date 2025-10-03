package com.iam.gateway_service.service;

import com.iam.gateway_service.externe.RiskServiceWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RiskServiceImpl implements RiskService {

    private final RiskServiceWebClient riskServiceWebClient;

    public RiskServiceImpl(RiskServiceWebClient riskServiceWebClient) {
        this.riskServiceWebClient = riskServiceWebClient;
    }

    @Override
    public Map<String, Object> getRiskEvaluation(Map<String, Object> body) {
        return riskServiceWebClient.getRiskEvaluation(body);
    }
}
