package ru.mitroshkin.controller;

import ru.mitroshkin.model.Client;
import ru.mitroshkin.model.pet.Pet;
import ru.mitroshkin.model.pet.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by alex on 25.08.2016.
 */
public class DbStorage implements Storage {

    private Connection connection;

    public DbStorage() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/webclinic", "postgres", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Collection<Client> listClients() {
        List<Client> clients = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM client");
            getFoundClients(rs, clients);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public int addClient(Client client) {
        try(PreparedStatement insClien = this.connection.prepareStatement("INSERT INTO client(\"fullname\") VALUES(?)", Statement.RETURN_GENERATED_KEYS);
         PreparedStatement insPet = this.connection.prepareStatement("INSERT INTO pet(index, client_id, type, name, age) VALUES (?, ?, ?, ?, ?)") ) {
            insClien.setString(1,client.getFullName());
            insClien.executeUpdate();
            ResultSet rs = insClien.getGeneratedKeys();
            int id = 0;
            if (rs.next()){
                id = rs.getInt(1);
            }

            for (Pet pet: client.getPets()) {
                insPet.setInt(1,client.getPets().indexOf(pet));
                insPet.setInt(2,id);
                insPet.setInt(3,pet.getType().ordinal());
                insPet.setString(4, pet.getName());
                insPet.setInt(5,pet.getAge());
                insPet.executeUpdate();
            }
            return id;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Не получилось создать клиента.");
    }

    @Override
    public void editClient(Client client) {
        //TODO
    }

    @Override
    public void deleteClientById(int id) {
        try(PreparedStatement deleteFromClient = this.connection.prepareStatement("DELETE FROM client WHERE uid=?");
        PreparedStatement deleteFromPet = this.connection.prepareStatement("DELETE FROM  pet WHERE client_id=?")) {
            deleteFromClient.setInt(1,id);
            deleteFromPet.setInt(1,id);
            deleteFromPet.executeUpdate();
            deleteFromClient.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Client getClientById(int id) {
        Client client = new Client();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM client WHERE uid = ?");
             PreparedStatement getPetsStatment = connection.prepareStatement("SELECT * FROM pet WHERE client_id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String fullName = resultSet.getString("fullname");
                client.setId(id);
                client.setFullName(fullName);
                List<Pet> pets = new ArrayList<>();
                getPetsStatment.setInt(1,id);
                ResultSet rs = getPetsStatment.executeQuery();
                while (rs.next()){

                    int petId = rs.getInt("uid");
                    int type = rs.getInt("type");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    Pet pet = new Pet(client, Type.values()[type], name, age);
                    pets.add(pet);
                }
                client.setPets(pets);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public List<Client> findClientByName(String name) {
        return null;
    }

    @Override
    public void addPetToClient(Client client, Pet pet) {
        int client_id = client.getId();
        try(PreparedStatement ps = this.connection.prepareStatement("INSERT INTO pet(index, client_id, type, name, age) VALUES (?, ?, ?, ?, ?)")) {
            ps.setInt(1, client.getPets().size());
            ps.setInt(2, client_id);
            ps.setInt(3, pet.getType().ordinal());
            ps.setString(4, pet.getName());
            ps.setInt(5, pet.getAge());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Pet> getClientPets(int clientId) {
        List<Pet> petList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM pet WHERE client_id = ?")) {
            statement.setInt(1, clientId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("uid");
                int type = rs.getInt("type");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                Pet pet = new Pet(getClientById(clientId), Type.values()[type], name, age);
                petList.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return petList;
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getFoundClients(ResultSet resultSet, List<Client> clients) throws SQLException {
        while (resultSet.next()) {
            Client client = new Client();
            int id = resultSet.getInt("uid");
            client.setId(id);
            client.setFullName(resultSet.getString("fullname"));
            List<Pet> pets = (List) getClientPets(id);
            client.setPets(pets);
            clients.add(client);
        }
    }
}
