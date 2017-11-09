Prometheus
========================
Prometheus (https://prometheus.io/) is monitoring and alerting open-source solution.

Follow these links to get started:
 * https://prometheus.io/docs/introduction/getting_started/
 * https://www.digitalocean.com/community/tutorials/how-to-use-prometheus-to-monitor-your-centos-7-server (Node Exporter part is nice)
 * https://prometheus.io/docs/visualization/grafana/
 * https://grafana.com/dashboards?dataSource=prometheus

## MicroProfile 1.2 servers ++ Prometheus
Experiment based on https://dzone.com/articles/monitoring-an-eclipse-microprofile-12-server-with article.

### WildFly Swarm
Look at [pom.xml](pom.xml) in this directory, `org.wildfly.swarm:microprofile-metrics` is currently only in version `2017.11.0-SNAPSHOT`.

```bash
mvn clean package
java -jar target/demo-swarm.jar
```
Unfortunately boot is not succesfull due to WFSWARM0008: Artifact 'org.wildfly.swarm:mp_metrics_cdi_extension:jar:2017.11.0' not found.

```
2017-11-09 15:54:27,460 ERROR [stderr] (main) org.wildfly.swarm.container.DeploymentException: java.lang.RuntimeException: WFSWARM0008: Artifact 'org.wildfly.swarm:mp_metrics_cdi_extension:jar:2017.11.0' not found.
2017-11-09 15:54:27,461 ERROR [stderr] (main) 	at org.wildfly.swarm.container.runtime.RuntimeDeployer.deploy(RuntimeDeployer.java:301)
2017-11-09 15:54:27,461 ERROR [stderr] (main) 	at org.wildfly.swarm.container.runtime.RuntimeDeployer.deploy(RuntimeDeployer.java:174)
2017-11-09 15:54:27,461 ERROR [stderr] (main) 	at org.wildfly.swarm.container.runtime.RuntimeDeployer.deploy(RuntimeDeployer.java:107)
2017-11-09 15:54:27,462 ERROR [stderr] (main) 	at org.wildfly.swarm.container.runtime.RuntimeDeployer$Proxy$_$$_WeldClientProxy.deploy(Unknown Source)
2017-11-09 15:54:27,462 ERROR [stderr] (main) 	at org.wildfly.swarm.Swarm.deploy(Swarm.java:474)
2017-11-09 15:54:27,462 ERROR [stderr] (main) 	at org.wildfly.swarm.Swarm.main(Swarm.java:736)
2017-11-09 15:54:27,462 ERROR [stderr] (main) 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
2017-11-09 15:54:27,463 ERROR [stderr] (main) 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
2017-11-09 15:54:27,463 ERROR [stderr] (main) 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
2017-11-09 15:54:27,463 ERROR [stderr] (main) 	at java.lang.reflect.Method.invoke(Method.java:498)
2017-11-09 15:54:27,463 ERROR [stderr] (main) 	at org.wildfly.swarm.bootstrap.MainInvoker.invoke(MainInvoker.java:54)
2017-11-09 15:54:27,464 ERROR [stderr] (main) 	at org.wildfly.swarm.bootstrap.Main.run(Main.java:133)
2017-11-09 15:54:27,464 ERROR [stderr] (main) 	at org.wildfly.swarm.bootstrap.Main.main(Main.java:86)
2017-11-09 15:54:27,464 ERROR [stderr] (main) Caused by: java.lang.RuntimeException: WFSWARM0008: Artifact 'org.wildfly.swarm:mp_metrics_cdi_extension:jar:2017.11.0' not found.
2017-11-09 15:54:27,464 ERROR [stderr] (main) 	at org.wildfly.swarm.internal.ArtifactManager.artifact(ArtifactManager.java:61)
2017-11-09 15:54:27,464 ERROR [stderr] (main) 	at org.wildfly.swarm.internal.ArtifactManager.artifact(ArtifactManager.java:54)
2017-11-09 15:54:27,465 ERROR [stderr] (main) 	at org.wildfly.swarm.spi.api.DependenciesContainer.addDependency(DependenciesContainer.java:91)
2017-11-09 15:54:27,465 ERROR [stderr] (main) 	at org.wildfly.swarm.microprofile_metrics.runtime.CdiExtensionInstaller.process(CdiExtensionInstaller.java:54)
2017-11-09 15:54:27,465 ERROR [stderr] (main) 	at org.wildfly.swarm.microprofile_metrics.runtime.CdiExtensionInstaller$Proxy$_$$_WeldClientProxy.process(Unknown Source)
2017-11-09 15:54:27,465 ERROR [stderr] (main) 	at org.wildfly.swarm.container.runtime.RuntimeDeployer.deploy(RuntimeDeployer.java:233)
2017-11-09 15:54:27,466 ERROR [stderr] (main) 	... 12 more
```

```bash
unzip -l target/demo-swarm.jar | grep cdi_extension
    36724  11-09-2017 15:51   m2repo/org/wildfly/swarm/mp_metrics_cdi_extension/2017.11.0-SNAPSHOT/mp_metrics_cdi_extension-2017.11.0-SNAPSHOT.jar
        0  11-09-2017 15:51   m2repo/org/wildfly/swarm/mp_metrics_cdi_extension/
        0  11-09-2017 15:51   m2repo/org/wildfly/swarm/mp_metrics_cdi_extension/2017.11.0-SNAPSHOT/

```

TODO: Try with 2017.12.0 once released