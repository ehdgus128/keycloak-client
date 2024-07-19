package com.ezace.springkeycloak.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class KeycloakConfigController {

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;

    @Value("${spring.security.oauth2.client.registration.external.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.external.client-secret}")
    private String clientSecret;

    @GetMapping("/keycloak-config")
    public Map<String, String> getKeycloakConfig(HttpSession session) {

        // 세션에서 refreshToken 가져오기
        String refreshToken = (String) session.getAttribute("refreshToken");
        String idToken = (String) session.getAttribute("idToken");


        Map<String, String> config = new HashMap<>();
        config.put("keycloakUrl", keycloakUrl);
        config.put("realm", realm);
        config.put("refreshToken", refreshToken);
        config.put("idToken", idToken);

        return config;
    }
}