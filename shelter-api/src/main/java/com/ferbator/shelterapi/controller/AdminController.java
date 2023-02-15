package com.ferbator.shelterapi.controller;

import com.ferbator.shelterapi.dao.dto.FriendshipCatDto;
import com.ferbator.shelterapi.dao.dto.OwnerDto;
import com.ferbator.shelterapi.dao.dto.OwnershipCatDto;
import com.ferbator.shelterapi.services.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@Secured("ROLE_ADMIN")
public class AdminController {
    @Autowired
    ShelterService service;

    @GetMapping("")
    public String adminPage() {
        return "Shelter service for admin";
    }

    @PostMapping("/add-owner")
    public boolean addOwner(@RequestBody OwnerDto ownerDTO) {
        return service.addOwner(ownerDTO);
    }

    @DeleteMapping("/delete-owner/{id}")
    public boolean delOwnerById(@PathVariable("id") Long id) {
        return service.delOwner(id);
    }

    @GetMapping("/find-all-owner")
    public List<OwnerDto> findAllOwners() {
        return service.getListAllOwners();
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
