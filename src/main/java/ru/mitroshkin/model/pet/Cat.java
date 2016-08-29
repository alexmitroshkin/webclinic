package ru.mitroshkin.model.pet;


import ru.mitroshkin.model.Client;

import javax.persistence.Entity;
import javax.persistence.Table;

public class Cat extends Pet {
    public Cat() {
        super();
    }

    public Cat(Client client, Type type, String name, int age) {
        super(client, type, name, age);
    }
}
