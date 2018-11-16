# Microprofile Metrics TCK notes

Starting from scratch and following https://github.com/eclipse/microprofile-metrics/blob/master/tck/running_the_tck.asciidoc was more complicated than expected.

So next attempt was to look at https://github.com/smallrye/smallrye-metrics/tree/master/testsuite/rest-tck.

Steps were following:
 - Check if it can run with WF 14 using `mvn test -Dversion.wildfly=14.0.0.Final`
 - Move from WildFly servlet distribution to full WildFly 14 distribution
 - Move to WildFly 15 distribution which includes MP Metrics

## End result
MP Metrics REST TCK passes, warnings in log.

 - Repo: https://github.com/rsvoboda/smallrye-metrics/tree/wf15TckExperiment
 - Command: `mvn clean test -Dversion.wildfly=15.0.0.CR1 -Dtest.url=http://localhost:9990 -Dmaven.test.redirectTestOutputToFile=true`

```java
[INFO] -------------------------------------------------------
[INFO] Running org.eclipse.microprofile.metrics.test.MpMetricTest
[INFO] Tests run: 30, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 9.925 s - in org.eclipse.microprofile.metrics.test.MpMetricTest
[INFO] Running org.eclipse.microprofile.metrics.test.ReusableMetricsTest
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.36 s - in org.eclipse.microprofile.metrics.test.ReusableMetricsTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 39, Failures: 0, Errors: 0, Skipped: 0
```

## Warnings
Log contains warnings similar to this:
```ruby
10:36:12,085 WARN  [io.smallrye.metrics] (management I/O-2) Unable to export metric deployment/test.war/subsystem/undertow/servlet/ArquillianServletRunner/max-request-time: java.lang.IllegalStateException: WFLYMETRICS0005: Metric attribute max-request-time on [
    ("deployment" => "test.war"),
    ("subsystem" => "undertow"),
    ("servlet" => "ArquillianServletRunner")
] is undefined and will not be exposed.
	at org.wildfly.extension.microprofile.metrics.MetricsRegistrationService$1.getValue(MetricsRegistrationService.java:281)
	at org.wildfly.extension.microprofile.metrics.MetricsRegistrationService$1.getValue(MetricsRegistrationService.java:250)
	at io.smallrye.metrics.exporters.JsonExporter.getValueFromMetric(JsonExporter.java:191)
	at io.smallrye.metrics.exporters.JsonExporter.writeMetricsForMap(JsonExporter.java:98)
	at io.smallrye.metrics.exporters.JsonExporter.getMetricsForAScope(JsonExporter.java:65)
	at io.smallrye.metrics.exporters.JsonExporter.exportOneScope(JsonExporter.java:52)
	at io.smallrye.metrics.MetricsRequestHandler.handleRequest(MetricsRequestHandler.java:120)
	at org.wildfly.extension.microprofile.metrics.MetricsContextService$1.handleRequest(MetricsContextService.java:94)
	at org.jboss.as.domain.http.server.security.RealmReadinessHandler.handleRequest(RealmReadinessHandler.java:51)
	at org.jboss.as.domain.http.server.security.ServerErrorReadinessHandler.handleRequest(ServerErrorReadinessHandler.java:35)
	at io.undertow.server.handlers.PathHandler.handleRequest(PathHandler.java:94)
	at io.undertow.server.handlers.ChannelUpgradeHandler.handleRequest(ChannelUpgradeHandler.java:211)
	at io.undertow.server.handlers.cache.CacheHandler.handleRequest(CacheHandler.java:92)
	at io.undertow.server.handlers.error.SimpleErrorPageHandler.handleRequest(SimpleErrorPageHandler.java:78)
	at io.undertow.server.handlers.CanonicalPathHandler.handleRequest(CanonicalPathHandler.java:49)
	at org.jboss.as.domain.http.server.ManagementHttpRequestHandler.handleRequest(ManagementHttpRequestHandler.java:57)
	at org.jboss.as.domain.http.server.cors.CorsHttpHandler.handleRequest(CorsHttpHandler.java:75)
	at org.jboss.as.domain.http.server.ManagementHttpServer$UpgradeFixHandler.handleRequest(ManagementHttpServer.java:662)
	at io.undertow.server.Connectors.executeRootHandler(Connectors.java:360)
	at io.undertow.server.protocol.http.HttpReadListener.handleEventWithNoRunningRequest(HttpReadListener.java:255)
	at io.undertow.server.protocol.http.HttpReadListener.handleEvent(HttpReadListener.java:136)
	at io.undertow.server.protocol.http.HttpOpenListener.handleEvent(HttpOpenListener.java:162)
	at io.undertow.server.protocol.http.HttpOpenListener.handleEvent(HttpOpenListener.java:100)
	at io.undertow.server.protocol.http.HttpOpenListener.handleEvent(HttpOpenListener.java:57)
	at org.xnio.ChannelListeners.invokeChannelListener(ChannelListeners.java:92)
	at org.xnio.ChannelListeners$10.handleEvent(ChannelListeners.java:291)
	at org.xnio.ChannelListeners$10.handleEvent(ChannelListeners.java:286)
	at org.xnio.ChannelListeners.invokeChannelListener(ChannelListeners.java:92)
	at org.xnio.nio.QueuedNioTcpServer$1.run(QueuedNioTcpServer.java:131)
	at org.xnio.nio.WorkerThread.safeRun(WorkerThread.java:612)
	at org.xnio.nio.WorkerThread.run(WorkerThread.java:479)
```

## Error
Also one error about already started response appeared in the log, not functional issue from MP Metrics perspective.
```ruby
10:36:08,517 ERROR [io.undertow.request] (management I/O-1) UT005071: Undertow request failed HttpServerExchange{ GET /metrics/bad-tree request {Accept=[*/*], Connection=[Keep-Alive], Accept-Encoding=[gzip,deflate], User-Agent=[Apache-HttpClient/4.3.6 (java 1.5)], Host=[localhost:9990]} response {Connection=[keep-alive], Content-Length=[29], Date=[Thu, 15 Nov 2018 19:36:08 GMT]}}: java.lang.IllegalStateException: UT000002: The response has already been started
	at io.undertow.server.HttpServerExchange.setStatusCode(HttpServerExchange.java:1406)
	at org.wildfly.extension.microprofile.metrics.MetricsContextService$1.lambda$handleRequest$0(MetricsContextService.java:95)
	at io.smallrye.metrics.MetricsRequestHandler.handleRequest(MetricsRequestHandler.java:110)
	at org.wildfly.extension.microprofile.metrics.MetricsContextService$1.handleRequest(MetricsContextService.java:94)
	at org.jboss.as.domain.http.server.security.RealmReadinessHandler.handleRequest(RealmReadinessHandler.java:51)
	at org.jboss.as.domain.http.server.security.ServerErrorReadinessHandler.handleRequest(ServerErrorReadinessHandler.java:35)
	at io.undertow.server.handlers.PathHandler.handleRequest(PathHandler.java:94)
	at io.undertow.server.handlers.ChannelUpgradeHandler.handleRequest(ChannelUpgradeHandler.java:211)
	at io.undertow.server.handlers.cache.CacheHandler.handleRequest(CacheHandler.java:92)
	at io.undertow.server.handlers.error.SimpleErrorPageHandler.handleRequest(SimpleErrorPageHandler.java:78)
	at io.undertow.server.handlers.CanonicalPathHandler.handleRequest(CanonicalPathHandler.java:49)
	at org.jboss.as.domain.http.server.ManagementHttpRequestHandler.handleRequest(ManagementHttpRequestHandler.java:57)
	at org.jboss.as.domain.http.server.cors.CorsHttpHandler.handleRequest(CorsHttpHandler.java:75)
	at org.jboss.as.domain.http.server.ManagementHttpServer$UpgradeFixHandler.handleRequest(ManagementHttpServer.java:662)
	at io.undertow.server.Connectors.executeRootHandler(Connectors.java:360)
	at io.undertow.server.protocol.http.HttpReadListener.handleEventWithNoRunningRequest(HttpReadListener.java:255)
	at io.undertow.server.protocol.http.HttpReadListener.handleEvent(HttpReadListener.java:136)
	at io.undertow.server.protocol.http.HttpReadListener.handleEvent(HttpReadListener.java:59)
	at org.xnio.ChannelListeners.invokeChannelListener(ChannelListeners.java:92)
	at org.xnio.conduits.ReadReadyHandler$ChannelListenerHandler.readReady(ReadReadyHandler.java:66)
	at org.xnio.nio.NioSocketConduit.handleReady(NioSocketConduit.java:89)
	at org.xnio.nio.WorkerThread.run(WorkerThread.java:591)
```