package com.ezace.springkeycloak.service;

import com.ezace.springkeycloak.entity.User;
import com.ezace.springkeycloak.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Value("${keycloak.realm}")
    private String realm;

    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> getUserInfo(HttpSession session, String url) {
        HttpHeaders headers = new HttpHeaders();
        String accessToken = (String) session.getAttribute("accessToken");
        headers.setBearerAuth(accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching user info");
        }
    }

    public ResponseEntity<String> unlockUser(HttpSession session, String url) {
        HttpHeaders headers = new HttpHeaders();
        String accessToken = (String) session.getAttribute("accessToken");
        headers.setBearerAuth(accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
            if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
                return ResponseEntity.ok("User unlocked successfully");
            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to unlock user");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error unlocking user");
        }
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getHashPwd());
        user.setHashPwd(encodedPassword);
        return userRepository.save(user);
    }
}
