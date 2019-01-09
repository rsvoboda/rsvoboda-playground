# Graal and GraalVM performance experiments

JMH based experiments, start with https://github.com/oracle/graal/issues/449#issuecomment-395258518

```
java -jar target/benchmarks.jar
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -jar target/benchmarks.jar
```

Experiments on:
* jdk1.8.0_191.jdk
* jdk-11.0.1.jdk
* graalvm-ee-1.0.0-rc10
* graalvm-ce-1.0.0-rc10
* jdk-11.0.1.jdk with Graal as JIT Compiler


jdk-11.0.1.jdk
`GH_449.testFactorial  thrpt   25  37.054 ± 1.024  ops/s`

jdk-11.0.1.jdk with Graal as JIT Compiler
`GH_449.testFactorial  thrpt   25  28.292 ± 0.666  ops/s`

jdk1.8.0_191.jdk
`GH_449.testFactorial  thrpt   25  36.847 ± 0.511  ops/s`

graalvm-ee-1.0.0-rc10
`GH_449.testFactorial  thrpt   25  34.059 ± 0.519  ops/s`