package ru.mitroshkin.controller;

import ru.mitroshkin.model.Client;
import ru.mitroshkin.model.pet.Pet;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by alex on 23.08.2016.
 */
public class ClientCache{
    private static final ClientCache INSTANCE = new ClientCache();

//    private  final Storage storage = new MemoryStroage();
//    private  final Storage storage = new DbStorage();
    private final Storage storage = new HibernateStorage();

    private ClientCache(){
    }

    public static ClientCache getInstance() {
        return INSTANCE;
    }

    public Collection<Client> values() {
        return storage.values();
    }

    public int add(final Client client) {
        return storage.add(client);
    }

    public void edit(final Client client) {
        storage.edit(client);
    }

    public void delete(final int id) {
        storage.delete(id);
    }

    public Client get(final int id) {
        return storage.get(id);
    }

    public Client findByName(final String name) {
        return storage.findByName(name);
    }

    public void addPet(final Client client, final Pet pet) {
        storage.addPet(client, pet);
    }

    public Collection<Pet> getPets(final int id) {
        return storage.getPets(id);
    }

    public void close() {
        storage.close();
    }
}
