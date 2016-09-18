package ru.mitroshkin.model;

import org.junit.Test;
import ru.mitroshkin.model.pet.Pet;
import ru.mitroshkin.model.pet.Type;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alex on 18.09.2016.
 */
public class ClientTest {
    Client client = new Client(1,"Вася",new ArrayList<Pet>());
    Pet pet = new Pet(client, Type.Собака,"Барсик",2);

    @Test
    public void hashCodeTest() throws Exception {
        client.addPet(pet);
        Client client2 = new Client(2,"Вася",new ArrayList<Pet>());
        client2.addPet(pet);
        assertTrue(client.hashCode()==client2.hashCode());
    }

    @Test
    public void equalsTest() throws Exception {
        client.addPet(pet);
        Client client2 = new Client(2,"Вася",new ArrayList<Pet>());
        client2.addPet(pet);
        Client client3 = new Client(3,"Вася",new ArrayList<Pet>());
        client3.addPet(pet);
        assertTrue(client.equals(client));
        assertTrue(client2.equals(client) && client.equals(client2));
        assertTrue(client.equals(client2) && client2.equals(client3) && client3.equals(client));
        assertTrue(client.equals(client2));

    }

    @Test
    public void toStringTest() throws Exception {
        client.addPet(pet);
        String s = client.toString();
        assertEquals(s,client.getFullName()+" с питомцами: "+ client.getStringPets());
    }

    @Test
    public void addPet() throws Exception {
        client.addPet(pet);
        assertEquals(pet, client.getPets().get(0));
    }

    @Test
    public void removePet() throws Exception {
        client.removePet(pet);
        assertEquals(0,client.getPets().size());
    }

    @Test
    public void hasPetWithName() throws Exception {
        client.addPet(new Pet(client,Type.Собака,"Барбос",3));
        assertTrue(client.hasPetWithName("Барбос"));
    }

    @Test
    public void findPetsByName() throws Exception {
        client.addPet(pet);
        List<Pet> find = client.findPetsByName("Барсик");
        assertEquals(find,client.getPets());
    }

    @Test
    public void changePetName() throws Exception {
        client.addPet(pet);
        client.changePetName(pet,"Новый Барсик");
        assertTrue(client.hasPetWithName("Новый Барсик"));
    }
}