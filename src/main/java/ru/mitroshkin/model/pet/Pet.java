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
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (this != null ? client.hashCode() : 0);
        result = 31 * result + (this != null ? this.type.hashCode() : 0);
        result = 31 * result + (this != null ? this.age : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Pet)){
            return false;
        }
        Pet pet = (Pet) obj;
        if (name != null ? name.equals(pet.name) : pet.name != null) return false;
        return !(client != null  ? !client.equals(pet.client) : pet.client != null);
    }
}
