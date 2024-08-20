package com.ezace.springkeycloak.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(path = "/index")
    public String index() {
        return "index";
    }
    @GetMapping(path = "/user")
    public String user() {
        return "user";
    }
    @GetMapping(path = "/session")
    public String session() {
        return "session";
    }
    @GetMapping(path = "/log")
    public String log() {
        return "log";
    }
    @GetMapping(path = "/group")
    public String group() {
        return "group";
    }
}
