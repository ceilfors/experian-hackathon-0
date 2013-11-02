package com.carpoolbuddy.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created with IntelliJ IDEA.
 * User: alexus
 * Date: 11/2/13
 * Time: 4:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuddiesServiceTest {
//    @Test
    public void testFindMatchBuddy() throws Exception {
        URI uri = UriBuilder.fromUri("http://localhost:8080/").build();

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(uri);

        // Get JSON for application
        System.out.println("App Json:");
        System.out.println(service.path("rest").path("/buddies").accept(MediaType.APPLICATION_JSON).get(String.class));
        // Get XML for application
        System.out.println("App xml:");
        System.out.println(service.path("rest").path("/buddies").accept(MediaType.APPLICATION_XML).get(String.class));
    }

    @Test
    public void testFindMatchBuddyWithFromAndToParams() throws Exception {
        URI uri = UriBuilder.fromUri("http://localhost:8080/").build();

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(uri);

        // Get JSON for application
        System.out.println("App Json:");
        System.out.println(service.path("rest").path("/buddies").queryParam("from", "Puchong").queryParam("to", "Cyberjaya").accept(MediaType.APPLICATION_JSON).get(String.class));
        // Get XML for application
        System.out.println("App xml:");
        System.out.println(service.path("rest").path("/buddies").queryParam("from", "Puchong").queryParam("to", "Cyberjaya").accept(MediaType.APPLICATION_XML).get(String.class));
    }
}
