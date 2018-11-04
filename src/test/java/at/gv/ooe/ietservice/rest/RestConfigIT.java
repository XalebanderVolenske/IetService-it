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
                .path("ticket")
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Testen, ob Statuscode = 200 ok
        assertThat(response.getStatus(), is(200));
        JsonArray payload = response.readEntity(JsonArray.class);
        System.out.println(payload);
        // Testen, ob Anzahl der elemente im JsonArray ok
        assertThat(payload.size(),is(18));
        // Testen, ob die JsonObjekte ok
        JsonObject ticket = payload.getJsonObject(0);
        assertThat(ticket.getString("answerDate"),is("2018-10-25T15:00:00"));
    }

    @Test
    public void fetchDiaryEntries() {
        Response response = this
                .sut
                .path("diaryEntry")
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Testen, ob Statuscode = 200 ok
        assertThat(response.getStatus(), is(200));
        JsonArray payload = response.readEntity(JsonArray.class);
        System.out.println(payload);
        // Testen, ob Anzahl der elemente im JsonArray ok
        assertThat(payload.size(),is(3));
        // Testen, ob die JsonObjekte ok
        JsonObject ticket = payload.getJsonObject(0);
        assertThat(ticket.getString("createdDate"),is("28.10.2018 15:30"));
    }

    @Test
    public void fetchDepartments() {
        Response response = this
                .sut
                .path("department")
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Testen, ob Statuscode = 200 ok
        assertThat(response.getStatus(), is(200));
        JsonArray payload = response.readEntity(JsonArray.class);
        System.out.println(payload);
        // Testen, ob Anzahl der elemente im JsonArray ok
        assertThat(payload.size(),is(3));
        // Testen, ob die JsonObjekte ok
        JsonObject ticket = payload.getJsonObject(0);
        assertThat(ticket.getString("name"),is("Wohnbaufoerderung"));
    }

    @Test
    public void fetchUsers() {
        Response response = this
                .sut
                .path("user")
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Testen, ob Statuscode = 200 ok
        assertThat(response.getStatus(), is(200));
        JsonArray payload = response.readEntity(JsonArray.class);
        System.out.println(payload);
        // Testen, ob Anzahl der elemente im JsonArray ok
        assertThat(payload.size(),is(10));
        // Testen, ob die JsonObjekte ok
        JsonObject ticket = payload.getJsonObject(0);
        assertThat(ticket.getString("address"),is("Bahnhofplatz 1"));
    }

    @Test
    public void fetchCIs() {
        Response response = this
                .sut
                .path("ci")
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Testen, ob Statuscode = 200 ok
        assertThat(response.getStatus(), is(200));
        JsonArray payload = response.readEntity(JsonArray.class);
        System.out.println(payload);
        // Testen, ob Anzahl der elemente im JsonArray ok
        assertThat(payload.size(),is(0));
    }

    @Test
    public void fetchTicketsFromDepartment() {
        Response response = this
                .sut
                .path("departmentticket/1")
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Testen, ob Statuscode = 200 ok
        assertThat(response.getStatus(), is(200));
        JsonArray payload = response.readEntity(JsonArray.class);
        System.out.println(payload);
        // Testen, ob Anzahl der elemente im JsonArray ok
        assertThat(payload.size(),is(15));
        // Testen, ob die JsonObjekte ok
        JsonObject ticket = payload.getJsonObject(0);
        assertThat(ticket.getString("answerDate"),is("2018-10-25T15:00:00"));
    }

    @Test
    public void fetchTicketsByDateRangeFromUser() {
        Response response = this
                .sut
                .path("ticket/1/2018-10-02T15:00:00/2018-10-16T15:00:00")
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Testen, ob Statuscode = 200 ok
        assertThat(response.getStatus(), is(200));
        JsonArray payload = response.readEntity(JsonArray.class);
        System.out.println(payload);
        // Testen, ob Anzahl der elemente im JsonArray ok
        assertThat(payload.size(),is(2));
        // Testen, ob die JsonObjekte ok
        JsonObject ticket = payload.getJsonObject(0);
        assertThat(ticket.getString("answerDate"),is("2018-11-01T07:30:00"));
    }

    @Test
    public void fetchTicketDiaryEntries() {
        Response response = this
                .sut
                .path("ticketdiary/2")
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Testen, ob Statuscode = 200 ok
        assertThat(response.getStatus(), is(200));
        JsonArray payload = response.readEntity(JsonArray.class);
        System.out.println(payload);
        // Testen, ob Anzahl der elemente im JsonArray ok
        assertThat(payload.size(),is(3));
        // Testen, ob die JsonObjekte ok
        JsonObject ticket = payload.getJsonObject(0);
        assertThat(ticket.getString("createdDate"),is("28.10.2018 15:30"));
    }


}
