package org.experiments.rsvoboda;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by rsvoboda on 5/1/17.
 *
 */
@Path("/hello")
public class HelloResource {


    @Inject
    @Config("hello.greeting")
    String greeting;

    @Inject
    @Config("hello.name")
    String name;

    @GET
    public String hello() {
        return greeting + ", " + name + "!";
    }

}
