package com.ferbator.shelterapi.controller;

import com.ferbator.shelterapi.dao.dto.OwnerDto;
import com.ferbator.shelterapi.services.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("registrationController")
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    ShelterService service;

    @GetMapping
    public String showRegForm() {
        return "registration.html";
    }
//    @GetMapping("/registration")
//    public String registration(Model model) {
//        model.addAttribute("userForm", new User());
//
//        return "registration";
//    }

    @PostMapping()
    public String registration(@RequestBody OwnerDto owner) {
        try {
            service.addOwner(owner);
            return "OK";
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}