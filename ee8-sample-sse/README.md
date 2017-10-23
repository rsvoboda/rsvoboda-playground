JAX-RS 2.1 Server Sent Events example
========================
JAX-RS 2.1 Server Sent Events example based on YouTube video by Adam Bien - https://www.youtube.com/watch?time_continue=8&v=qQLVCgmiMgQ

SSE for JAX-RS is defined in https://www.jcp.org/en/jsr/detail?id=370 

Running
-------------------
```bash
mvn clean package
cp target/ee8-sample-sse.war ${PATH_TO_SERVER}

curl -i http://localhost:8080/ee8-sample-sse/resources/beats  
```

Another use-case is to run the project directly in IDE - e.g. NetBeans IDE

Where do I get those servers?
-------------------
GlassFish 5 - https://javaee.github.io/glassfish/download

WildFly 10.1 + RESTEasy master - not yet released, you have to build it:
```bash
git clone https://github.com/resteasy/Resteasy.git
cd Resteasy
mvn clean package -DskipTests -Denforcer.skip=true -Dcheckstyle.skip=true
ls testsuite/integration-tests/target/test-server/wildfly-10.1.0.Final
```

WildFly & RESTEasy - Accept header
-------------------
When running against WildFly 10.1 + RESTEasy master, Accept header was needed for some time
```bash
curl -i -H "Accept: text/event-stream" http://localhost:8080/ee8-sample-sse/resources/beats
```

 * Discussed in http://lists.jboss.org/pipermail/resteasy-dev/2017-October/000478.html
 * Issue tracked in https://issues.jboss.org/browse/RESTEASY-1733

 WildFly & RESTEasy - Filters issue
-------------------
```bash
curl -vvv http://localhost:8080/ee8-sample-sse/resources/foo
## vs.
curl -vvv -i http://localhost:8080/ee8-sample-sse/resources/beats
```
Response for beats endpoint is without CORS filter headers.

* Issue tracked in https://issues.jboss.org/browse/RESTEASY-1742