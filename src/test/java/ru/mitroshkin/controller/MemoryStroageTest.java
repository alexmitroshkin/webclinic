package ru.mitroshkin.controller;

import org.junit.Test;
import ru.mitroshkin.model.Client;
import ru.mitroshkin.model.pet.Pet;
import ru.mitroshkin.model.pet.Type;

import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alex on 29.08.2016.
 */
public class MemoryStroageTest {
    Storage stroage = new MemoryStroage();

    @Test
    public void add2ClientReturnSize(){
        Client client1 = new Client("Alex");
        client1.setId(0);

        Client client2 = new Client("Vasya");
        client2.setId(1);

        int id1 = stroage.add(client1);
        int id2 = stroage.add(client2);

        assertEquals(stroage.values().size(),2);
        assertEquals(id1, client1.getId());
        assertEquals(id2, client2.getId());
    }

    @Test
    public void getClient(){
        Client client = new Client("Alex");
        stroage.add(client);
        Client client1 = stroage.get(0);
        assertEquals(client,client1);
    }

    @Test
    public void deleteClient(){
        Client client = new Client("Alex");
        client.setId(0);
        Client client1 = new Client("Boris");
        client1.setId(1);
        stroage.add(client);
        stroage.add(client1);
        assertEquals(2, stroage.values().size());
        stroage.delete(1);
        assertEquals(1, stroage.values().size());
    }

    @Test
    public void findClient(){
        Client client = new Client("Alex");
        client.setId(0);
        Client client1 = new Client("Boris");
        client1.setId(1);

        stroage.add(client);
        stroage.add(client1);

        Client find = stroage.findByName("Alex");
        assertEquals(find, client);
    }

    @Test
    public void addPet(){
        Client client = new Client("Alex");
        client.setId(0);
        List<Pet> pets = new ArrayList<>();
        Pet pet1 = new Pet(client, Type.Кошка, "Barsik",10);
        pet1.setId(0);
        pets.add(pet1);
        client.setPets(pets);
        stroage.add(client);

        Pet pet2 = new Pet(client, Type.Собака, "Рекс",2);
        stroage.addPet(client, pet2);

        assertEquals(2, client.getPets().size());
    }
}