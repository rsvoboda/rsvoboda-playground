Simple JAX-RS experiments
========================
Simple JAX-RS container side filter

```bash
mvn clean install
# deploy to server - e.g. WildFly using WildFly Maven Plugin, CLI deploy command or copy to SERVER_PATH/standalone/deployments/ 
curl http://127.0.0.1:8080/simple-jaxrs/resources/simple
# check the server log - e.g. console output of WildFly or SERVER_PATH/standalone/log/server.log
```
