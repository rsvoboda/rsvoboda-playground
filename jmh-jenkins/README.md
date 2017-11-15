# JMH Benchmark results on Jenkins

When running JMH based benchmark (for example https://github.com/rsvoboda/testcases) one would appreciate to see the results visually.

For Jenkins there is JMH Benchmark Jenkins Plugin available, but it is not available in standard Jenkins plugin center.

Code from https://github.com/blackboard/jmh-jenkins must be compiled and built `target/jmhbenchmark.hpi` must be uploaded to Jenkins instance.

In job configution you must select `Post-build Actions` - `Publish JMH Test Result`. You do not specify `.csv` file name, plugin scans workspace root directory for  JMH based`.csv` file 
Details in https://github.com/blackboard/jmh-jenkins/blob/master/src/main/java/blackboard/test/jenkins/jmhbenchmark/BenchmarkPublisher.java#L89
