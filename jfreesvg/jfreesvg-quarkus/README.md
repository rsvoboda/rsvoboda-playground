# JFreeSVG

# Skeleton
```
mvn io.quarkus:quarkus-maven-plugin:0.23.2:create \
    -DprojectGroupId=cz.rsvoboda \
    -DprojectArtifactId=jfreesvg-quarkus \
    -DclassName="cz.rsvoboda.PingResource" \
    -Dpath="/ping"
```

## Build
```
mvn clean package
```

## Run (dev mode)
```
mvn package quarkus:dev
```

## Try
```
curl localhost:8080/ping
```

## Native mode
Ensure `<enableJni>true</enableJni>` configuration is set for native-image goal.
```
mvn clean package -Dnative
target/jfreesvg-quarkus-1.0-SNAPSHOT-runner
```

Ping endpoint throws error because of https://github.com/oracle/graal/issues/1163.