package ru.mitroshkin.controller;

import ru.mitroshkin.model.Client;
import ru.mitroshkin.model.pet.Pet;

import java.util.Collection;

/**
 * Created by alex on 25.08.2016.
 */
interface Storage {

    /**
     * Получить список всех клиентов
     * @return
     */
    Collection<Client> listClients();

    /**
     * Добавить клиента
     * @param client
     * @return
     */
    int addClient(Client client);

    /**
     * Изменить клиента
     * @param client
     */
    void editClient(Client client);


    /**
     * Удалить клиента по id
     * @param id
     */
    void deleteClientById(int id);

    /**
     * Получить клиента по id
     * @param id
     * @return
     */
    Client getClientById(int id);

    /**
     * Поиск клиента по имени
     * @param name
     * @return
     */
    Client findClientByName(String name);

    /**
     * Добавление питомца клиенту
     * @param client
     * @param pet
     */
    void addPetToClient(Client client, Pet pet);

    /**
     * Получить всех питомцев клиента
     * @param id
     * @return
     */
    Collection<Pet> getClientPets(int id);

    void close();
}
