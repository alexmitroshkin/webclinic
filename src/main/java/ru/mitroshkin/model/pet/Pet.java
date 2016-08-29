package ru.mitroshkin.model.pet;

import ru.mitroshkin.model.Client;


public class Pet {

    private int id;

    private Client client;

    private Type type;

    private String name;

    private int age;

    public Pet() {
    }

    public Pet(Client client, Type type, String name, int age) {
        this.client = client;
        this.type = type;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        String year = "";
        if (getAge()==1){
            year = "год";
        }else if (getAge()>=2 && getAge()<=4){
            year = "года";
        }else {
            year = "лет";
        }
        return String.format("%s: %s, Возраст: %d %s", getType(), getName(), getAge(), year);
    }
}
