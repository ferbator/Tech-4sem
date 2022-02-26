package clientServices;

import tools.CentralBankException;

public class ClientBuilder {
    private String name;
    private String surname;
    private String address;
    private String passport;

    public ClientBuilder addName(String name) throws CentralBankException {
        if ((name == null) || (name.isBlank())) {
            throw new CentralBankException("Incorrect name");
        }
        this.name = name;
        return this;
    }

    public ClientBuilder addSurname(String surname) throws CentralBankException {
        if ((surname == null) || (surname.isBlank())) {
            throw new CentralBankException("Incorrect surname");
        }
        this.surname = surname;
        return this;
    }

    public ClientBuilder addAddress(String address) throws CentralBankException {
        if ((address == null) || (address.isBlank())) {
            throw new CentralBankException("Incorrect address");
        }
        this.address = address;

        return this;
    }

    public ClientBuilder addPassport(String passport) throws CentralBankException {
        if ((passport == null) || (passport.isBlank())) {
            throw new CentralBankException("Incorrect passport");
        }
        this.passport = passport;
        return this;
    }

    public Client getClient() throws CentralBankException {
        return new Client(name, surname, address, passport);
    }
}
