# JDK Flight Recorder

Resources:
 - https://en.wikipedia.org/wiki/JDK_Flight_Recorder
 - https://www.oracle.com/java/technologies/jdk-mission-control.html
 - https://www.youtube.com/watch?v=Gx_JGVborJ0&ab_channel=Java

## Java Native Memory With JDK Flight Recorder

Experiments with direct byte buffer and new Foreign Memory API, based on https://www.morling.dev/blog/tracking-java-native-memory-with-jdk-flight-recorder/.


```bash
java --enable-preview --source 22 -XX:StartFlightRecording=name=Profiling,filename=recording.jfr,settings=profile main.java
java --enable-preview --source 22 -XX:StartFlightRecording=name=Profiling,filename=nmt-recording.jfr,settings=profile -XX:NativeMemoryTracking=detail main.java

/Applications/JDK\ Mission\ Control.app/Contents/MacOS/jmc
 and open recording.jfr / nmt-recording.jfr
```

## Run JDK Mission Control directly from Applications
Edit `JDK Mission Control.app/Contents/Info.plist` to point to JDK location
```
...
    <array>
      <string>-keyring</string>
      <string>~/.eclipse_keyring</string>
      <string>-vm</string>
      <string>/Users/rsvoboda/.sdkman/candidates/java/21.0.2-tem/bin/java</string>
    </array>
...
```
