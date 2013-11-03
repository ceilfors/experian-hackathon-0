package com.carpoolbuddy.data.dao;

import com.carpoolbuddy.data.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alexus
 * Date: 11/3/13
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersonDataHandler {

    public boolean createPerson(final Person person) {
        EntityManager entityManager = CarPoolBuddyEntityManagerFactory.getInstance().makeEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        boolean persisted;
        try {
            tx.begin();
            entityManager.persist(person);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            persisted = entityManager.contains(person);
            entityManager.close();
        }
        return persisted;
    }

    public Person findPersonByFacebookId(final String facebookId) {
        Person persons;
        EntityManager entityManager = CarPoolBuddyEntityManagerFactory.getInstance().makeEntityManager();

        Query query = entityManager.createQuery("select p from Person p where p.facebookId = :facebookId");
        query.setParameter("facebookId", facebookId);
        persons = (Person) query.getSingleResult();

        entityManager.close();

        return persons;
    }
}
