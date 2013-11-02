package com.carpoolbuddy.rest;

import com.carpoolbuddy.data.City;
import com.carpoolbuddy.data.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
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
public class BuddiesService {

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Person> findMatchBuddy(@Context UriInfo info) {
        //get the from and to from person
        String fromCity = info.getQueryParameters().getFirst("from");
        String toCity = info.getQueryParameters().getFirst("to");

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

    private List<Person> mockBuddies() {
        List<Person> buddies = new ArrayList<Person>();
        buddies.add(new Person("ali", new City("Puchong"), new City("Cyberjaya")));
        buddies.add(new Person("peter", new City("Sunway"), new City("Subang")));
        buddies.add(new Person("kumar", new City("Cyberjaya"), new City("Puchong")));

        return buddies;
    }

}
