package ru.mitroshkin.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.mitroshkin.model.Client;
import ru.mitroshkin.model.pet.Pet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HibernateStorage implements Storage {

    private final SessionFactory factory;

    public HibernateStorage(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Collection<Client> listClients() {
        List<Client> clients = null;
        Session session = factory.openSession();
        Transaction tx = null                ;
        try {
            tx = session.beginTransaction();
            clients = session.createQuery("from Client").list();
            tx.commit();
        }catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return clients;
    }

    @Override
    public int addClient(Client client) {
        Session sesion = factory.openSession();
        Transaction tx = null;
        int id = 0;
        try {
            tx = sesion.beginTransaction();
            sesion.save(client);
            id = client.getId();
            tx.commit();
        } catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            sesion.close();
        }
        return id;
    }

    @Override
    public void editClient(Client client) {
        Transaction tx = null;
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(client);
            tx.commit();
        }catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClientById(int id) {
        Transaction tx = null;
        try (Session sesion = factory.openSession()){
            tx = sesion.beginTransaction();
            Client client = getClientById(id);
            sesion.delete(client);
            tx.commit();
        }catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Client getClientById(int id) {
        Transaction tx = null;
        Client client = null;
        try (Session sesion = factory.openSession()){
            tx = sesion.beginTransaction();
            client = sesion.get(Client.class, id);
            tx.commit();
        }catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public List<Client> findClientByName(String name) {
        List<Client> findClients = null;
        Transaction tx = null;
        try(Session session = factory.openSession();) {
            tx = session.beginTransaction();
            Query query = session.createQuery("select c from Client c  where c.fullName like :name ");
            query.setParameter("name", "%"+name+"%");
            findClients = query.list();
            tx.commit();
        }catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return findClients;
    }

    @Override
    public void addPetToClient(Client client, Pet pet) {
        Transaction tx = null;
        try(Session sesion = factory.openSession();) {
            tx = sesion.beginTransaction();
            pet.setClient(client);
            sesion.saveOrUpdate(pet);
            tx.commit();
        } catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public Collection<Pet> getClientPets(int id) {
        List<Pet> pets = null;
        Client client = null;
        client = getClientById(id);
        pets = client.getPets();
        return pets;
    }

    @Override
    public void close() {
        this.factory.close();
    }
}
