package com.sebastian_daschner.hello_prometheus;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Gauge;

@ApplicationScoped
public class RandomGauge {

    private void init(@Observes @Initialized(ApplicationScoped.class) Object startEvent) {
        // to trigger instance creation / activate the gauge
    }

    /*
    curl http://127.0.0.1:9990/metrics/application/  2>/dev/null | grep -v TYPE | grep -v HELP
    curl https://localhost:9443/metrics/application/ --insecure -u theUser:thePassword 2>/dev/null | grep -v TYPE | grep -v HELP
    */
    @Gauge(unit = MetricUnits.NONE)
    public double getRandomGauge() {
        return Math.random() * 1000;
    }
}