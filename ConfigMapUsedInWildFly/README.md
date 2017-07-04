ConfigMap Used In WildFly
========================
You can inject Kubernetes ConfigMap values with Java EE & WildFly

This sample is based on https://blog.sebastian-daschner.com/entries/kubernetes_configmaps_javaee article.

ConfigMap values are persisted as properties files, those files are available in global WildFly module.
Custom CDI producer for the properties can be used by your application to simplify usage.

Running
-------------------
Prerequisites:

* JDK 8 or newer - check `java -version`
* Maven 3 or newer - check `mvn -v`


Setup EAP server (using absolute-resources, workaround for https://issues.jboss.org/browse/WFCORE-2765)
```bash
[[ -d properties ]] || mkdir properties

cat <<EOF > properties/application.properties
hello.greeting=Hi
hello.name=Rostislav
EOF

jboss-eap-7.1/bin/standalone.sh &
sleep 6
jboss-eap-7.1/bin/jboss-cli.sh -c --commands="
  module add --name=org.experiments.rsvoboda --absolute-resources=properties,
  /subsystem=ee:list-add(name=global-modules,value={name=org.experiments.rsvoboda}),
  :shutdown(restart=true)"
```

Setup WildFly / EAP server with WFCORE-2765 changes (allows `properties` directory inside modules)
```bash
echo "module add --name=org.experiments.rsvoboda --resources=properties --allow-nonexistent-resources
embed-server
/subsystem=ee:list-add(name=global-modules,value={name=org.experiments.rsvoboda})" | dist/target/wildfly-11.0.0.Beta1-SNAPSHOT/bin/jboss-cli.sh

cat <<EOF > dist/target/wildfly-11.0.0.Beta1-SNAPSHOT/modules/org/experiments/rsvoboda/main/properties/application.properties
hello.greeting=Hi
hello.name=Rostislav
EOF

dist/target/wildfly-11.0.0.Beta1-SNAPSHOT/bin/standalone.sh &
```

Deploy, run and undeploy the application (second terminal recommended)
```bash
cd /home/rsvoboda/git/rsvoboda-playground/ConfigMapUsedInWildFly
mvn clean package wildfly:deploy

curl http://127.0.0.1:8080/ConfigMapUsedInWildFly/resources/hello
## you should see 'Hi, Rostislav!'

mvn wildfly:undeploy

```

Cleanup of EAP server
```bash
jboss-eap-7.1/bin/jboss-cli.sh -c --commands="
  /subsystem=ee:list-remove(name=global-modules,value={name=org.experiments.rsvoboda}),
  module remove --name=org.experiments.rsvoboda,
  :shutdown"
```
