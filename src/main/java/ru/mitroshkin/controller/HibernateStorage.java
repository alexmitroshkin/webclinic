package ru.mitroshkin.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.mitroshkin.model.Client;
import ru.mitroshkin.model.pet.Pet;

import java.util.Collection;
import java.util.List;

/**
 * Created by alex on 28.08.2016.
 */
public class HibernateStorage implements Storage {

    private final SessionFactory factory;

    public HibernateStorage(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Collection<Client> values() {

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
    public int add(Client client) {
        Session sesion = factory.openSession();
        Transaction tx = null;
        int id = 0;
        try {
            tx = sesion.beginTransaction();
            id = (int) sesion.save(client);
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
    public void edit(Client client) {
        //TODO
    }

    @Override
    public void delete(int id) {
        Transaction tx = null;
        try (Session sesion = factory.openSession()){
            tx = sesion.beginTransaction();
            Client client = get(id);
            sesion.delete(client);
            tx.commit();
        }catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Client get(int id) {
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
    public Client findByName(String name) {
        return null;
    }

    @Override
    public void addPet(Client client, Pet pet) {
//        Transaction tx = null;
//        try(Session sesion = factory.openSession();) {
//            tx = sesion.beginTransaction();
//            tx.commit();
//        } catch (HibernateException e){
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//        }

    }

    @Override
    public Collection<Pet> getPets(int id) {
        List<Pet> pets = null;
        Client client = null;
        client = get(id);
        pets = client.getPets();
        return pets;
    }

    @Override
    public void close() {
        this.factory.close();
    }
}
