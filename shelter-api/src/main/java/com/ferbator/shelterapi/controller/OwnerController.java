package com.ferbator.shelterapi.controller;

import com.ferbator.shelterapi.dao.dto.CatDto;
import com.ferbator.shelterapi.dao.dto.FriendshipCatDto;
import com.ferbator.shelterapi.dao.dto.OwnershipCatDto;
import com.ferbator.shelterapi.dao.enums.Colors;
import com.ferbator.shelterapi.services.ShelterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Контроллер для владельцев:
 * просмотр котов, добавление и удаление котов,
 * управление дружбой и владением котами (в рамках своих полномочий).
 */
@RestController
@RequestMapping("/api/owner")
@Secured("ROLE_USER")
public class OwnerController {

    private final ShelterService service;

    public OwnerController(ShelterService service) {
        this.service = service;
    }

    /**
     * Получить список всех котов.
     */
    @GetMapping("/cats")
    public ResponseEntity<List<CatDto>> findAllCats() {
        return ResponseEntity.ok(service.getListAllCats());
    }

    /**
     * Получить список котов определённого цвета.
     */
    @GetMapping("/cats/color/{color}")
    public ResponseEntity<List<CatDto>> findAllOneColorCats(@PathVariable("color") String color) {
        try {
            Colors c = Colors.valueOf(color.toUpperCase());
            return ResponseEntity.ok(service.getListAllOneColorCats(c));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid color");
        }
    }

    /**
     * Добавить кота.
     */
    @PostMapping("/cats")
    public ResponseEntity<Void> addCat(@RequestBody CatDto catDTO) {
        service.addCat(catDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Удалить кота по ID.
     */
    @DeleteMapping("/cats/{id}")
    public ResponseEntity<Void> delCatById(@PathVariable("id") Long id) {
        service.delCat(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Разорвать связь владения котом.
     * Здесь логика может предполагать, что владелец разрывает связь только со своими котами.
     */
    @DeleteMapping("/ownership")
    public ResponseEntity<Void> breakOwnershipCat(@RequestBody OwnershipCatDto ownershipCat) {
        service.breakOwnershipCat(ownershipCat.getOwnerId(), ownershipCat.getCatId());
        return ResponseEntity.ok().build();
    }

    /**
     * Установить связь владения котом.
     */
    @PostMapping("/ownership")
    public ResponseEntity<Void> makeOwnershipCat(@RequestBody OwnershipCatDto ownershipCat) {
        service.makeOwnershipCat(ownershipCat);
        return ResponseEntity.ok().build();
    }

    /**
     * Разорвать дружбу между котами.
     */
    @DeleteMapping("/friendship")
    public ResponseEntity<Void> breakFriendship(@RequestBody FriendshipCatDto friendshipCat) {
        service.breakFriendshipCat(friendshipCat.getFirstCatId(), friendshipCat.getSecondCatId());
        return ResponseEntity.ok().build();
    }

    /**
     * Установить дружбу между котами.
     */
    @PostMapping("/friendship")
    public ResponseEntity<Void> makeFriendship(@RequestBody FriendshipCatDto friendshipCat) {
        service.makeFriendshipCat(friendshipCat);
        return ResponseEntity.ok().build();
    }
}
