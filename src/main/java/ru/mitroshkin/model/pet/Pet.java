package ru.mitroshkin.model.pet;

import ru.mitroshkin.model.Client;

import javax.persistence.*;

@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "uid")
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private Type type;
    @Column (name = "name")
    private String name;
    @Column (name = "age")
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

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result + (name != null ? name.hashCode() : 0);
        result = prime * result + (type != null ? type.hashCode() : 0);
        result = prime * result + (this.age);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.hashCode() != obj.hashCode()) return false;
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pet pet = (Pet) obj;
        if (!this.type.equals(pet.type)) return false;
        if (this.age != pet.age) return false;
        if (!this.client.equals(pet.client)) return false;
        return true;
    }
}
