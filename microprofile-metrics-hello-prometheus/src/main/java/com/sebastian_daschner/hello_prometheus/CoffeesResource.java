package com.sebastian_daschner.hello_prometheus;

import org.eclipse.microprofile.metrics.*;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("coffee")
public class CoffeesResource {

    @Inject
    MetricRegistry registry;

    @Inject
    Coffees coffees;

    @GET
    public String getCoffee() {
        return coffees.retrieveCoffee();
    }

    // Coffee class needs to be @ApplicationScoped
    @GET
    @Path("/price")
    public Response getItemsPrice() {
        return Response.ok(coffees.getPrice()).build();
    }

    // examples from https://www.tomitribe.com/blog/getting-started-with-microprofile-metrics/

    /*
        curl http://localhost:8080/hello-prometheus/resources/coffee/sold
        curl http://127.0.0.1:9990/metrics/application/itemsSold -H "Accept: application/json"
        curl http://127.0.0.1:9990/metrics/application/itemsSold
           Output / values diff
    */
    @Metered(name = "itemsSold",
            unit = MetricUnits.MINUTES,
            description = "Metrics to monitor sold method - @Metered.",
            absolute = true)
    @GET
    @Path("/sold")
    public Response itemSold() {
        return Response.ok().build();
    }

    /*
        curl http://localhost:8080/hello-prometheus/resources/coffee/process
        curl http://127.0.0.1:9990/metrics/application/itemsProcessed -H "Accept: application/json"
        curl http://127.0.0.1:9990/metrics/application/itemsProcessed
           Output / values diff
    */
    @Timed(name = "itemsProcessed",
            description = "Metrics to monitor the times of processItem method. - @Timed(",
            unit = MetricUnits.MINUTES,
            absolute = true)
    @GET
    @Path("/process")
    public Response processItem() {
        return Response.ok().build();
    }

    /*
        curl http://localhost:8080/hello-prometheus/resources/coffee/add/5
        curl http://127.0.0.1:9990/metrics/application/itemsAdded -H "Accept: application/json"
        curl http://127.0.0.1:9990/metrics/application/itemsAdded
        curl http://localhost:8080/hello-prometheus/resources/coffee/add/5
           Caused by: java.lang.IllegalArgumentException: Previously registered metric itemsAdded was not flagged as reusable
        */
    @GET
    @Path("/add/{numberOfItems}")
    public Response addItems(@PathParam("numberOfItems") String numberOfItems) {
        Metadata metadata = new Metadata("itemsAdded", MetricType.HISTOGRAM);
        Histogram histogram = registry.histogram(metadata);
        histogram.update(Long.valueOf(numberOfItems));
        return Response.ok().build();
    }

}
