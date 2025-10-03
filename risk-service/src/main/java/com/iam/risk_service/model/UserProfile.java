package com.iam.risk_service.model;

import lombok.Data;

import java.util.List;

@Data
public class UserProfile {
    private String username;
    private List<String> knownDevices;
}
