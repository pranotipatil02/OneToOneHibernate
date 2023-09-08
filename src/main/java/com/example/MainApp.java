package com.example;

import com.example.model.Address;
import com.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Create a user and an address
        User user = new User();
        user.setUsername("JohnDoe");

        Address address = new Address();
        address.setStreet("123 Main St");

        // Establish the one-to-one association
        user.setAddress(address);
        address.setUser(user);

        // Save the user and address
        session.save(user);

        session.getTransaction().commit();

        // Retrieve user and address
        User retrievedUser = session.get(User.class, user.getId());
        Address retrievedAddress = retrievedUser.getAddress();

        System.out.println("User: " + retrievedUser.getUsername());
        System.out.println("Address: " + retrievedAddress.getStreet());

        session.close();
        sessionFactory.close();
    }
}
