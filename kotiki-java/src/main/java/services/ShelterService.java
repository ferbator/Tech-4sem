package services;

import dao.daoInterface.DAO;
import dao.entities.Cats;
import dao.entities.FriendshipCats;
import dao.entities.Owners;
import dao.entities.OwnershipCats;
import dao.enums.Colors;
import dao.tools.DAOException;
import services.tools.ShelterServiceException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ShelterService {
    private DAO<Owners> daoOwn;
    private DAO<Cats> daoCat;
    private DAO<OwnershipCats> daoOwnShip;
    private DAO<FriendshipCats> daoFriendShip;

    public ShelterService(DAO<Owners> daoOwn, DAO<Cats> daoCat, DAO<OwnershipCats> daoOwnShip, DAO<FriendshipCats> daoFriendShip) {
        this.daoOwn = daoOwn;
        this.daoCat = daoCat;
        this.daoOwnShip = daoOwnShip;
        this.daoFriendShip = daoFriendShip;
    }

    public boolean addOwnerToBase(String name, String birthday) throws ShelterServiceException {
        Owners own = new Owners();
        own.setName(name);
        own.setBirthday(Timestamp.valueOf(birthday));
        try {
            return daoOwn.add(own);
        } catch (DAOException e) {
            throw new ShelterServiceException("Error when adding", e);
        }
    }

    public boolean addCatToBase(String name, Colors color, String breed, String birthday) throws ShelterServiceException {
        Cats cat = new Cats();
        cat.setName(name);
        cat.setColor(color);
        cat.setBirthday(Timestamp.valueOf(birthday));
        cat.setBreed(breed);
        try {
            return daoCat.add(cat);
        } catch (DAOException e) {
            throw new ShelterServiceException("Error when adding", e);
        }
    }

    public boolean delOwnerFromBase(long idOwn) throws ShelterServiceException {
        List<OwnershipCats> tmpOwnershipCats = null;
        try {
            tmpOwnershipCats = daoOwnShip.findAll();
        } catch (DAOException e) {
            throw new ShelterServiceException("Error when findAll", e);
        }
        for (OwnershipCats ship : tmpOwnershipCats) {
            if (ship.getOwnerId() == idOwn)
                try {
                    return daoOwnShip.del(ship);
                } catch (DAOException e) {
                    throw new ShelterServiceException("Error when adding", e);
                }
        }
        try {
            return daoOwn.del(daoOwn.getById(idOwn));
        } catch (DAOException e) {
            throw new ShelterServiceException("Error when adding", e);
        }
    }

    public boolean delCatFromBase(long idCat) throws ShelterServiceException {
        List<OwnershipCats> tmpOwnershipCats = null;
        try {
            tmpOwnershipCats = daoOwnShip.findAll();
        } catch (DAOException e) {
            throw new ShelterServiceException("Error when findAll", e);
        }
        for (OwnershipCats ship : tmpOwnershipCats) {
            if (ship.getCatId() == idCat)
                try {
                    return daoOwnShip.del(ship);
                } catch (DAOException e) {
                    throw new ShelterServiceException("Error when delete", e);
                }
        }
        List<FriendshipCats> tmpFriendshipCats = null;
        try {
            tmpFriendshipCats = daoFriendShip.findAll();
        } catch (DAOException e) {
            throw new ShelterServiceException("Error when findAll", e);
        }
        for (FriendshipCats ship : tmpFriendshipCats) {
            if (ship.getFirstCatId() == idCat || ship.getSecondCatId() == idCat)
                try {
                    return daoFriendShip.del(ship);
                } catch (DAOException e) {
                    throw new ShelterServiceException("Error when delete", e);
                }
        }
        try {
            return daoCat.del(daoCat.getById(idCat));
        } catch (DAOException e) {
            throw new ShelterServiceException("Error when delete", e);
        }
    }

    public boolean startСatOwnership(long idOwner, long idCat) throws ShelterServiceException {
        OwnershipCats ship = new OwnershipCats();
        ship.setOwnerId(idOwner);
        ship.setCatId(idCat);
        try {
            return daoOwnShip.add(ship);
        } catch (DAOException e) {
            throw new ShelterServiceException("Error when adding", e);
        }
    }

    public boolean cancelCatOwnership(long idOwner, long idCat) throws ShelterServiceException {
        List<OwnershipCats> tmpOwnershipCats = null;
        try {
            tmpOwnershipCats = daoOwnShip.findAll();
        } catch (DAOException e) {
            throw new ShelterServiceException("Error when findAll", e);
        }
        for (OwnershipCats ship : tmpOwnershipCats) {
            if (ship.getOwnerId() == idOwner && ship.getCatId() == idCat)
                try {
                    return daoOwnShip.del(ship);
                } catch (DAOException e) {
                    throw new ShelterServiceException("Error when delete", e);
                }
        }
        return false;
    }

    public boolean startСatFriendship(long idFirstCat, long idSecondCat) throws ShelterServiceException {
        FriendshipCats ship = new FriendshipCats();
        ship.setFirstCatId(idFirstCat);
        ship.setSecondCatId(idSecondCat);
        try {
            return daoFriendShip.add(ship);
        } catch (DAOException e) {
            throw new ShelterServiceException("Error when adding", e);
        }
    }

    public boolean cancelCatFriendship(long idFirstCat, long idSecondCat) throws ShelterServiceException {
        List<FriendshipCats> tmpFriendshipCats = null;
        try {
            tmpFriendshipCats = daoFriendShip.findAll();
        } catch (DAOException e) {
            throw new ShelterServiceException("Error when findAll", e);
        }
        for (FriendshipCats ship : tmpFriendshipCats) {
            if (ship.getFirstCatId() == idFirstCat && ship.getSecondCatId() == idSecondCat)
                try {
                    return daoFriendShip.del(ship);
                } catch (DAOException e) {
                    throw new ShelterServiceException("Error when delete", e);
                }
        }
        return false;
    }

    public List<Cats> getListFriendsForCat(long id) throws ShelterServiceException {
        List<FriendshipCats> tmpFriendshipCats = null;
        try {
            tmpFriendshipCats = daoFriendShip.findAll();
        } catch (DAOException e) {
            throw new ShelterServiceException("Error when findAll", e);
        }
        List<Cats> tmpFriendsForCat = new ArrayList<>();
        for (FriendshipCats ship : tmpFriendshipCats) {
            if (ship.getFirstCatId() == id)
                try {
                    tmpFriendsForCat.add(daoCat.getById(ship.getSecondCatId()));
                } catch (DAOException e) {
                    throw new ShelterServiceException("Error when adding", e);
                }
            if (ship.getSecondCatId() == id) {
                try {
                    tmpFriendsForCat.add(daoCat.getById(ship.getFirstCatId()));
                } catch (DAOException e) {
                    throw new ShelterServiceException("Error when adding", e);
                }
            }
        }

        return tmpFriendsForCat;
    }

    public List<Cats> getListCatsForOwner(long id) throws ShelterServiceException {
        List<OwnershipCats> tmpOwnershipCats = null;
        try {
            tmpOwnershipCats = daoOwnShip.findAll();
        } catch (dao.tools.DAOException e) {
            throw new ShelterServiceException("Error when findAll", e);
        }
        List<Cats> tmpCatsForOwner = new ArrayList<>();
        for (OwnershipCats ship : tmpOwnershipCats) {
            if (ship.getOwnerId() == id)
                try {
                    tmpCatsForOwner.add(daoCat.getById(ship.getCatId()));
                } catch (dao.tools.DAOException e) {
                    throw new ShelterServiceException("Error when adding", e);
                }
        }
        return tmpCatsForOwner;
    }
}
