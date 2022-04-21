package com.ferbator.controller;

import com.ferbator.dao.dto.CatDTO;
import com.ferbator.dao.enums.Colors;
import com.ferbator.services.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CatController {
    @Autowired
    ShelterService service;

    @GetMapping("/findAllCats")
    public List<CatDTO> findAllCats() {
        return service.getListAllCats();
    }

    @GetMapping("/findAllOneColorCats/{color}")
    public List<CatDTO> findAllOneColorCats(@PathVariable("color") String color) {
        try {
            return service.getListAllOneColorCats(Colors.valueOf(color));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addCat")
    public boolean addCat(@RequestBody CatDTO catDTO) {
        return service.addCat(catDTO);
    }

    @DeleteMapping("/delCat/{id}")
    public boolean delCatById(@PathVariable("id") Long id) {
        return service.delCat(id);
    }
}
