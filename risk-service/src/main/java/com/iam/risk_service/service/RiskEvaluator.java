package com.iam.risk_service.service;

import com.iam.risk_service.model.RiskRequest;
import com.iam.risk_service.model.RiskScore;
import com.iam.risk_service.model.UserProfile;
import org.springframework.stereotype.Service;

@Service
public class RiskEvaluator {
    public RiskScore evaluate(RiskRequest request, UserProfile profile) {
        int score = 0;

        // Chequeos simulados
        if (!profile.getKnownDevices().contains(request.getDeviceId())) {
            score += 40; // dispositivo desconocido
        }

        if (request.getIp().startsWith("192.168")) {
            score += 0; // IP confiable
        } else {
            score += 20; // IP externa
        }

        if (request.getAction().equals("transfer")) {
            score += 20; // acciones sensibles
        }

        RiskScore riskScore = new RiskScore();
        riskScore.setScore(score);
        riskScore.setRiskLevel(score < 30 ? "LOW" : (score < 60 ? "MEDIUM" : "HIGH"));
        return riskScore;
    }
}
