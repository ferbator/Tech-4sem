package com.ferbator.shelterapi.controller;

import com.ferbator.shelterapi.dao.dto.OwnerDto;
import com.ferbator.shelterapi.services.RegistrationService;
import com.ferbator.shelterapi.services.ShelterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для регистрации новых владельцев.
 */
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> showRegForm() {
        return ResponseEntity.ok("Use POST /registration with OwnerDto in body to register.");
    }

    /**
     * Зарегистрировать нового владельца.
     */
    @PostMapping
    public ResponseEntity<String> registration(@RequestBody OwnerDto owner) {
        try {
            service.registerNewOwner(owner);
            return ResponseEntity.ok("Registration successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR: " + e.getMessage());
        }
    }
}
