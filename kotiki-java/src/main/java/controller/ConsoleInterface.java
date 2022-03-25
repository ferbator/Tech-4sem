package controller;

import dao.daoImpl.CatsDAO;
import dao.daoImpl.FriendshipCatsDAO;
import dao.daoImpl.OwnersDAO;
import dao.daoImpl.OwnershipCatsDAO;
import dao.daoInterface.DAO;
import dao.entities.Cats;
import dao.entities.FriendshipCats;
import dao.entities.Owners;
import dao.entities.OwnershipCats;
import dao.enums.Colors;
import services.ShelterService;
import services.tools.ShelterServiceException;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleInterface {
    private ShelterService service;

    public ConsoleInterface() {
        DAO<Owners> daoOwn = new OwnersDAO();
        DAO<Cats> daoCat = new CatsDAO();
        DAO<OwnershipCats> daoOwnShip = new OwnershipCatsDAO();
        DAO<FriendshipCats> daoFriendShip = new FriendshipCatsDAO();
        this.service = new ShelterService(daoOwn, daoCat, daoOwnShip, daoFriendShip);
    }

    public void input() throws ShelterServiceException {
        String str = null;
        while (!Objects.equals(str, "Q")) {
            preamble();
            Scanner in = new Scanner(System.in);
            str = in.nextLine();
            switch (str) {
                case "1" -> {
                    createOwner();
                }
                case "2" ->{
                    createCat();
                }
                case "3" ->{
                    deleteOwner();
                }
                case "4" ->{
                    deleteCat();
                }
                case "5" ->{
                    startOwnership();
                }
                case "6" ->{
                    cancelOwnership();
                }
                case "7" ->{
                    startFriendship();
                }
                case "8" ->{
                    cancelFriendship();
                }
            }
        }
    }

    private void preamble() {
        System.out.println("1 - Start Create Owner");
        System.out.println("2 - Start Create Cat");
        System.out.println("3 - Delete Owner by id");
        System.out.println("4 - Delete Cat by id");
        System.out.println("5 - Start Ownership by id of Owner and id Cat");
        System.out.println("6 - Cancel Ownership by id of Owner and id Cat");
        System.out.println("7 - Start Friendship by id of Cats");
        System.out.println("8 - Cancel Friendship by id of Cats");
        System.out.println("Q - Exit Program");
    }

    private void createOwner() throws ShelterServiceException {
        Scanner in = new Scanner(System.in);
        System.out.println("Start create");
        System.out.println("Set name");
        String name = in.nextLine();
        System.out.println("Set birthday example:2002-01-12");
        String birth = in.nextLine();
        service.addOwnerToBase(name, birth + " 00:00:00");
    }

    private void createCat() throws ShelterServiceException {
        Scanner in = new Scanner(System.in);
        System.out.println("Start create");
        System.out.println("Set name");
        String name = in.nextLine();
        System.out.println("Colors{\n 1 - Black,\n" +
                "2 - Red,\n" +
                "3 - Orange,\n" +
                "4 - Blue}");
        Colors tmpColor = null;
        switch (in.nextLine()) {
            case "1" -> {
                tmpColor = Colors.Black;
            }
            case "2" -> {
                tmpColor = Colors.Red;
            }
            case "3" -> {
                tmpColor = Colors.Orange;
            }
            case "4" -> {
                tmpColor = Colors.Blue;
            }
        }
        System.out.println("Set birthday example:2002-01-12");
        String birth = in.nextLine();
        System.out.println("Set breed");
        String breed = in.nextLine();
        service.addCatToBase(name, tmpColor, breed, birth + " 00:00:00");
    }

    private void deleteOwner() throws ShelterServiceException{
        Scanner in = new Scanner(System.in);
        System.out.println("Start delete");
        System.out.println("Set id");
        long id = in.nextLong();
        service.delOwnerFromBase(id);
    }

    private void deleteCat() throws ShelterServiceException{
        Scanner in = new Scanner(System.in);
        System.out.println("Start delete");
        System.out.println("Set id");
        long id = in.nextLong();
        service.delCatFromBase(id);
    }

    private void startOwnership() throws ShelterServiceException{
        Scanner in = new Scanner(System.in);
        System.out.println("Start ownership");
        System.out.println("Set id owner");
        long id1 = in.nextLong();
        System.out.println("Set id cat");
        long id2 = in.nextLong();
        service.startСatOwnership(id1, id2);
    }

    private void cancelOwnership() throws ShelterServiceException{
        Scanner in = new Scanner(System.in);
        System.out.println("Cancel ownership");
        System.out.println("Set id owner");
        long id1 = in.nextLong();
        System.out.println("Set id cat");
        long id2 = in.nextLong();
        service.cancelCatOwnership(id1, id2);
    }

    private void startFriendship() throws ShelterServiceException{
        Scanner in = new Scanner(System.in);
        System.out.println("Start friendship");
        System.out.println("Set id first cat");
        long id1 = in.nextLong();
        System.out.println("Set id second cat");
        long id2 = in.nextLong();
        service.startСatFriendship(id1, id2);
    }

    private void cancelFriendship() throws ShelterServiceException{
        Scanner in = new Scanner(System.in);
        System.out.println("Cancel friendship");
        System.out.println("Set id first cat");
        long id1 = in.nextLong();
        System.out.println("Set id second cat");
        long id2 = in.nextLong();
        service.cancelCatFriendship(id1, id2);
    }
}
