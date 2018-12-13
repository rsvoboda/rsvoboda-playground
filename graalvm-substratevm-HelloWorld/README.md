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

## GraalVM 1.0.0-rc10 based experiment
Binary produced by rc10 is much smaller (less than 50 %) comparing to binary produced by rc6.

```bash
~/Downloads/graalvm-ee-1.0.0-rc10/Contents/Home/bin/native-image HelloWorld
~/Downloads/graalvm-ce-1.0.0-rc10/Contents/Home/bin/native-image HelloWorld

```

Interesting is also image size difference between Community Edition (CE) and Enterprise Edition (EE) - to be precise 2.2 MB vs 1.9 MB.
```bash
ll -h helloworld-*
-rwxr-xr-x  1 rsvoboda  staff   2.2M Dec 10 13:25 helloworld-ce
-rwxr-xr-x  1 rsvoboda  staff   2.2M Dec 10 13:26 helloworld-ce-v2
-rwxr-xr-x  1 rsvoboda  staff   1.9M Dec 10 13:24 helloworld-ee
-rwxr-xr-x  1 rsvoboda  staff   1.9M Dec 10 13:25 helloworld-ee-v2
-rwxr-xr-x@ 1 rsvoboda  staff   5.0M Sep 17 15:37 helloworld-rc6
```

This is probably due to different default arguments for CE and EE distributions.