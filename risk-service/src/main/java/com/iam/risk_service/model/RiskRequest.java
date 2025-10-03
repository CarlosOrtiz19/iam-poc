package com.iam.risk_service.model;

import lombok.Data;

@Data
public class RiskRequest {
    private String username;
    private String deviceId;
    private String ip;
    private String action;
}
