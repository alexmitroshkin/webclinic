package ru.mitroshkin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.mitroshkin.model.Client;

import java.util.List;

/**
 * Created by alex on 28.08.2016.
 */
public class Start {
    private static SessionFactory factory;
    public static void main(String[] args) {

        System.out.println(1);
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();
        List<Client> clients = null;
        try {
             clients = session.createQuery("from Client").list();
        }finally {
            transaction.commit();
            session.close();
        }

        for (Client client: clients){
            System.out.println(client.toString());
        }


    }
}
