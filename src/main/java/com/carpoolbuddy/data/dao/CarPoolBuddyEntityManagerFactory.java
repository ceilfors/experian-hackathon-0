package com.carpoolbuddy.data.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created with IntelliJ IDEA.
 * User: alexus
 * Date: 11/3/13
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class CarPoolBuddyEntityManagerFactory {

    private static CarPoolBuddyEntityManagerFactory instance;
    private static EntityManagerFactory emf;

    private CarPoolBuddyEntityManagerFactory() {}

    public static CarPoolBuddyEntityManagerFactory getInstance() {
        if (instance == null) {
            instance = new CarPoolBuddyEntityManagerFactory();
        }
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("CarpoolBuddy");
        }
        return instance;
    }

    public EntityManager makeEntityManager() {
        EntityManager em = emf.createEntityManager();
        return em;
    }
}
