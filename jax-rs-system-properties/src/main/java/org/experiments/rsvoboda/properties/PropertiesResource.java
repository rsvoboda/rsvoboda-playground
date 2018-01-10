/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2018, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.experiments.rsvoboda.properties;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonStructure;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Rostislav Svoboda
 */
@Path("properties")
public class PropertiesResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getProperties() {

        JsonObjectBuilder builder = Json.createObjectBuilder();
        System.getProperties()
                .entrySet()
                .stream()
                .forEach(entry -> builder.add((String) entry.getKey(), (String) entry.getValue()));
        return builder.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("pretty")
    public String getPrettyProperties() {

        JsonObjectBuilder builder = Json.createObjectBuilder();
        System.getProperties()
                .entrySet()
                .stream()
                .forEach(entry -> builder.add((String) entry.getKey(), (String) entry.getValue()));

        return toString(builder.build());
    }

    private static JsonWriterFactory FACTORY_INSTANCE;

    public static String toString(final JsonStructure status) {
        final StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = getPrettyJsonWriterFactory().createWriter(stringWriter)) {
            jsonWriter.write(status);
        }
        return stringWriter.toString();
    }

    private static JsonWriterFactory getPrettyJsonWriterFactory() {
        if (null == FACTORY_INSTANCE) {
            final Map<String, Object> properties = new HashMap<>(1);
            properties.put(JsonGenerator.PRETTY_PRINTING, true);
            FACTORY_INSTANCE = Json.createWriterFactory(properties);
        }
        return FACTORY_INSTANCE;
    }

}
