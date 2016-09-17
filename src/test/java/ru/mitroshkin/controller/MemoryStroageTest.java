package ru.mitroshkin.controller;

import org.junit.Test;
import ru.mitroshkin.model.Client;
import ru.mitroshkin.model.pet.Pet;
import ru.mitroshkin.model.pet.Type;

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
        Client client1 = new Client(0,"Alex", null);

        Client client2 = new Client(1, "Vasya", null);

        int id1 = stroage.addClient(client1);
        int id2 = stroage.addClient(client2);

        assertEquals(stroage.listClients().size(),2);
        assertEquals(id1, client1.getId());
        assertEquals(id2, client2.getId());
    }

    @Test
    public void getClient(){
        Client client = new Client(0,"Alex",null);
        stroage.addClient(client);
        Client client1 = stroage.getClientById(0);
        assertEquals(client,client1);
    }

    @Test
    public void deleteClient(){
        Client client = new Client(0,"Alex",null);
        Client client1 = new Client(1,"Boris",null);
        stroage.addClient(client);
        stroage.addClient(client1);
        assertEquals(2, stroage.listClients().size());
        stroage.deleteClientById(1);
        assertEquals(1, stroage.listClients().size());
    }

    @Test
    public void findClient(){
        Client client = new Client(0,"Alex",null);
        Client client1 = new Client(1,"Boris",null);

        stroage.addClient(client);
        stroage.addClient(client1);

        Client find = stroage.findClientByName("Alex");
        assertEquals(find, client);
    }

    @Test
    public void addPet(){
        Client client = new Client(0, "Alex", null);
        List<Pet> pets = new ArrayList<>();
        Pet pet1 = new Pet(client, Type.Кошка, "Barsik",10);
        pet1.setId(0);
        pets.add(pet1);
        client.setPets(pets);
        stroage.addClient(client);

        Pet pet2 = new Pet(client, Type.Собака, "Рекс",2);
        stroage.addPetToClient(client, pet2);

        assertEquals(2, client.getPets().size());
    }
}