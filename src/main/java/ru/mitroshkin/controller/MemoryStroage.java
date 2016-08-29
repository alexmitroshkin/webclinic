package ru.mitroshkin.controller;

import ru.mitroshkin.model.Client;
import ru.mitroshkin.model.pet.Pet;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by alex on 25.08.2016.
 */
public class MemoryStroage implements Storage {
    private  final ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<Integer,Client>();

    @Override
    public Collection<Client> values() {
        return clients.values();
    }

    @Override
    public int add(final Client client) {
        clients.put(client.getId(),client);
        return client.getId();
    }

    @Override
    public void edit(final Client client) {
        this.clients.replace(client.getId(), client);
    }

    @Override
    public void delete(final int id) {
        this.clients.remove(id);
    }

    @Override
    public Client get(final int id) {
        return clients.get(id);
    }

    @Override
    public Client findByName(final String name) {
        for (final Client client: clients.values()){
            if (name.equals(client.getFullName()))
                return client;
        }
        throw new IllegalStateException(String.format("Клиент с именем %s не найден", name));
    }

    @Override
    public void addPet(final Client client, final Pet pet) {
        client.addPet(pet);
    }

    @Override
    public Collection<Pet> getPets(final int id) {
        return clients.get(id).getPets();
    }

    @Override
    public void close() {
    }
}
