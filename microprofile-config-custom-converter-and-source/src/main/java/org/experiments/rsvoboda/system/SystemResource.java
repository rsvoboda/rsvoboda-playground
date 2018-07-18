package org.experiments.rsvoboda.system;

import javax.ws.rs.GET;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("properties")
public class SystemResource {

  @Inject
  SystemConfig systemConfig;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getProperties() {
    if (!systemConfig.isInMaintenance()) {
      return Response.ok(System.getProperties()).build();
    } else {
      return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                     .entity("ERROR: Service is currently in maintenance. Contact: "
                         + systemConfig.getEmail().toString())
                     .build();
    }
  }

}
