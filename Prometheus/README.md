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
Look at [pom.xml](pom.xml) in this directory.

```bash
mvn clean package
java -jar target/demo-swarm.jar
```

### OpenLiberty
Get and configure OpenLiberty
```bash
wget https://public.dhe.ibm.com/ibmdl/export/pub/software/openliberty/runtime/release/2017-09-27_1951/openliberty-17.0.0.3.zip
unzip -q openliberty-17.0.0.3.zip
wlp/bin/server create  mp --template=microProfile1
gedit wlp/usr/servers/mp/server.xml
```

Changes in wlp/usr/servers/mp/server.xml:
```xml
<server description="new server">
    ==>
<server description="new server">
    <quickStartSecurity userName="theUser" userPassword="thePassword"/>
    <keyStore id="defaultKeyStore" password="Liberty"/>
```

Run OpenLiberty
```bash
wlp/bin/server run mp &
```

MicroProfile related URLs:
 * http://localhost:9080/metrics/
 * http://localhost:9080/health/
 * http://localhost:9080/jwt/
 * http://localhost:9080/ibm/api/

### Prometheus
Get and configure Prometheus
```bash
wget https://github.com/prometheus/prometheus/releases/download/v2.0.0/prometheus-2.0.0.linux-amd64.tar.gz
tar xzf prometheus-2.0.0.linux-amd64.tar.gz
gedit prom.yml
```

Content of prom.yml
```yaml
scrape_configs:
  # Configuration to poll from WildFly Swarm
  - job_name: 'swarm'
    scrape_interval: 15s
    # translates to http://localhost:8080/metrics
    static_configs:
      - targets: ['localhost:8080']
  # Configuration to poll from OpenLiberty
  - job_name: 'liberty'
    scrape_interval: 15s
    scheme: https
    basic_auth:
      username: 'theUser'
      password: 'thePassword'
    tls_config:
      insecure_skip_verify: true
    # translates to https://localhost:9443/metrics
    static_configs:
      - targets: ['localhost:9443']
```

Run Prometheus
```bash
prometheus-2.0.0.linux-amd64/prometheus --config.file=prom.yml
```

Open http://localhost:9090 to access to Prometheus UI

Sample graph for `base:memory_used_heap_bytes` and `base:thread_count`: http://localhost:9090/graph?g0.range_input=1h&g0.expr=base%3Amemory_used_heap_bytes&g0.tab=0&g1.range_input=1h&g1.expr=base%3Athread_count&g1.tab=0


### End of experiment
```bash
Ctrl + C ## to stop running Prometheus
wlp/bin/server stop mp
```

### WildFly Swarm prior 2017.12.1

Unfortunately boot is not successful due to WFSWARM0008: Artifact org.wildfly.swarm:mp_metrics_cdi_extension:jar:2017.11.0 not found.

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

Version 2017.12.0 has dependency issue - https://issues.jboss.org/browse/SWARM-1735

## Scenario update 2018-11-01

### OpenLiberty
```
wget https://public.dhe.ibm.com/ibmdl/export/pub/software/openliberty/runtime/release/2018-09-05_2337/openliberty-18.0.0.3.zip
unzip -q openliberty-18.0.0.3.zip
wlp/bin/server create  mp --template=microProfile1
code wlp/usr/servers/mp/server.xml
```
```xml
<server description="new server">
    ==>
<server description="new server">
    <quickStartSecurity userName="theUser" userPassword="thePassword"/>
    <keyStore id="defaultKeyStore" password="Liberty"/>
```
```
wlp/bin/server run mp
curl http://localhost:9443/metrics
wlp/bin/server stop mp
```

### Thorntail
```
mvn -f pom-thorntail.xml clean package
java -jar target/demo-thorntail.jar -Dswarm.port.offset=200
curl http://localhost:8280/metrics
```

### WildFly
Using https://github.com/jmesnil/wildfly/tree/WFLY-10712_microprofile_metrics-smallrye
```
git clone https://github.com/jmesnil/wildfly.git
cd wildfly
git checkout WFLY-10712_microprofile_metrics-smallrye
mvn clean install -DskipTests -Denforcer.skip=true -Dcheckstyle.skip=true -Drelease
dist/target/wildfly-15.0.0.Alpha1-SNAPSHOT/bin/standalone.sh -Djboss.socket.binding.port-offset=100

curl -X GET http://localhost:10090/metrics

dist/target/wildfly-15.0.0.Alpha1-SNAPSHOT/bin/standalone.sh -Djboss.socket.binding.port-offset=100 -c standalone-full-ha.xml
```

### Payara
```
wget https://s3-eu-west-1.amazonaws.com/payara.fish/Payara+Downloads/5.183/payara-5.183.zip

payara5/bin/asadmin create-domain --portbase 7000 rs-domain
payara5/bin/asadmin start-domain rs-domain

hit http://localhost:7048/ in browser to lead console, this was needed to see metrics endpoint
curl http://127.0.0.1:7080/metrics

payara5/bin/asadmin stop-domain rs-domain
```

### Helidon
```
mvn archetype:generate -DinteractiveMode=false \
    -DarchetypeGroupId=io.helidon.archetypes \
    -DarchetypeArtifactId=helidon-quickstart-mp \
    -DarchetypeVersion=0.10.4 \
    -DgroupId=io.helidon.examples \
    -DartifactId=quickstart-mp \
    -Dpackage=io.helidon.examples.quickstart.mp

cd quickstart-mp
mvn package
java -jar target/quickstart-mp.jar
curl -X GET http://localhost:8080/metrics
```

### Prometheus
```
wget https://github.com/prometheus/prometheus/releases/download/v2.4.3/prometheus-2.4.3.darwin-amd64.tar.gz
tar xzf prometheus-2.4.3.darwin-amd64.tar.gz
prometheus-2.4.3.darwin-amd64/prometheus --config.file=prom.yml
```

### Prometheus links
http://localhost:9090/graph?g0.range_input=1h&g0.expr=base%3Aclassloader_total_loaded_class_count&g0.tab=0

http://localhost:9090/graph?g0.range_input=1h&g0.expr=base%3Aclassloader_total_loaded_class_count&g0.tab=0&g1.range_input=1h&g1.expr=base%3Athread_count&g1.tab=0&g2.range_input=1h&g2.expr=base%3Amemory_committed_heap_bytes&g2.tab=0&g3.range_input=1h&g3.expr=base%3Amemory_used_heap_bytes&g3.tab=0&g4.range_input=1h&g4.expr=base%3Amemory_max_heap_bytes&g4.tab=0


## Experiment with WildFly Subsystem Metrics
Using WildFly build from https://github.com/wildfly/wildfly/pull/11949 related branch, Prometheus v2.7.0-rc.1.
Started with `wildfly-16.0.0.Beta1-SNAPSHOT/bin/standalone.sh -Dwildfly.statistics-enabled=true` command, accessed http://localhost:8080/ and https://localhost:8443/ several times.

Prometheus displays one metric per chart, so 2 charts for `wildfly_undertow_bytes_received_bytes` and `wildfly_undertow_bytes_sent_bytes`
* http://localhost:9090/graph?g0.range_input=1h&g0.expr=wildfly_undertow_bytes_received_bytes&g0.tab=0&g1.range_input=1h&g1.expr=wildfly_undertow_bytes_sent_bytes%20%20&g1.tab=0

Using workaround from https://github.com/prometheus/prometheus/issues/39#issuecomment-305183333 to have 2 metrics in one chart:
* http://localhost:9090/graph?g0.range_input=1h&g0.expr=%7B__name__%3D~%22wildfly_undertow_bytes_sent_bytes%7Cwildfly_undertow_bytes_received_bytes%7Cwildfly_io_max_pool_size%22%7D&g0.tab=0
