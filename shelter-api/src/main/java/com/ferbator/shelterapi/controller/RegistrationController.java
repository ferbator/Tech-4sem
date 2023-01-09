package com.ferbator.shelterapi.controller;

import com.ferbator.shelterapi.dao.dto.OwnerDto;
import com.ferbator.shelterapi.services.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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