package at.gv.ooe.ietservice.rest;

import org.junit.Before;
import org.junit.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static junit.framework.TestCase.fail;
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

    @Test
    public void fetchTickets() {
        Response response = this
                .sut
                .path("tickets")
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Testen, ob Statuscode = 200 ok
        assertThat(response.getStatus(), is(200));
        JsonArray payload = response.readEntity(JsonArray.class);
        System.out.println(payload);
        // Testen, ob Anzahl der elemente im JsonArray ok
        assertThat(payload.size(),is(4));
        // Testen, ob die JsonObjekte ok
        JsonObject ticket = payload.getJsonObject(0);
        assertThat(ticket.getString("answerDate"),is("12.05.2017"));
    }

}
