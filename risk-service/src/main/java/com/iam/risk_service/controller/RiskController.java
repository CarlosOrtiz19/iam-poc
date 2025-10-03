package com.iam.risk_service.controller;

import com.iam.risk_service.model.RiskRequest;
import com.iam.risk_service.model.RiskScore;
import com.iam.risk_service.model.UserProfile;
import com.iam.risk_service.service.RiskEvaluator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/risk")
public class RiskController {
    private final RiskEvaluator evaluator;

    public RiskController(RiskEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @PostMapping("/score")
    public RiskScore score(@RequestBody RiskRequest request) {
        // Dummy UserProfile
        UserProfile profile = new UserProfile();
        profile.setUsername(request.getUsername());
        profile.setKnownDevices(java.util.List.of("device123", "device456"));

        return evaluator.evaluate(request, profile);
    }
}
