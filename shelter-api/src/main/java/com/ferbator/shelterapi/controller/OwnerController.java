package com.ferbator.shelterapi.controller;

import com.ferbator.shelterapi.dao.dto.CatDto;
import com.ferbator.shelterapi.dao.dto.FriendshipCatDto;
import com.ferbator.shelterapi.dao.dto.OwnershipCatDto;
import com.ferbator.shelterapi.dao.enums.Colors;
import com.ferbator.shelterapi.services.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
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

    @DeleteMapping("/break-ownership")
    public boolean breakOwnershipCat(@RequestBody OwnershipCatDto ownershipCat) {
        return service.breakOwnershipCat(ownershipCat.getOwnerId(), ownershipCat.getCatId());
    }

    @PostMapping("/make-ownership")
    public boolean makeOwnershipCat(@RequestBody OwnershipCatDto ownershipCat) {
        return service.makeOwnershipCat(ownershipCat);
    }

    @DeleteMapping("/break-friendship")
    public boolean breakFriendship(@RequestBody FriendshipCatDto friendshipCat) {
        return service.breakFriendshipCat(friendshipCat.getFirstCatId(), friendshipCat.getSecondCatId());
    }

    @PostMapping("/make-friendship")
    public boolean makeFriendship(@RequestBody FriendshipCatDto friendshipCat) {
        return service.makeFriendshipCat(friendshipCat);
    }
}
