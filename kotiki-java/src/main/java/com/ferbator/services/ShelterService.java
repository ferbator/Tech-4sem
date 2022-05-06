package com.ferbator.services;

import com.ferbator.dao.daoImpl.CatRepository;
import com.ferbator.dao.daoImpl.FriendshipCatRepository;
import com.ferbator.dao.daoImpl.OwnerRepository;
import com.ferbator.dao.daoImpl.OwnershipCatRepository;
import com.ferbator.dao.dto.FriendshipCatDto;
import com.ferbator.dao.dto.OwnerDto;
import com.ferbator.dao.dto.OwnershipCatDto;
import com.ferbator.dao.entities.Cat;
import com.ferbator.dao.dto.CatDto;
import com.ferbator.dao.entities.FriendshipCat;
import com.ferbator.dao.entities.Owner;
import com.ferbator.dao.entities.OwnershipCat;
import com.ferbator.dao.enums.Colors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public OwnerDto findOwnerByLogin(String login) {
        return new OwnerDto(ownerRepository.findByLogin(login));
    }

    @Transactional
    public boolean delOwner(Long id) {
        ownershipCatRepository.deleteAllByOwnerId(id);
        ownerRepository.deleteById(id);
        return true;
    }

    public boolean breakOwnershipCat(Long ownerId, Long catId){
        OwnershipCatDto tmpOwnershipCatDto = new OwnershipCatDto();
        tmpOwnershipCatDto.setCatId(catId);
        tmpOwnershipCatDto.setOwnerId(ownerId);
        ownershipCatRepository.save(new OwnershipCat(tmpOwnershipCatDto));
        return true;
    }

    public boolean makeOwnershipCat(OwnershipCatDto ownershipCat){
        ownershipCatRepository.save(new OwnershipCat(ownershipCat));
        return true;
    }

    public boolean breakFriendshipCat(Long firstCatId, Long secondCatId){
        FriendshipCatDto tmpFriendshipCatDto = new FriendshipCatDto();
        tmpFriendshipCatDto.setFirstCatId(firstCatId);
        tmpFriendshipCatDto.setSecondCatId(secondCatId);
        friendshipCatRepository.save(new FriendshipCat(tmpFriendshipCatDto));
        return true;
    }

    public boolean makeFriendshipCat(FriendshipCatDto friendshipCat){
        friendshipCatRepository.save(new FriendshipCat(friendshipCat));
        return true;
    }
}
