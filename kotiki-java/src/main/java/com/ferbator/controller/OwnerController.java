package com.ferbator.controller;

import com.ferbator.dao.dto.OwnerDto;
import com.ferbator.services.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    ShelterService service;

    @PostMapping("/add-owner")
    public boolean addOwner(@RequestBody OwnerDto ownerDTO) {
        return service.addOwner(ownerDTO);
    }

    @DeleteMapping("/del-owner/{id}")
    public boolean delOwnerById(@PathVariable("id") Long id) {
        return service.delOwner(id);
    }

    @GetMapping("/find-all-owner")
    public List<OwnerDto> findAllOwners() {
        return service.getListAllOwners();
    }
}
