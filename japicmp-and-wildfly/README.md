# japicmp and WildFly
https://siom79.github.io/japicmp is a tool to compare two versions of a jar archive

http://wildfly.org/ is application server written in Java

## generate all in on jar
```bash
for i in `find wildfly-12.0.0.Final |grep '\.jar$'`; do echo $i; unzip -q -o -d wildfly-12.0.0.Final-all-in-one $i; done
rm -rf wildfly-12.0.0.Final-all-in-one/META-INF wildfly-12.0.0.Final-all-in-one/WEB-INF wildfly-12.0.0.Final-all-in-one/OSGI-INF
cd wildfly-12.0.0.Final-all-in-one; zip ../wildfly-12.0.0.Final-all-in-one.zip -q -r *; cd -

for i in `find wildfly-11.0.0.Final |grep '\.jar$'`; do echo $i; unzip -q -o -d wildfly-11.0.0.Final-all-in-one $i; done
rm -rf wildfly-11.0.0.Final-all-in-one/META-INF wildfly-11.0.0.Final-all-in-one/WEB-INF wildfly-11.0.0.Final-all-in-one/OSGI-INF
cd wildfly-11.0.0.Final-all-in-one; zip ../wildfly-11.0.0.Final-all-in-one.zip -q -r *; cd -
```

## generate API diff - japicmp-0.8.1
```bash
wget -O japicmp-0.8.1-jar-with-dependencies.jar http://search.maven.org/remotecontent?filepath=com/github/siom79/japicmp/japicmp/0.8.1/japicmp-0.8.1-jar-with-dependencies.jar

java -jar japicmp-0.8.1-jar-with-dependencies.jar -n wildfly-12.0.0.Final-all-in-one.zip -o wildfly-11.0.0.Final-all-in-one.zip --ignore-missing-classes --only-modified --html-file compare.html
```
This ends up with NPE :(

## generate API diff - custom branch
Prepared https://github.com/rsvoboda/japicmp/tree/npeChecks to overcome NPE problems.

```bash
java -Xmx6G -jar /Users/rsvoboda/git/japicmp/japicmp/target/japicmp-0.11.2-SNAPSHOT-jar-with-dependencies.jar -n wildfly-12.0.0.Final-all-in-one.zip -o wildfly-11.0.0.Final-all-in-one.zip --ignore-missing-classes --only-modified --html-file compare.html
```
Generated report is huge, around 60 MB.