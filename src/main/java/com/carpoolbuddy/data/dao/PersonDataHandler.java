package com.carpoolbuddy.data.dao;

import com.carpoolbuddy.data.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created with IntelliJ IDEA.
 * User: alexus
 * Date: 11/3/13
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersonDataHandler {

    public DaoResult createPerson(final Person person) {
        //check if the person has already been created
        if (findPersonByFacebookId(person.getFacebookId()) != null) {
            return DaoResult.RECORD_ALREADY_EXIST;
        }
        //if no, find the city from City table, and create
        CityDataHandler cityDataHandler = new CityDataHandler();
        City fromCity = cityDataHandler.findCity(person.getFrom().getName());
        if (fromCity != null) {
            person.setFrom(fromCity);
        }
        City toCity = cityDataHandler.findCity(person.getTo().getName());
        if (toCity != null) {
            person.setTo(toCity);
        }

        EntityManager entityManager = CarPoolBuddyEntityManagerFactory.getInstance().makeEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(person);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            entityManager.close();
        }

        //check if inserted
        if (findPersonByFacebookId(person.getFacebookId()) != null) {
            return DaoResult.RECORD_CREATED_SUCCESSFULLY;
        } else {
            return DaoResult.RECORD_CREATION_FAILED;
        }
    }

    public Person findPersonByFacebookId(final String facebookId) {
        Person persons;
        EntityManager entityManager = CarPoolBuddyEntityManagerFactory.getInstance().makeEntityManager();
        try {
            Query query = entityManager.createQuery("select p from Person p where p.facebookId = :facebookId");
            query.setParameter("facebookId", facebookId);
            persons = (Person) query.getSingleResult();

        } catch (NoResultException e) {
            return null;
       // } catch (IndexOutOfBoundsException e){
            //return null;
        } finally {
            entityManager.close();
        }

        return persons;
    }
}
