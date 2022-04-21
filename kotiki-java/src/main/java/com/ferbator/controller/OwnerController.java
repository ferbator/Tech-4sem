package com.ferbator.controller;

import com.ferbator.dao.dto.OwnerDTO;
import com.ferbator.services.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    ShelterService service;

    @PostMapping("/addOwner")
    public boolean addOwner(@RequestBody OwnerDTO ownerDTO) {
        return service.addOwner(ownerDTO);
    }

    @DeleteMapping("/delOwner/{id}")
    public boolean delOwnerById(@PathVariable("id") Long id) {
        return service.delOwner(id);
    }

    @GetMapping("/findAllOwner")
    public List<OwnerDTO> findAllOwners() {
        return service.getListAllOwners();
    }
}
