package com.ezace.springkeycloak.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ClientLogoutController {

    @PostMapping("/backchannel-logout")
    public ResponseEntity<Void> backChannelLogout(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("============================================ backChannelLogout ============================================");

        // Spring Security 로그아웃 처리를 위한 SecurityContextLogoutHandler 사용
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        // 세션 무효화
        request.getSession().invalidate();

        // JSESSIONID 쿠키 삭제
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);  // 쿠키 삭제
        response.addCookie(cookie);


        return ResponseEntity.ok().build();
    }

    @GetMapping("/frontchannel-logout")
    public void frontChannelLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("============================================ frontChannelLogout ============================================");

        // Spring Security 로그아웃 처리를 위한 SecurityContextLogoutHandler 사용
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        response.sendRedirect("/logout");
    }
}