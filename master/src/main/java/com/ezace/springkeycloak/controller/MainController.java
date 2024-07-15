package com.ezace.springkeycloak.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Value("${keycloak.realm}")
    private String realm;

    @GetMapping(path = "/main")
    public String main() {
        return "main";
    }
}
