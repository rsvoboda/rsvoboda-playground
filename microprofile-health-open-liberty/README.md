# MicroProfile Health on Open Liberty
MicroProfile Health experiment on Open Liberty

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
cp target/microprofile-health.war ~/tmp/wlp/usr/servers/mp/dropins/
```

## Run
```bash
curl http://localhost:9080/health
```