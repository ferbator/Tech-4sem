package com.ferbator.shelterapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("loginController")
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    String login() {
        return "login";
    }
}
