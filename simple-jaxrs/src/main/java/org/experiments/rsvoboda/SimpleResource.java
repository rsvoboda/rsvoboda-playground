package org.experiments.rsvoboda;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("simple") public class SimpleResource {

    @GET public String hi() {
        return "hi";
    }
}
