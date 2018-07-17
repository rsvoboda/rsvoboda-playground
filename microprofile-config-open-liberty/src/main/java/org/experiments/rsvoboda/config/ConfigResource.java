package org.experiments.rsvoboda.config;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.Json;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.config.spi.ConfigSource;

//@RequestScoped
@Path("/")
public class ConfigResource {

    @Inject
    private Config config;

    @Inject
    @ConfigProperty(name = "port_number")
    Integer port;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getAllConfig() {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        return builder.add("ConfigSources", port.toString()).build();

//    return builder.add("ConfigSources", sourceJsonBuilder())
//                  .add("ConfigProperties", propertyJsonBuilder()).build();
    }

    public JsonObject sourceJsonBuilder() {
        JsonObjectBuilder sourcesBuilder = Json.createObjectBuilder();
        for (ConfigSource source : config.getConfigSources()) {
            sourcesBuilder.add(source.getName(), source.getOrdinal());
        }
        return sourcesBuilder.build();
    }

    public JsonObject propertyJsonBuilder() {
        JsonObjectBuilder propertiesBuilder = Json.createObjectBuilder();
        for (String name : config.getPropertyNames()) {
            propertiesBuilder.add(name, config.getValue(name, String.class));
        }
        return propertiesBuilder.build();
    }

}
