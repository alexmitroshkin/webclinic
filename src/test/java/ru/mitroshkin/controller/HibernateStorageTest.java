package ru.mitroshkin.controller;

import org.junit.Test;
import ru.mitroshkin.model.Client;
import ru.mitroshkin.model.pet.Pet;
import ru.mitroshkin.model.pet.Type;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by alex on 03.09.2016.
 */
public class HibernateStorageTest {
    Storage storage = new HibernateStorage();
    static  int id;
    @Test
    public void add() throws Exception {
        Client client = new Client();
        Pet pet = new Pet(client, Type.Кошка, "Мурзик", 1);
        client.setFullName("Яков");
        ArrayList<Pet> pets = new ArrayList<>();
        pets.add(pet);
        client.setPets(pets);
        id = storage.addClient(client);
        assertEquals(id, storage.getClientById(id).getId());
    }

    @Test
    public void delete() throws Exception {
        int id = storage.listClients().size();
        storage.deleteClientById(id);
        assertEquals(null, storage.getClientById(id));
    }

}