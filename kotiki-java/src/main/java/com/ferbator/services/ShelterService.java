package com.ferbator.services;

import com.ferbator.dao.daoImpl.CatDAO;
import com.ferbator.dao.daoImpl.FriendshipCatDAO;
import com.ferbator.dao.daoImpl.OwnerDAO;
import com.ferbator.dao.daoImpl.OwnershipCatDAO;
import com.ferbator.dao.dto.OwnerDTO;
import com.ferbator.dao.entities.Cat;
import com.ferbator.dao.dto.CatDTO;
import com.ferbator.dao.entities.Owner;
import com.ferbator.dao.enums.Colors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShelterService {
    @Autowired
    CatDAO catDAO;
    @Autowired
    OwnerDAO ownerDAO;
    @Autowired
    FriendshipCatDAO friendshipCatDAO;
    @Autowired
    OwnershipCatDAO ownershipCatDAO;

    public List<CatDTO> getListAllCats() {
        return catDAO.findAll().stream().map(CatDTO::new).toList();
    }

    public List<OwnerDTO> getListAllOwners() {
        return ownerDAO.findAll().stream().map(OwnerDTO::new).toList();
    }

    public List<CatDTO> getListAllOneColorCats(Colors colors) {
        return catDAO.findAllByColor(colors).stream().map(CatDTO::new).toList();
    }

    public boolean addCat(CatDTO cat) {
        catDAO.save(new Cat(cat));
        return true;
    }

    public boolean delCat(Long id) {
        ownershipCatDAO.deleteAllByCatId(id);
        friendshipCatDAO.deleteAllByFirstCatIdOrSecondCatId(id, id);
        catDAO.deleteById(id);
        return true;
    }

    public boolean addOwner(OwnerDTO owner) {
        ownerDAO.save(new Owner(owner));
        return true;
    }

    @Transactional
    public boolean delOwner(Long id) {
        ownershipCatDAO.deleteAllByOwnerId(id);
        ownerDAO.deleteById(id);
        return true;
    }
}
