package com.ferbator.shelterapi.services;

import com.ferbator.shelterapi.dao.daoImpl.CatRepository;
import com.ferbator.shelterapi.dao.daoImpl.FriendshipCatRepository;
import com.ferbator.shelterapi.dao.daoImpl.OwnerRepository;
import com.ferbator.shelterapi.dao.daoImpl.OwnershipCatRepository;
import com.ferbator.shelterapi.dao.dto.CatDto;
import com.ferbator.shelterapi.dao.dto.FriendshipCatDto;
import com.ferbator.shelterapi.dao.dto.OwnerDto;
import com.ferbator.shelterapi.dao.dto.OwnershipCatDto;
import com.ferbator.shelterapi.dao.entities.Cat;
import com.ferbator.shelterapi.dao.entities.FriendshipCat;
import com.ferbator.shelterapi.dao.entities.Owner;
import com.ferbator.shelterapi.dao.entities.OwnershipCat;
import com.ferbator.shelterapi.dao.enums.Colors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Сервис для управления объектами приюта: котами, владельцами,
 * дружескими отношениями между котами и связями между владельцем и котом.
 */
@Service
public class ShelterService {

    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;
    private final FriendshipCatRepository friendshipCatRepository;
    private final OwnershipCatRepository ownershipCatRepository;

    @Autowired
    public ShelterService(CatRepository catRepository,
                          OwnerRepository ownerRepository,
                          FriendshipCatRepository friendshipCatRepository,
                          OwnershipCatRepository ownershipCatRepository) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
        this.friendshipCatRepository = friendshipCatRepository;
        this.ownershipCatRepository = ownershipCatRepository;
    }

    public List<CatDto> getListAllCats() {
        return catRepository.findAll().stream()
                .map(CatDto::new)
                .toList();
    }

    public List<OwnerDto> getListAllOwners() {
        return ownerRepository.findAll().stream()
                .map(OwnerDto::new)
                .toList();
    }

    public List<CatDto> getListAllOneColorCats(Colors colors) {
        return catRepository.findAllByColor(colors).stream()
                .map(CatDto::new)
                .toList();
    }

    @Transactional
    public boolean addCat(CatDto cat) {
        catRepository.save(new Cat(cat));
        return true;
    }

    @Transactional
    public boolean delCat(Long id) {
        ownershipCatRepository.deleteAllByCatId(id);
        friendshipCatRepository.deleteAllByFirstCatIdOrSecondCatId(id, id);
        catRepository.deleteById(id);
        return true;
    }

    @Transactional
    public boolean addOwner(OwnerDto owner) {
        ownerRepository.save(new Owner(owner));
        return true;
    }

    public OwnerDto findOwnerByLogin(String login) {
        Owner owner = ownerRepository.findByLogin(login);
        return new OwnerDto(owner);
    }

    @Transactional
    public boolean delOwner(Long id) {
        ownershipCatRepository.deleteAllByOwnerId(id);
        ownerRepository.deleteById(id);
        return true;
    }

    public boolean breakOwnershipCat(Long ownerId, Long catId) {
        OwnershipCatDto tmpOwnershipCatDto = new OwnershipCatDto();
        tmpOwnershipCatDto.setCatId(catId);
        tmpOwnershipCatDto.setOwnerId(ownerId);
        ownershipCatRepository.save(new OwnershipCat(tmpOwnershipCatDto));
        return true;
    }

    public boolean makeOwnershipCat(OwnershipCatDto ownershipCat) {
        ownershipCatRepository.save(new OwnershipCat(ownershipCat));
        return true;
    }

    public boolean breakFriendshipCat(Long firstCatId, Long secondCatId) {
        FriendshipCatDto tmpFriendshipCatDto = new FriendshipCatDto();
        tmpFriendshipCatDto.setFirstCatId(firstCatId);
        tmpFriendshipCatDto.setSecondCatId(secondCatId);
        friendshipCatRepository.save(new FriendshipCat(tmpFriendshipCatDto));
        return true;
    }

    public boolean makeFriendshipCat(FriendshipCatDto friendshipCat) {
        friendshipCatRepository.save(new FriendshipCat(friendshipCat));
        return true;
    }
}
