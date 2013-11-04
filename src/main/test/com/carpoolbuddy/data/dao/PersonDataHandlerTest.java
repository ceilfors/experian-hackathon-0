package com.carpoolbuddy.data.dao;

import com.carpoolbuddy.data.City;
import com.carpoolbuddy.data.Person;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: alexus
 * Date: 11/3/13
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
//TODO: check why does these test always fails with the message class not enhanced
public class PersonDataHandlerTest {
    @Test
    public void testCreatePerson() throws Exception {
        PersonDataHandler personDataHandler = new PersonDataHandler();
        Person person = new Person("Mong", "edjayks", new City("Seri Kembangan"), new City("Cyberjaya"));
        personDataHandler.createPerson(person);
    }

    @Test
    public void testFindPersonByFacebookId() throws Exception {
        PersonDataHandler personDataHandler = new PersonDataHandler();
        Person person = new Person("Lee", "lie", new City("Sunway"), new City("Cyberjaya"));
        personDataHandler.createPerson(person);

        Person foundPerson = personDataHandler.findPersonByFacebookId("lie");
        if (foundPerson == null) {
            fail();
        } else {
            System.out.print("Found:" + foundPerson.getName());
        }
    }

    @Test
    public void testFindBuddiesWithSameFromAndTo() throws Exception {
        //TODO: should prepare db and data before performing search here
        PersonDataHandler personDataHandler = new PersonDataHandler();
        List<Person> persons = personDataHandler.findBuddiesWithSameFromAndTo("Cyberjaya", "Puchong");

        if (persons.isEmpty()) {
            fail();
        }
    }
}
