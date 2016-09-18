package ru.mitroshkin.model;

import ru.mitroshkin.model.pet.Pet;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "client")
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "uid")
    private int id;
    @Column (name = "fullName")
    private String fullName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "client", orphanRemoval = true)
    private List<Pet> pets;

    public Client(){
    }

    public Client(int id, String fullName, List<Pet> pets) {
        this.id = id;
        this.fullName = fullName;
        this.pets = pets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    /**
     * Добавление питомца
     * @param pet
     */
    public void addPet(Pet pet){
        if (this.pets.contains(pet)){
            //TODO: бросить исключение или др.
        }else {
            this.pets.add(pet);
        }
    }

    /**
     * Удаление питомца
     * @param pet
     */
    public void removePet(Pet pet){
        if (this.pets.contains(pet)){
            this.pets.remove(pet);
        }else {
            //TODO: ситуаця отсутствия данного питомца
        }
    }

    /**
     * Проверка существует ли питомец с именем: petName
     * @param petName
     * @return
     */
    public boolean hasPetWithName(String petName){
        boolean res = false;
        for (Pet pet: pets){
            if (petName.equals(pet.getName())) res = true;
        }

        return res;
    }

    /**
     * Получить питомцев клиента с переданным именем
     * @param namePet
     * @return
     */
    public List<Pet> findPetsByName(String namePet){
        List<Pet> findPets = new ArrayList<Pet>();
        for (Pet pet: pets){
            if (hasPetWithName(namePet)){
                findPets.add(pet);
            }
        }
        return findPets;
    }

    /**
     * Изменить имя питомца
     * @param pet
     * @param newPetName
     */
    public void changePetName(Pet pet, String newPetName){
        if (pets.contains(pet)){
            int petID = pets.indexOf(pet);
            pets.get(petID).setName(newPetName);
        }else {
            //TODO: исключение по поводу отсутсвия питомца
        }
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result + (fullName != null ? fullName.hashCode() : 0);
        result = prime * result + (pets != null ? pets.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Client client = (Client) obj;
        if (!this.fullName.equals(client.fullName)) return false;
        if (!this.pets.equals(client.pets)) return false;
        return true;
    }

    @Override
    public String toString() {
        String pets = getStringPets();
        return String.format("%s с питомцами: %s", getFullName(), pets);
    }

    public String getStringPets() {
        String result = "";
        for (Pet pet: pets){
            result += "\n \t \t" + pet.toString();
        }
        return result;
    }
}
