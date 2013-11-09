package com.carpoolbuddy.data.dao;

import com.carpoolbuddy.data.*;

import javax.persistence.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: alexus
 * Date: 11/3/13
 * Time: 5:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class CityDataHandler {

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<City>();
        EntityManager entityManager = CarPoolBuddyEntityManagerFactory.getInstance().makeEntityManager();

        Query query = entityManager.createQuery("select c from City c");
        cities = query.getResultList();

        entityManager.close();

        return cities;
    }

    public City findCity(final String cityName) {
        List<City> cities;
        EntityManager entityManager = CarPoolBuddyEntityManagerFactory.getInstance().makeEntityManager();
        try {
            Query query = entityManager.createQuery("select c from City c where c.name = :cityname");
            query.setParameter("cityname", cityName);
            cities = query.getResultList();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }

        //return the first city currently, possibly want to rethink this once address entity is used
        if (!cities.isEmpty()) {
            return cities.get(0);
        } else {
            return null;
        }
    }
}
