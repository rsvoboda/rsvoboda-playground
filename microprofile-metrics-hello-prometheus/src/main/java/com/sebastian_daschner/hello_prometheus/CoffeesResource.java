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

        curl http://localhost:9080/hello-prometheus/resources/coffee/sold
        curl https://localhost:9443/metrics/application/itemsSold -H "Accept: application/json" --insecure -u theUser:thePassword 2>/dev/null | jq
        curl https://localhost:9443/metrics/application/itemsSold --insecure -u theUser:thePassword 2>/dev/null | grep -v TYPE | grep -v HELP
            OpenLiberty is fine
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

        curl http://localhost:9080/hello-prometheus/resources/coffee/process
        curl http://localhost:9080/hello-prometheus/resources/coffee/process
        curl https://localhost:9443/metrics/application/itemsProcessed -H "Accept: application/json" --insecure -u theUser:thePassword 2>/dev/null | jq
        curl https://localhost:9443/metrics/application/itemsProcessed --insecure -u theUser:thePassword 2>/dev/null | grep -v TYPE | grep -v HELP
            OpenLiberty is fine
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

        curl http://localhost:9080/hello-prometheus/resources/coffee/add/5
        curl https://localhost:9443/metrics/application/itemsAdded -H "Accept: application/json" --insecure -u theUser:thePassword 2>/dev/null | jq
        curl https://localhost:9443/metrics/application/itemsAdded --insecure -u theUser:thePassword 2>/dev/null | grep -v TYPE | grep -v HELP
        curl http://localhost:9080/hello-prometheus/resources/coffee/add/5
        curl http://localhost:9080/hello-prometheus/resources/coffee/add/2
        curl http://localhost:9080/hello-prometheus/resources/coffee/add/1
            OpenLiberty is fine
        */
    @GET
    @Path("/add/{numberOfItems}")
    public Response addItems(@PathParam("numberOfItems") String numberOfItems) {
        Metadata metadata = new Metadata("itemsAdded", MetricType.HISTOGRAM);
        Histogram histogram = registry.histogram(metadata);
        histogram.update(Long.valueOf(numberOfItems));
        return Response.ok().build();
    }


    // examples from https://www.ibm.com/support/knowledgecenter/en/SSEQTP_liberty/com.ibm.websphere.wlp.doc/ae/cwlp_mp_metrics_api.html

    Metadata statsHitsCounterMetadata = new Metadata(
            "statsHits",
            "Stats Hits",
            "Number of hits on the /stats endpoint",
            MetricType.COUNTER,
            MetricUnits.NONE);

    /*
        curl http://localhost:8080/hello-prometheus/resources/coffee/stats
        curl http://localhost:8080/hello-prometheus/resources/coffee/stats
            Caused by: java.lang.IllegalArgumentException: Previously registered metric statsHits was not flagged as reusable

        curl http://localhost:9080/hello-prometheus/resources/coffee/stats
        curl http://localhost:9080/hello-prometheus/resources/coffee/stats
        curl http://localhost:9080/hello-prometheus/resources/coffee/stats
        curl -k -u theUser:thePassword https://localhost:9443/metrics/application/statsHits
        curl -k -u theUser:thePassword -H "Accept: application/json" https://localhost:9443/metrics/application/statsHits
            OpenLiberty is fine
     */
    @GET
    @Path("/stats")
    public String getTotalDonations() {
        // https://github.com/smallrye/smallrye-metrics/issues/43#issuecomment-446498898
        statsHitsCounterMetadata.setReusable(true);
        registry.counter(statsHitsCounterMetadata).inc();

        return "12345";
    }


    Gauge<Double> progressGauge = new Gauge<Double>() {
        public Double getValue() {
            return Math.random() * 100;
        }
    };
    Metadata progressMetadata = new Metadata(
            "progress",
            "Donation Progress",
            "The percentage of the goal achieved so far",
            MetricType.GAUGE,
            MetricUnits.PERCENT);

    /*
        curl http://localhost:8080/hello-prometheus/resources/coffee/donation
        curl http://localhost:8080/hello-prometheus/resources/coffee/donation
            Caused by: java.lang.IllegalArgumentException: A metric with name progress already exists
        curl http://127.0.0.1:9990/metrics/application/progress

        curl http://localhost:9080/hello-prometheus/resources/coffee/donation
        curl -k -u theUser:thePassword https://localhost:9443/metrics/application/progress
        curl -k -u theUser:thePassword -H "Accept: application/json" https://localhost:9443/metrics/application/progress

     */
    @GET
    @Path("/donation")
    public Response updateGauge() {
        registry.register(progressMetadata, progressGauge);
        return Response.ok().build();
    }


}
