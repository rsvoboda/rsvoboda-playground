# Dropwizard Metrics with Apache CXF, Spring Boot, Prometheus and Grafana

Since version 3.1 Apache CXF provides metrics feature (see org.apache.cxf.metrics.MetricsFeature) for collecting metrics about a CXF services. Codahale/DropWizard based collector included.

I tried play with https://github.com/reta/jax-rs-2.0-cxf-metrics but I hit issue with docker compose.
Boot of jax-rs-2.0-cxf-metrics:latest image was killed.

```
cxf_1         | 
cxf_1         |   .   ____          _            __ _ _
cxf_1         |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
cxf_1         | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
cxf_1         |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
cxf_1         |   '  |____| .__|_| |_|_| |_\__, | / / / /
cxf_1         |  =========|_|==============|___/=/_/_/_/
cxf_1         |  :: Spring Boot ::        (v1.4.0.RELEASE)
cxf_1         | 
cxf_1         | 2017-12-08 19:27:30.842  INFO 8 --- [           main] com.example.Application                  : Starting Application on bdb169d85be7 with PID 8 (/jax-rs-2.0-cxf-metrics-0.0.1-SNAPSHOT.jar started by root in /)
cxf_1         | 2017-12-08 19:27:30.848  INFO 8 --- [           main] com.example.Application                  : No active profile set, falling back to default profiles: default
cxf_1         | 2017-12-08 19:27:30.994  INFO 8 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@7e0b37bc: startup date [Fri Dec 08 19:27:30 GMT 2017]; root of context hierarchy
cxf_1         | Killed
docker_cxf_1 exited with code 137
```

After some investigation I figured out that problem is with memory. After forking the repo and I fixed the problem.
See https://github.com/rsvoboda/jax-rs-2.0-cxf-metrics/commit/883acf59443bc9703dfab0b30a44fe22881594dd

## Running
Follow steps in https://github.com/rsvoboda/jax-rs-2.0-cxf-metrics/blob/master/README.md

To generate some load you can use simply curl and for cycle:
```bash
 for i in {1..100}; do curl http://localhost:19090/services/people | jq .; sleep $((RANDOM % 6)); done &
 for i in {1..100}; do curl http://localhost:19090/services/people | jq .; sleep $((RANDOM % 10)); done &
```