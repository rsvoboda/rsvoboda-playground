package foo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("")
public class Service {

  @GET
  public String get() { return "foo"; }

}

