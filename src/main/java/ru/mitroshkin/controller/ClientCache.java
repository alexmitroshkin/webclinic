package ru.mitroshkin.controller;

import ru.mitroshkin.model.Client;
import ru.mitroshkin.model.pet.Pet;

import java.util.Collection;

/**
 * Created by alex on 23.08.2016.
 */
public class ClientCache{
    private static final ClientCache INSTANCE = new ClientCache();

    private final Storage storage = new HibernateStorage();

    private ClientCache(){
    }

    public static ClientCache getInstance() {
        return INSTANCE;
    }

    public Collection<Client> listClients() {
        return storage.listClients();
    }

    public int addClient(final Client client) {
        return storage.addClient(client);
    }

    public void editClient(final Client client) {
        storage.editClient(client);
    }

    public void deleteClientById(final int id) {
        storage.deleteClientById(id);
    }

    public Client getClientById(final int id) {
        return storage.getClientById(id);
    }

    public Client findClientByName(final String name) {
        return storage.findClientByName(name);
    }

    public void addPetToClient(final Client client, final Pet pet) {
        storage.addPetToClient(client, pet);
    }

    public Collection<Pet> getClientPets(final int id) {
        return storage.getClientPets(id);
    }

    public void close() {
        storage.close();
    }
}
