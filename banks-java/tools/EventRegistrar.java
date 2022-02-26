package tools;

import clientServices.Client;

import java.util.ArrayList;
import java.util.HashMap;

public class EventRegistrar {
    HashMap<String, ArrayList<Client>> listeners = new HashMap<>();

    public EventRegistrar(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, Client listener) {
        ArrayList<Client> users = listeners.get(eventType);
        users.add(listener);
    }

    public void subscribeAll(Client listener) {
        for (ArrayList<Client> clients : listeners.values())
            clients.add(listener);
    }

    public void unsubscribe(String eventType, Client listener) {
        ArrayList<Client> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType) {
        ArrayList<Client> users = listeners.get(eventType);
        for (Client listener : users) {
            Client.update(eventType);
        }
    }
}
