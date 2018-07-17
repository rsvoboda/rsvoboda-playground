# MicroProfile Config - Servlet Scenario
MicroProfile Config experiment with simple servlet scenario

## Build
```bash
mvn clean package && cp target/microprofile-config.war ~/tmp/wildfly-14.0.0.Beta1-SNAPSHOT/standalone/deployments/
```
## Run
```bash
curl http://localhost:8080/microprofile-config/config
```
