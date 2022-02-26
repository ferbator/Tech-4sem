package clientServices;

import accountServices.IAccount;
import banksServices.Bank;
import tools.CentralBankException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class Client {
    private HashMap<Bank, ArrayList<IAccount>> clientCollectionAccounts;
    private UUID id;
    private String name;
    private String surname;
    private String address;
    private String passport;
    private boolean isAllInfo;

    public Client(String name, String surname, String address, String passport) throws CentralBankException {
        this.id = UUID.randomUUID();
        if (name.isBlank()) {
            throw new CentralBankException("Incorrect name");
        }
        this.name = name;
        if (surname.isBlank()) {
            throw new CentralBankException("Incorrect surname");
        }
        this.surname = surname;
        this.address = address;
        this.passport = passport;
        this.clientCollectionAccounts = new HashMap<Bank, ArrayList<IAccount>>();
        isAllInfo = this.address != null && this.passport != null && !this.address.isBlank() && !this.passport.isBlank();
    }

    public static void update(String other) {
        System.out.print(other);
    }

    public static ClientBuilder Builder(String name, String surname) throws CentralBankException {
        return new ClientBuilder().addName(name).addSurname(surname);
    }

    public boolean getVerification() {
        return isAllInfo;
    }


    public void createAccount(Bank bank, ArrayList<IAccount> accounts) {
        if (!clientCollectionAccounts.containsKey(bank))
            clientCollectionAccounts.put(bank, accounts);
        else {
            clientCollectionAccounts.get(bank).addAll(accounts);
        }
    }
}
