# MicroProfile Config on Open Liberty
MicroProfile Config experiment on Open Liberty

## Open Liberty
```bash
cd ~/tmp
wget https://public.dhe.ibm.com/ibmdl/export/pub/software/openliberty/runtime/release/2018-06-19_0502/openliberty-18.0.0.2.zip
unzip -q openliberty-18.0.0.2.zip

wlp/bin/server create  mp --template=microProfile1
wlp/bin/server run mp
```

## Build and Deploy
```bash
## !!! edit CustomConfigSource file, line 19 - String fileLocation = ... !!!
mvn clean package
cp target/microprofile-config.war ~/tmp/wlp/usr/servers/mp/dropins/
```

## Run
```bash
curl http://localhost:9080/microprofile-config/config
curl http://localhost:9080/microprofile-config/config/properties
```

## Expected output
```
curl http://localhost:9080/microprofile-config/config
{"ConfigSources":{"System Properties Config Source":400,"Environment Variables Config Source":300,"Custom Config Source: file:/Users/rsvoboda/tmp/microprofile-config-example/resources/CustomConfigSource.json":150,"Properties File Config Source: file:/Users/rsvoboda/tmp/wlp/usr/servers/mp/apps/expanded/microprofile-config.war/WEB-INF/classes/META-INF/microprofile-config.properties":100},"ConfigProperties":{"io_openliberty_guides_system_inMaintenance":"true","io_openliberty_guides_testConfigOverwrite":"CustomSource","io_openliberty_guides_port_number":"9080","io_openliberty_guides_email":"admin@guides.openliberty.io","io_openliberty_guides_inventory_inMaintenance":"false","io_openliberty_guides_address":"Purkynova 111,Brno"}} 
```
```
curl http://localhost:9080/microprofile-config/config/properties
ERROR: Service is currently in maintenance. Contact: admin(at)guides.openliberty.io
```