package controller;

import services.tools.ShelterServiceException;

public class Main {
    public static void main(String[] args) throws ShelterServiceException {
        ConsoleInterface cons = new ConsoleInterface();
        cons.input();
    }
}
