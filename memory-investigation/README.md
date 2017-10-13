Memory investigation for management operations in WildFly
========================
When playing with HAL.next (https://github.com/hal/hal.next) I noticed weird issue related to refresh os Status page and memory usage

Details in https://github.com/hal/hal.next/issues/129

"Issue" visible via CLI
------------------------
**A) CLI file with 100 lines, using `ls` command**
```
FOR /L %x IN (1,1,100) DO echo ls core-service=platform-mbean/type=memory >> memory-info.cli
jboss-cli.bat -c --file=memory-info.cli
```
I tried to run the .cli file multiple times to have more valid data, memory increase was 39-40 MB

**B) CLI file with 100 lines, using `:read-resource`**
```
FOR /L %x IN (1,1,100) DO echo /core-service=platform-mbean/type=memory:read-resource(recursive=true, include-runtime=true) >> memory-info-read-resource.cli
jboss-cli.bat -c --file=memory-info-read-resource.cli
```
Did also multiple runs,  memory increase was 23-24 MB


Detailed investigation
------------------------
**A) Diff between `ls` and `:read-resource`**

Jean-François Denise (CLI component owner) replied: ls is a composite containing 2 operations in your case, that explains the diff.

**B) Memory usage itself**

tl;dr version: no issue confirmed

```bash
for i in {1..100}; do echo ls core-service=platform-mbean/type=memory >> memory-info.cli; done

PID=`jps -l | grep jboss-modules.jar | cut -d" " -f 1`
TODAY=`date +%Y-%m-%d.%H:%M:%S`

jcmd ${PID} GC.run && sleep 3
jcmd ${PID} GC.heap_dump server-${TODAY}-before-100-commands.hprof

jboss-eap-7.1/bin/jboss-cli.sh -c --file=memory-info.cli && sleep 1

jcmd ${PID} GC.heap_dump server-${TODAY}-after-100-commands.hprof
```
I used https://www.eclipse.org/mat/ to compare the dumps and nothing stood out, used size and number of classes was almost the same.

So I tried it again with jconsole running to monitor jvm in realtime, heap size goes down when JVM is asked for heap dump, seems temporarily used memory goes away. I tried to get the heap dump via jcmd and via jvisualvm UI tool, wait longer before heap dump. Heap size always went down when I asked for heap dump.

Final check was to invoke 100 line cli script in 150 iterations, nothing came out from it, no extra GC activity.
```bash
for i in {1..150}; do jboss-eap-7.1/bin/jboss-cli.sh -c --file=memory-info.cli 1>/dev/null; sleep 1;  done
```

So what I saw initially was increase of temporarily used memory when JVM had enought space in heap and didn't need to throw it away. 