package com.ferbator.shelterapi.controller;

import com.ferbator.shelterapi.dao.dto.FriendshipCatDto;
import com.ferbator.shelterapi.dao.dto.OwnerDto;
import com.ferbator.shelterapi.dao.dto.OwnershipCatDto;
import com.ferbator.shelterapi.services.ShelterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для административных операций:
 * управление владельцами, дружескими связями и владениями котами.
 */
@RestController
@RequestMapping("/api/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

    private final ShelterService service;

    public AdminController(ShelterService service) {
        this.service = service;
    }

    /**
     * Стартовая страница для администратора.
     */
    @GetMapping
    public ResponseEntity<String> adminPage() {
        return ResponseEntity.ok("Shelter service for admin");
    }

    /**
     * Добавить нового владельца.
     */
    @PostMapping("/owners")
    public ResponseEntity<Void> addOwner(@RequestBody OwnerDto ownerDTO) {
        service.addOwner(ownerDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Удалить владельца по его ID.
     */
    @DeleteMapping("/owners/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable("id") Long id) {
        service.delOwner(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Получить список всех владельцев.
     */
    @GetMapping("/owners")
    public ResponseEntity<List<OwnerDto>> findAllOwners() {
        return ResponseEntity.ok(service.getListAllOwners());
    }

    /**
     * Разорвать связь владения котом. (например, когда владелец больше не владеет котом)
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
