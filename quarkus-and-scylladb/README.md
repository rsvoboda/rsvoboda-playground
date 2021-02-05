# ScyllaDB and Quarkus integration

ScyllaDB got my attention thanks to claimed low latency and extremely high throughput. I wanted to try it with Quarkus, the first idea was to directly interact with https://github.com/scylladb/java-driver. But isn't there a better way?
As ScyllaDB is a drop-in Apache Cassandra alternative and Cassandra has already a community-based extension, why not try ScyllaDB with that extension?

## cassandra-quarkus quickstart and ScyllaDB
This went very well, instead of running docker image for Cassandra I just went ahead with ScyllaDB image.
```
docker run -p 9042:9042 --name local-cassandra-instance scylladb/scylla
```
Follow https://github.com/datastax/cassandra-quarkus/tree/master/quickstart#running-a-local-cassandra-database for further setup.

## cassandra-quarkus quickstart with ScyllaDB dependencies
ScyllaDB is a drop-in Apache Cassandra alternative in Java drivers world too.
To use ScyllaDB based java driver I had to change just `groupId` of some artifacts and add exclusion for `com.datastax.oss:java-driver-core`.

Here is the example commit: https://github.com/rsvoboda/cassandra-quarkus/commit/5d4b7b454dd84729bbbb53e911f2150f0f1f1af9

I did play with 3.10 and 4.9 based driver:
 - https://github.com/rsvoboda/cassandra-quarkus/tree/scylladb-quickstart
 - https://github.com/rsvoboda/cassandra-quarkus/tree/scylladb-quickstart-3.10

## JVM / NATIVE / DEV / TEST
Cassandra-quarkus quickstart with ScyllaDB related changes worked well in all the available modes - JVM / NATIVE / DEV / TEST.

## Conclusion
ScyllaDB experience was very nice. It is a very professional drop-in Apache Cassandra alternative, including the libraries for development.
