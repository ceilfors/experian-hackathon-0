package com.carpoolbuddy.rest;

import com.carpoolbuddy.data.City;

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
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/cities")
public class CitiesService {

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<City> getAllCities() {
        // The real one should query the cities from database
        return mockCities();
    }

    private List<City> mockCities() {
        List<City> cities = new ArrayList<City>();
        cities.add(new City("Cyberjaya"));
        cities.add(new City("Puchong"));
        cities.add(new City("Seri Kembangan"));
        cities.add(new City("Sunway"));
        cities.add(new City("Subang"));
        return cities;
    }
}
