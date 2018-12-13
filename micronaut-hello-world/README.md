# Micronaut Hello World

## First touch
- https://docs.micronaut.io/latest/guide/index.html#creatingServer
- https://github.com/micronaut-projects/micronaut-examples

## Micronaut and Substrate VM
```bash
export JAVA_HOME="/Users/rsvoboda/Downloads/graalvm-ee-1.0.0-rc10/Contents/Home" && export PATH=$JAVA_HOME/bin:$PATH && GRAALVM_HOME=$JAVA_HOME

native-image -jar build/libs/micronaut-hello-world-0.1-all.jar 

native-image -jar build/libs/micronaut-hello-world-0.1-all.jar --allow-incomplete-classpath --report-unsupported-elements-at-runtime
```

Initial experiment, output generation fails with `Error: com.oracle.graal.pointsto.constraints.UnsupportedFeatureException: Unsupported method java.lang.ClassLoader.getClassLoader(Class) is reachable: The declaring class of this element has been substituted, but this element is not present in the substitution class`