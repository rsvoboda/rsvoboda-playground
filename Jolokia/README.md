Jolokia and WildFly
========================
Jolokia (https://jolokia.org/) is remote JMX with JSON over HTTP, an alternative to JSR-160 connectors.

To run Jolokia with JBoss EAP you need to download JVM-Agent version from https://jolokia.org/download.html
After that you need to configure JAVA_OPTS to add -javaagent and few additional parameter related to JBoss Logging (global package, -Xbootclasspath and java.util.logging.manager property )

Running
-------------------
Prerequisites:

* JDK 8 or newer - check `java -version`
* wget
* jq (optional, just for json pretty print)


```bash
cd /tmp
wget -O wildfly-11.0.0.Alpha1.zip http://download.jboss.org/wildfly/11.0.0.Alpha1/wildfly-11.0.0.Alpha1.zip && unzip -q wildfly-11.0.0.Alpha1.zip
wget -O jolokia-jvm-1.3.6-agent.jar https://repo1.maven.org/maven2/org/jolokia/jolokia-jvm/1.3.6/jolokia-jvm-1.3.6-agent.jar

JAVA_OPTS="-Xms580m -Xmx580m -XX:MetaspaceSize=96M -Djboss.modules.system.pkgs=org.jboss.logmanager
  -javaagent:/tmp/jolokia-jvm-1.3.6-agent.jar -Xbootclasspath/p:/tmp/wildfly-11.0.0.Alpha1/jboss-modules.jar:/tmp/wildfly-11.0.0.Alpha1/modules/system/layers/base/org/jboss/logmanager/main/jboss-logmanager-2.0.6.Final.jar
  -Djava.util.logging.manager=org.jboss.logmanager.LogManager " wildfly-11.0.0.Alpha1/bin/standalone.sh &
```

```json
curl http://127.0.0.1:8778/jolokia/read/jboss.as:management-root=server 2>/dev/null | jq .
{
  "request": {
    "mbean": "jboss.as:management-root=server",
    "type": "read"
  },
  "value": {
    "profileName": null,
    "suspendState": "RUNNING",
    "releaseVersion": "3.0.0.Beta11",
    "runningMode": "NORMAL",
    "schemaLocations": {},
    "uuid": "a298112f-a59e-47b5-842b-1ec9ca55e4d8",
    "managementMajorVersion": 5,
    "managementMicroVersion": 0,
    "productName": "WildFly Full",
    "managementMinorVersion": 0,
    "serverState": "running",
    "productVersion": "11.0.0.Alpha1",
    "runtimeConfigurationState": "ok",
    "organization": null,
    "name": "rs",
    "processType": "Server",
    "releaseCodename": "Kenny",
    "launchType": "STANDALONE",
    "namespaces": {}
  },
  "timestamp": 1493927168,
  "status": 200
}
```