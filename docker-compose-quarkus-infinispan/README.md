# Docker-compose file for Quarkus Infinispan Quickstart

## Quarkus Infinispan Quickstart
Quarkus Infinispan Quickstart (https://github.com/quarkusio/quarkus-quickstarts/tree/master/infinispan-client) 
misses instructions for both client and server running inside Docker containers.

## Docker-compose file for Quarkus Infinispan Quickstart
https://github.com/quarkusio/quarkus-quickstarts/pull/192 is the initial work to deliver docker-compose based solution for this. The challenge is to detect Infinispan server availability so the client can start.

As mentioned in https://github.com/quarkusio/quarkus-quickstarts/pull/192#issuecomment-492135698 2 options were identified:

**Option A**
```yaml
version: '3'

networks:
  mynetwork:
    driver: bridge

services:
  infinispan-server:
    image: jboss/infinispan-server:10.0.0.Beta3
    ports:
    - "11222:11222"
    container_name: infinispan
    networks:
      - mynetwork
  client-app:
    image: quarkus/infinispan-client
    ports:
      - "8081:8081"
    container_name: client
    depends_on:
      - infinispan-server
    restart: on-failure
    networks:
      - mynetwork
```
Nicer file, but `restart: on-failure` causes several client app restarts (and stacktraces in log) before server is available => worse user experience

**Option B**
```yaml
version: '3'

networks:
  mynetwork:
    driver: bridge

services:
  infinispan-server:
    image: jboss/infinispan-server:10.0.0.Beta3
    environment:
      - APP_USER=user
      - APP_PASS=changeme
    ports:
      - "11222:11222"
    container_name: infinispan
    networks:
      - mynetwork
  client-app:
    image: quarkus/infinispan-client
    ports:
      - "8081:8081"
    container_name: client
    depends_on:
      - infinispan-server
    networks:
      - mynetwork
    command: >
      /bin/sh -c "
        echo Waiting for Infinispan service start ...;
        while ! curl --silent --output /dev/null --fail-early --user user:changeme http://infinispan:8080;
        do
          sleep 1;
        done;
        echo Infinispan service started!;
        ./application -Dquarkus.http.host=0.0.0.0 -Dquarkus.infinispan-client.server-list=infinispan:11222
      "
```
Longer file, nicer end user experience as the log is not polluted by exception stacktraces.
Also application start command has to be here explicitly defined.
