package ru.mitroshkin.model.pet;

import org.junit.Test;
import ru.mitroshkin.model.Client;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by alex on 18.09.2016.
 */
public class PetTest {

    @Test
    public void equalsTest() throws Exception {
        Client client = new Client(1,"",new ArrayList<Pet>());
        Pet pet = new Pet(client,Type.Кошка,"Мурка",2);
        Pet pet2 = new Pet(client,Type.Кошка,"Мурка",2);
        Pet pet3 = new Pet(client,Type.Кошка,"Мурка",2);
        assertTrue(pet.equals(pet));
        assertTrue(pet.equals(pet2) && pet2.equals(pet));
        assertTrue(pet.equals(pet2) && pet2.equals(pet3) && pet3.equals(pet));
    }

}