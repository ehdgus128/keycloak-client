package com.ezace.springkeycloak.controller;

import com.ezace.springkeycloak.entity.User;
import com.ezace.springkeycloak.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<String> getUserInfo(HttpSession session) {

        System.out.println("getuserinfo 들어옴");

        String url = "https://172.30.1.132/admin/realms/external/ui-ext/brute-force-user?briefRepresentation=true&first=0&max=11&q=";
        return userService.getUserInfo(session, url);
    }

    @DeleteMapping("/unlockUser/{userId}")
    public ResponseEntity<String> unlockUser(@PathVariable String userId, HttpSession session) {
        String url = String.format("https://172.30.1.132/admin/realms/external/attack-detection/brute-force/users/%s", userId);
        return userService.unlockUser(session, url);
    }
}
