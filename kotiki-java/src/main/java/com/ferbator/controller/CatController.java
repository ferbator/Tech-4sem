package com.ferbator.controller;

import com.ferbator.dao.dto.CatDto;
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

    @GetMapping("/find-all-cats")
    public List<CatDto> findAllCats() {
        return service.getListAllCats();
    }

    @GetMapping("/find-all-one-color-cats/{color}")
    public List<CatDto> findAllOneColorCats(@PathVariable("color") String color) {
        try {
            return service.getListAllOneColorCats(Colors.valueOf(color));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-cat")
    public boolean addCat(@RequestBody CatDto catDTO) {
        return service.addCat(catDTO);
    }

    @DeleteMapping("/delete-cat/{id}")
    public boolean delCatById(@PathVariable("id") Long id) {
        return service.delCat(id);
    }
}
