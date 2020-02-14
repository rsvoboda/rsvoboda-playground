# Quarkus Quickstart on CRC

Using hibernate-orm-quickstart from https://github.com/quarkusio/quarkus-quickstarts and deploying it on Red Hat CodeReady Containers https://github.com/code-ready/crc

## Patch of the QS
```diff
diff --git a/hibernate-orm-quickstart/pom.xml b/hibernate-orm-quickstart/pom.xml
index 2700b76..a2978b2 100644
--- a/hibernate-orm-quickstart/pom.xml
+++ b/hibernate-orm-quickstart/pom.xml
@@ -114,7 +114,7 @@
                                 <env>
                                     <POSTGRES_USER>quarkus_test</POSTGRES_USER>
                                     <POSTGRES_PASSWORD>quarkus_test</POSTGRES_PASSWORD>
-                                    <POSTGRES_DB>quarkus_test</POSTGRES_DB>
+                                    <POSTGRES_DB>quarkusdb</POSTGRES_DB>
                                 </env>
                                 <ports>
                                     <port>5432:5432</port>
diff --git a/hibernate-orm-quickstart/src/main/resources/application.properties b/hibernate-orm-quickstart/src/main/resources/application.properties
index b306b12..151dd0e 100644
--- a/hibernate-orm-quickstart/src/main/resources/application.properties
+++ b/hibernate-orm-quickstart/src/main/resources/application.properties
@@ -1,4 +1,4 @@
-quarkus.datasource.url=jdbc:postgresql://localhost/quarkus_test
+quarkus.datasource.url=jdbc:postgresql://${POSTGRESQL_SERVICE_HOST:localhost}:${POSTGRESQL_SERVICE_PORT:5432}/quarkusdb
 quarkus.datasource.driver=org.postgresql.Driver
 quarkus.datasource.username=quarkus_test
 quarkus.datasource.password=quarkus_test
```

## Deploy to CRC
```bash
# Login
oc login -u developer -p developer https://api.crc.testing:6443

# Build native application
cd hibernate-orm-quickstart
mvn package -Pnative -Dnative-image.docker-build=true -DskipTests=true

# Create a new Quarkus project
oc new-project quarkus-hibernate

# Create a PostgreSQL application
oc new-app -e POSTGRESQL_USER=quarkus_test -e POSTGRESQL_PASSWORD=quarkus_test -e POSTGRESQL_DATABASE=quarkusdb postgresql

# Create a new Binary Build named "quarkus-hibernate"
oc new-build --binary --name=quarkus-hibernate -l app=quarkus-hibernate

# Set the dockerfilePath attribute into the Build Configuration
oc patch bc/quarkus-hibernate -p '{"spec":{"strategy":{"dockerStrategy":{"dockerfilePath":"src/main/docker/Dockerfile.native"}}}}'

# Start the build, uploading content from the local folder: 
oc start-build quarkus-hibernate --from-dir=. --follow

# Create a new Application, using as Input the "quarkus-hibernate" Image Stream:
oc new-app --image-stream=quarkus-hibernate:latest

# Expose the Service through a Route:
oc expose svc/quarkus-hibernate
```

## Cleanup
```bash
oc delete all -l app=quarkus-hibernate
oc delete all -l app=postgresql
```