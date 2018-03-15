# WildFly Application Server (WildFly) Quickstarts
WildFly Application Server (WildFly) Quickstarts (QS) are hosted on GitHub https://github.com/wildfly/quickstart

## Get the bits
```bash
git clone git@github.com:wildfly/quickstart.git
```

## Run the server
Using WildFly 12 from http://wildfly.org/
```bash
cd ~/TESTING
wget http://download.jboss.org/wildfly/12.0.0.Final/wildfly-12.0.0.Final.zip
unzip -q wildfly-12.0.0.Final.zip
~/TESTING/wildfly-12.0.0.Final/bin/standalone.sh -c standalone-full.xml
```

## Build and test the bits
Use-case to check all the QS which have `jboss-jaxrs-api` defined with Java 11 (available target is still 10 - Java(TM) SE Runtime Environment 18.9 (build 11-ea+3))
```bash
for i in `git grep jboss-jaxrs-api | cut -d: -f1`; do
    mvn -f $i -Dmaven.compiler.target=10 -Dmaven.compiler.source=10 clean install wildfly:deploy wildfly:undeploy
    read -n 1 -p "compiled and deployed $i, continue?"
    mvn -f $i verify -Parq-remote
    read -n 1 -p "arq-remote checked on $i, continue?"
done
```

Use-case to build, deploy and undeploy the Quickstarts and use custom local repository
```bash
for i in `ls -1 -d */`; do
    mvn -f $i/pom.xml -Dmaven.repo.local=/Users/rsvoboda/TESTING/local-repo -Dmaven.compiler.target=10 -Dmaven.compiler.source=10 clean install wildfly:deploy wildfly:undeploy
done
```

## Checks on the local repository
List artifacts which are present in multiple versions
```bash
cd /Users/rsvoboda/TESTING/local-repo
find . | grep '\.pom$' | wc -l
for i in `find . | grep '\.pom$'`; do dirname $i | xargs dirname ; done | sort | uniq -c | grep -v '1 \.\/'
```

Get disk usage of artifacts which are present in multiple versions
```bash
for i in `find . | grep '\.pom$'`; do dirname $i | xargs dirname ; done | sort | uniq -c | grep -v '1 \.\/' | cut -d'/' -f2- | xargs du -cskh 
```