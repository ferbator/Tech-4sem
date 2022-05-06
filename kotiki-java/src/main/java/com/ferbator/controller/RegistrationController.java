package com.ferbator.controller;

import com.ferbator.dao.dto.OwnerDto;
import com.ferbator.services.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("registrationController")
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    ShelterService service;


    @PostMapping()
    public String registration(@RequestBody OwnerDto owner){
        try {
            service.addOwner(owner);
            return "OK";
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}