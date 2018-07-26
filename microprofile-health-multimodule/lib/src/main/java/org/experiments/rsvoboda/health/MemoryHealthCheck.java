package org.experiments.rsvoboda.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

@Health
public class MemoryHealthCheck implements HealthCheck {

    public HealthCheckResponse call() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long memUsed = memoryBean.getHeapMemoryUsage().getUsed();
        long memMax = memoryBean.getHeapMemoryUsage().getMax();
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("heap-memory")
                .withData("used", memUsed)
                .withData("max", memMax);

        // status is is down is used memory is greater than 90% of max memory.
        return (memUsed < memMax * 0.9) ? responseBuilder.up().build() : responseBuilder.down().build();
    }
}
