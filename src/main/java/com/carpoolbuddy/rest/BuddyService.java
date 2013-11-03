package com.carpoolbuddy.rest;

import com.carpoolbuddy.data.City;
import com.carpoolbuddy.data.Person;
import com.carpoolbuddy.data.dao.PersonDataHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alexus
 * Date: 11/2/13
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/buddies")
public class BuddyService {

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Person> findMatchBuddy(@QueryParam("from") String fromCity,
                                       @QueryParam("to") String toCity) {
        //Get this from db
        List<Person> mockBuds = mockBuddies();
        List<Person> foundBuds = new ArrayList<Person>();
        for (Person person : mockBuds) {
            if (person.getFrom().getName().equals(fromCity) && person.getTo().getName().equals(toCity)) {
                foundBuds.add(person);
            }
        }
        return foundBuds;
    }

    @POST
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response createPerson(@FormParam("name") String name,
                                 @FormParam("fbid") String facebookId,
                                 @FormParam("from") String from,
                                 @FormParam("to") String to) {
        PersonDataHandler personDataHandler = new PersonDataHandler();
        if (personDataHandler.createPerson(new Person(name, facebookId, new City(from), new City(to)))) {
            return Response.status(201).build();
        } else {
            return Response.status(500).build();
        }
    }

    @Path("/fbid/{param}")
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Person findPersonByFacebookId(@PathParam("param") String facebookId) {
        PersonDataHandler personDataHandler = new PersonDataHandler();
        Person person = personDataHandler.findPersonByFacebookId(facebookId);

        return person;
    }

    private List<Person> mockBuddies() {
        List<Person> buddies = new ArrayList<Person>();
        buddies.add(new Person("Ali The Strong Junior", "alistrongjunior", new City("Puchong"), new City("Cyberjaya")));
        buddies.add(new Person("Peter The Flier", "petertheflier", new City("Sunway"), new City("Subang")));
        buddies.add(new Person("Kumar The Cook", "kumarthecook", new City("Cyberjaya"), new City("Puchong")));

        return buddies;
    }

}