package com.foxminded.university.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectionFactory {
    private final static SessionFactory session = buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return session;
    }

    private static SessionFactory buildSessionFactory() {
        SessionFactory currentSessionFactory = new Configuration().configure().buildSessionFactory();
        return currentSessionFactory;
    }
}
