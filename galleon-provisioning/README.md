# Galleon Provisioning For WildFly
Galleon is a provisioning tool designed to create and maintain software distributions that consist of one or more products (or components). Currently the main focus is to provision WildFly application server - http://wildfly.org/

Details about Galleon Provisioning tool:
 * http://docs.wildfly.org/galleon/
 * https://github.com/wildfly/galleon

## Get it
Prepared distributions can be found on https://github.com/wildfly/galleon/releases page
```
wget https://github.com/wildfly/galleon/releases/download/2.0.0.Final/galleon-2.0.0.Final.zip
unzip -q galleon-2.0.0.Final.zip
galleon-2.0.0.Final/bin/galleon.sh
```

## Build it
```
git clone git@github.com:wildfly/galleon.git
cd galleon
mvn clean install -DskipTests -Denforcer.skip=true -Dcheckstyle.skip=true -Drelease
```

## Run it
```
$ dist/target/galleon-3.0.0.Alpha1-SNAPSHOT/bin/galleon.sh
[galleon]$
```

In galleon console do:
```
install wildfly-core:current --dir=/Users/rsvoboda/tmp/wf-core-by-galleon
```
NOTE: Do not use `~`, bash expansion is not supported at the moment.


To see all available commands just hit <TAB>.

## Docs
For latest docs see `dist/target/galleon-3.0.0.Alpha1-SNAPSHOT/docs/index.html`

## Feedback
https://issues.jboss.org/browse/GAL-125 was created based on the feedback