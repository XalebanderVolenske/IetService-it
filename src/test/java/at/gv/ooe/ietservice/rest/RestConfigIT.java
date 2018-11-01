package at.gv.ooe.ietservice.rest;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestConfigIT {

    private Client client;
    private WebTarget sut; // System under test

    @Before
    public void initClient() {
        this.client = ClientBuilder.newClient();
        this.sut = this.client.target("http://localhost:8080/ietservice/rs/ticketservice");
    }

    @Test
    public void fetchGreeting() {
        Response response = this
                .sut
                .request()
                .get();
        assertThat(response.getStatus(), is(200));
        String payload = response.readEntity(String.class);
        assertThat(payload, is("{\"greeting\":\"hello\"}"));
    }

}
