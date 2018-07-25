package org.experiments.rsvoboda.health;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class FooResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getAllConfig() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        return builder.add("foo", "bar").add("fool", "baz").build();
    }

}
