package com.iam.gateway_service.service;

import java.util.Map;

public interface RiskService {
    Map<String, Object> getRiskEvaluation(Map<String, Object> body);
}
