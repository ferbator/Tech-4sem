package com.ferbator.services;

import com.ferbator.dao.daoImpl.CatRepository;
import com.ferbator.dao.daoImpl.FriendshipCatRepository;
import com.ferbator.dao.daoImpl.OwnerRepository;
import com.ferbator.dao.daoImpl.OwnershipCatRepository;
import com.ferbator.dao.dto.OwnerDto;
import com.ferbator.dao.entities.Cat;
import com.ferbator.dao.dto.CatDto;
import com.ferbator.dao.entities.Owner;
import com.ferbator.dao.enums.Colors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShelterService {
    CatRepository catRepository;
    OwnerRepository ownerRepository;
    FriendshipCatRepository friendshipCatRepository;
    OwnershipCatRepository ownershipCatRepository;

    @Autowired
    public ShelterService(CatRepository catRepository, OwnerRepository ownerRepository, FriendshipCatRepository friendshipCatRepository, OwnershipCatRepository ownershipCatRepository) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
        this.friendshipCatRepository = friendshipCatRepository;
        this.ownershipCatRepository = ownershipCatRepository;
    }

    public List<CatDto> getListAllCats() {
        return catRepository.findAll().stream().map(CatDto::new).toList();
    }

    public List<OwnerDto> getListAllOwners() {
        return ownerRepository.findAll().stream().map(OwnerDto::new).toList();
    }

    public List<CatDto> getListAllOneColorCats(Colors colors) {
        return catRepository.findAllByColor(colors).stream().map(CatDto::new).toList();
    }

    public boolean addCat(CatDto cat) {
        catRepository.save(new Cat(cat));
        return true;
    }

    public boolean delCat(Long id) {
        ownershipCatRepository.deleteAllByCatId(id);
        friendshipCatRepository.deleteAllByFirstCatIdOrSecondCatId(id, id);
        catRepository.deleteById(id);
        return true;
    }

    public boolean addOwner(OwnerDto owner) {
        ownerRepository.save(new Owner(owner));
        return true;
    }

    @Transactional
    public boolean delOwner(Long id) {
        ownershipCatRepository.deleteAllByOwnerId(id);
        ownerRepository.deleteById(id);
        return true;
    }
}
