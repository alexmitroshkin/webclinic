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
    Collection<Client> values();

    /**
     * Добавить клиента
     * @param client
     * @return
     */
    int add(Client client);

    /**
     * Изменить клиента
     * @param client
     */
    void edit(Client client);


    /**
     * Удалить клиента по id
     * @param id
     */
    void delete(int id);

    /**
     * Получить клиента по id
     * @param id
     * @return
     */
    Client get(int id);

    /**
     * Поиск клиента по имени
     * @param name
     * @return
     */
    Client findByName(String name);

    /**
     * Добавление питомца клиенту
     * @param client
     * @param pet
     */
    void addPet(Client client, Pet pet);

    /**
     * Получить всех питомцев клиента
     * @param id
     * @return
     */
    Collection<Pet> getPets(int id);

    void close();

    //    /**
//     * Создать id для добаления клиента
//     * @return
//     */
//    int generateId();
}
