package com.ezace.springkeycloak.service;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final JwtDecoder jwtDecoder;

    public TokenService() {
        // Keycloak의 JWK 세트 URL을 사용하여 디코더를 초기화합니다.
        this.jwtDecoder = JwtDecoders.fromIssuerLocation("https://172.30.1.132/realms/external");
    }

    public void verifyLogoutToken(String token) throws JwtException {
        Jwt decodedToken = jwtDecoder.decode(token);

        // 추가 검증 로직을 구현합니다.
        // 예: iss, aud, events 등의 클레임을 확인합니다.

        System.out.println("Token verified: " + decodedToken.getTokenValue());
    }
}
