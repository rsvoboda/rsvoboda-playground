# Graal and GraalVM notes

## Videos
* https://www.youtube.com/watch?v=PtgKmzgIh4c Twitter’s Quest for a Wholly Graal Runtime by Chris Thalinger
* https://www.youtube.com/watch?v=_7yIUkP5LiQ Graal: How to use the new JVM JIT compiler in real life by Chris Thalinger
* https://www.youtube.com/watch?v=a-XEZobXspo Deep dive into using GraalVM for Java and JavaScript developers by Oleg Šelajev, Thomas Wuerthinger
* https://www.youtube.com/watch?v=BL9SsY5orkA Introduction to Micronaut Lightweight Microservices with Ahead of Time Compilation (Graeme Rocher)

## JDK options
$ java \
  -XX:+UnlockExperimentalVMOptions \
  -XX:+EnableJVMCI \
  -XX:+UseJVMCICompiler \
  -XX:-TieredCompilation \
  -XX:+PrintCompilation \
  FooBar

export JAVA_TOOL_OPTIONS="-XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -XX:+UseParallelGC -Xmx2G -Xms2G"

## TODOs

https://github.com/mariusae/heapster
https://github.com/gperftools/gperftools
https://www.google.com/search?q=graal+escape+analysis&oq=graal+escape+analysis&aqs=chrome..69i57.4824j0j7&sourceid=chrome&ie=UTF-8
https://www.google.com/search?q=graal+inline+parameters&oq=graal+inline+parameters&aqs=chrome..69i57.5158j0j7&sourceid=chrome&ie=UTF-8
