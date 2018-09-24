# HelloWorld with SubstrateVM / GraalVM
https://github.com/oracle/graal/tree/master/substratevm#quick-start based experiment with JVMCI enabled JDK 8.

## GraalVM sources based experiment
Following https://github.com/oracle/graal/tree/master/substratevm#quick-start example to build native executable.

```bash
 rsvoboda ~ git/graal/substratevm $ mx native-image HelloWorld
 rsvoboda ~ git/graal/substratevm $ time ./helloworld
Hello World
real	0m0.010s
user	0m0.003s
sys	0m0.004s
 rsvoboda ~ git/graal/substratevm $ time java -cp . HelloWorld
Hello World
real	0m0.104s
user	0m0.076s
sys	0m0.027s
```

## GraalVM distribution based experiment
Using GraalVM Community Edition 1.0 RC6.

```bash
~/Downloads/graalvm-ee-1.0.0-rc6/Contents/Home/bin/native-image HelloWorld
```
