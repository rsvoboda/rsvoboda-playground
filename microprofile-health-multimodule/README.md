# MicroProfile Health Multi Module App on Open Liberty
MicroProfile Health multi module maven project experiment on Open Liberty
App module uses lib module as dependency, lib module defines generic health check.
So in the end 2 health checks are supposed to be available in the endpoint response.

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
mvn clean package
cp app/target/microprofile-health.war ~/tmp/wlp/usr/servers/mp/dropins/
```

## Run
```bash
curl -v http://localhost:9080/health
```

Check also response code and invoke it for few times to see some UPs and DOWNs.