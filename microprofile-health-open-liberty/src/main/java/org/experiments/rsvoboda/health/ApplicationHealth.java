package org.experiments.rsvoboda.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import java.util.Random;

@Health
public class ApplicationHealth implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        Random rand = new Random();

        if (rand.nextInt(2) == 0) {
            return HealthCheckResponse.named("ApplicationHealthCheck")
                    .withData("application", "not available").down()
                    .build();
        } else {
            return HealthCheckResponse.named("ApplicationHealthCheck")
                    .withData("application", "available").up()
                    .build();
        }

    }
}
