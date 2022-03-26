package services;
import dao.daoInterface.DAO;
import dao.entities.Cat;
import dao.entities.FriendshipCat;
import dao.entities.Owners;
import dao.entities.OwnershipCat;
import dao.enums.Colors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.tools.ShelterServiceException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ShelterServiceTest {
    @Mock
    private DAO<Owners> daoOwn;
    @Mock
    private DAO<Cat> daoCat;
    @Mock
    private DAO<OwnershipCat> daoOwnShip;
    @Mock
    private DAO<FriendshipCat> daoFriendShip;

    private Owners own;
    private Cat cat1;
    private Cat cat2;
    private OwnershipCat ownAndCat1;
    private OwnershipCat ownAndCat2;
    private FriendshipCat cat1AndCat2;

    private ShelterService service;

    @BeforeEach
    void setUp() {
        own = new Owners();
        own.setName("sock");
        own.setBirthday(Timestamp.valueOf("2002-01-12 00:00:00"));

        cat1 = new Cat();
        cat1.setName("Boris");
        cat1.setColor(Colors.Black);
        cat1.setBirthday(Timestamp.valueOf("2002-01-12 00:00:00"));
        cat1.setBreed("breed");

        cat2 = new Cat();
        cat2.setName("Stepan");
        cat2.setColor(Colors.Orange);
        cat2.setBirthday(Timestamp.valueOf("2002-01-12 00:00:00"));
        cat2.setBreed("breed");

        ownAndCat1 = new OwnershipCat();
        ownAndCat1.setOwnerId(1);
        ownAndCat1.setCatId(1);

        ownAndCat2 = new OwnershipCat();
        ownAndCat2.setOwnerId(1);
        ownAndCat2.setCatId(2);

        cat1AndCat2 = new FriendshipCat();
        cat1AndCat2.setFirstCatId(1);
        cat1AndCat2.setSecondCatId(2);

    }

    ShelterServiceTest() {
        MockitoAnnotations.initMocks(this);
        this.service = new ShelterService(daoOwn, daoCat, daoOwnShip, daoFriendShip);
    }

    @Test
    void addOwnerToBase() throws ShelterServiceException {
        try {
            when(daoOwn.add(own)).thenReturn(true);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        boolean test = service.addOwnerToBase("sock", "2002-01-12 00:00:00");
        assertTrue(test);
    }

    @Test
    void addCatToBase() throws ShelterServiceException {
        try {
            when(daoCat.add(cat1)).thenReturn(true);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        boolean test = service.addCatToBase("Boris", Colors.Black,"breed","2002-01-12 00:00:00");
        assertTrue(test);
    }

    @Test
    void delOwnerFromBase() throws ShelterServiceException {
        try {
            when(daoOwnShip.del(ownAndCat1)).thenReturn(true);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        List<OwnershipCat> ownershipCats = new ArrayList<>();
        ownershipCats.add(ownAndCat1);
        try {
            when(daoOwnShip.findAll()).thenReturn(ownershipCats);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        try {
            when(daoOwn.getById(1)).thenReturn(own);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        try {
            when(daoOwn.del(own)).thenReturn(true);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        boolean test = service.delOwnerFromBase(1);
        assertTrue(test);
    }

    @Test
    void delCatFromBase() throws ShelterServiceException {
        try {
            when(daoOwnShip.del(ownAndCat1)).thenReturn(true);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        List<OwnershipCat> ownershipCats = new ArrayList<>();
        ownershipCats.add(ownAndCat1);
        try {
            when(daoOwnShip.findAll()).thenReturn(ownershipCats);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        try {
            when(daoCat.getById(1)).thenReturn(cat1);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        try {
            when(daoCat.del(cat1)).thenReturn(true);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        List<FriendshipCat> friendshipCats = new ArrayList<>();
        friendshipCats.add(cat1AndCat2);
        try {
            when(daoFriendShip.findAll()).thenReturn(friendshipCats);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        boolean test = service.delCatFromBase(1);
        assertTrue(test);
    }

    @Test
    void startСatOwnership() throws ShelterServiceException {
        try {
            when(daoOwnShip.add(ownAndCat1)).thenReturn(true);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        boolean test = service.startСatOwnership(1,1);
        assertTrue(test);
    }

    @Test
    void cancelCatOwnership() throws ShelterServiceException {
        try {
            when(daoOwnShip.del(ownAndCat1)).thenReturn(true);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        List<OwnershipCat> ownershipCats = new ArrayList<>();
        ownershipCats.add(ownAndCat1);
        try {
            when(daoOwnShip.findAll()).thenReturn(ownershipCats);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        boolean test = service.cancelCatOwnership(1,1);
        assertTrue(test);
    }

    @Test
    void startСatFriendship() throws ShelterServiceException {
        try {
            when(daoFriendShip.add(cat1AndCat2)).thenReturn(true);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        boolean test = service.startСatFriendship(1,2);
        assertTrue(test);
    }

    @Test
    void cancelCatFriendship() throws ShelterServiceException {
        try {
            when(daoFriendShip.del(cat1AndCat2)).thenReturn(true);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        List<FriendshipCat> friendshipCats = new ArrayList<>();
        friendshipCats.add(cat1AndCat2);
        try {
            when(daoFriendShip.findAll()).thenReturn(friendshipCats);
        } catch (dao.tools.DAOException e) {
            e.printStackTrace();
        }
        boolean test = service.cancelCatFriendship(1,2);
        assertTrue(test);
    }
}