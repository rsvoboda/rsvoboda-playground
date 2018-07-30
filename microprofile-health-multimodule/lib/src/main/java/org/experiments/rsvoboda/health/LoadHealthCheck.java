package org.experiments.rsvoboda.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

@Health
public class LoadHealthCheck implements HealthCheck {

    public HealthCheckResponse call() {

        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        double load = osBean.getSystemLoadAverage();
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("system-load")
                .withData("load", load + "");

        return (load < 5.0) ? responseBuilder.up().build() : responseBuilder.down().build();
    }
}
