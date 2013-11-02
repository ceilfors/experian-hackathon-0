package com.carpoolbuddy.rest;

import com.carpoolbuddy.data.City;
import com.carpoolbuddy.data.Person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    public List<Person> findMatchBuddy() {
        //get the from and to from person
        //match with db
        return mockBuddies();
    }

    private List<Person> mockBuddies() {
        List<Person> buddies = new ArrayList<Person>();
        buddies.add(new Person("ali", new City("Puchong"), new City("Cyberjaya")));
        buddies.add(new Person("peter", new City("Sunway"), new City("Subang")));
        buddies.add(new Person("kumar", new City("Cyberjaya"), new City("Puchong")));

        return buddies;
    }

}
