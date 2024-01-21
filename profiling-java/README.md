# Profiling Java applications

## Flamegraphs
 * `jbang --javaagent=ap-loader@jvm-profiling-tools/ap-loader=start,event=cpu,file=profile.html -m a.b.c.MyMainClass target/foo-1.0.0-SNAPSHOT.jar`
 * `jbang --javaagent=ap-loader@jvm-profiling-tools/ap-loader=start,event=cpu,file=profile.html src/main/a/b/c/MyMainClass`

## JFRs
 * `jcmd 1234 JFR.start duration=100s filename=flight.jfr`
 * `java -XX:StartFlightRecording=duration=200s,filename=flight.jfr -cp . a.b.c.MyMainClass`
 * `jbang -m org.openjdk.jmc.flightrecorder.rules.report.html.JfrHtmlRulesReport --deps org.openjdk.jmc:flightrecorder.rules.jdk:8.3.1 org.openjdk.jmc:flightrecorder.rules:8.3.1 flight.jfr flight.html`
 * `jmc -open flight.jfr`

## Flamegraphs in terminal
```shell
pipx install flameshow
jbang ap-loader@jvm-profiling-tools/ap-loader converter jfr2flame --collapsed flight.jfr flight.stack
flameshow flight.stack
```
