package org.experiments.rsvoboda.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicLong;

@Health
@ApplicationScoped
public class CountHealthCheck implements HealthCheck {
    AtomicLong count = new AtomicLong(0);

    public HealthCheckResponse call() {
        return HealthCheckResponse.named("CountHealthCheck")
                .withData("count", count.addAndGet(1))
                .up()
                .build();
    }
}
