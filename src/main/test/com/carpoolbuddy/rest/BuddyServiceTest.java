package com.carpoolbuddy.rest;

import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.junit.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: alexus
 * Date: 11/2/13
 * Time: 4:54 PM
 * To change this template use File | Settings | File Templates.
 */
//TODO: find out why these unit test need the server to be running
public class BuddyServiceTest {

    private static URI uri;
    private WebResource service;

    @BeforeClass
    public static void ClassSetUp() {
        uri = UriBuilder.fromUri("http://localhost:8282/rest/buddies").build();
    }

    @Before
    public void setUp() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource(uri);
    }

    @Test
    public void testFindMatchBuddyWithFromAndToParams() throws Exception {
        // Get JSON for application
        System.out.println("App Json:");
        System.out.println(service.queryParam("from", "Puchong").queryParam("to", "Cyberjaya").accept(MediaType.APPLICATION_JSON).get(String.class));
        // Get XML for application
        System.out.println("App xml:");
        System.out.println(service.queryParam("from", "Puchong").queryParam("to", "Cyberjaya").accept(MediaType.APPLICATION_XML).get(String.class));
    }

//    @Test
    public void testCreatePerson() throws Exception {
        String input = "{\"name\":\"Micheal Buba\",\"fbid\":\"mb\",\"from\":\"Puchong\",\"to\":\"Cyberjaya\"}";

        ClientResponse response = service.path("/rest/buddies").type("application/json")
                .post(ClientResponse.class, input);

        if (response.getStatus() != 201) {
            fail(response.toString());
        }

    }

//    @Test
    public void testFindPersonByFacebook() throws Exception {
        testCreatePerson();

        // Get JSON for application
        System.out.println("testFindPersonByFacebookId:");
        System.out.println(service.path("/rest/buddies/fbid/mb")
                .accept(MediaType.APPLICATION_JSON).get(String.class));
    }
}
