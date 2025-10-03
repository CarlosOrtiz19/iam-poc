package com.iam.risk_service.model;

import lombok.Data;

@Data
public class RiskScore {
    private int score;
    private String riskLevel;
}
