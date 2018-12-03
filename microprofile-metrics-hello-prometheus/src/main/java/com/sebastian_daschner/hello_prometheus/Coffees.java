package com.sebastian_daschner.hello_prometheus;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metric;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class Coffees {

    @Inject
    @Metric(name = "total_coffees_consumed", absolute = true)
    Counter coffeesConsumed;

    @Counted(name = "total_coffees_retrieved", absolute = true, monotonic = true)
    public String retrieveCoffee() {
        coffeesConsumed.inc();
        return "Coffee!";
    }

    @Gauge(unit = "USD", name = "coffee_price", absolute = true)
    public long getPrice() {
        return 4;
    }
}
