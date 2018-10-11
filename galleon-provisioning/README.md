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

## Round 2 - updates

Update from 14.0.0.Final to the latest available .Final (14.0.1.Final at the moment)
```bash
install --dir=/Users/rsvoboda/tmp/asd wildfly:current#14.0.0.Final
update --dir=/Users/rsvoboda/tmp/asd
```

Update from 14.0.0.Final (notice current/snapshot for install command) to the latest available -SNAPSHOT (for experiments/development only)
```bash
install --dir=/Users/rsvoboda/tmp/snap wildfly:current/snapshot#14.0.0.Final
update --dir=/Users/rsvoboda/tmp/snap
```

Update from 14.0.0.Final (provisioned from current/snapshot) to the latest available .Final
```bash
install --dir=/Users/rsvoboda/tmp/snap wildfly:current/snapshot#14.0.0.Final
code /Users/rsvoboda/tmp/snap/.galleon/provisioning.xml
##<feature-pack location="wildfly@maven(org.jboss.universe:community-universe):current/snapshot#14.0.0.Final"/>
## change to
##<feature-pack location="wildfly@maven(org.jboss.universe:community-universe):current#14.0.0.Final"/>
update --dir=/Users/rsvoboda/tmp/snap
```

## Feedback for round 2
Issues from https://issues.jboss.org/browse/GAL-179 to https://issues.jboss.org/browse/GAL-183.


## Know limitations as of 2018-10-11
 - currently one chanel `current`, more are planned, strategy for it needs to be properly analyzed
 - FP version update in place, not cross FP upgrade
 - `filesystem cd/ls/pw` commands will become top level commands

Following command causes error at this moment as the latest wildfly-core final release is 6.0.2.Final and WF snapshot depends on 7.0.0.Alpha2 and there are some new classes in 7.0.0.Alpha2 which are used by WF snapshot. 
```
install --dir=/Users/rsvoboda/tmp/xxx wildfly-core:current/final
install --dir=/Users/rsvoboda/tmp/xxx wildfly:current/snapshot
```
By calling these commands user explicitly defines wildfly-core version and upgrade to WF doesn't do wildfly-core version change.