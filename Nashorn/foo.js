#!/usr/bin/jjs -fv

// jjs -scripting foo.js -- baseline.csv results.csv 0.97
// Expected csv file structure:
//   Test;Clients;Req by client;Requests per second


if ($ARG.length == 3) {
    var baselineFile = $ARG[0];
    var resultsFile = $ARG[1];
    var baselineMultiplier = $ARG[2];
} else {
    print("Expected arguments are missing");
    print("3 arguments are expected: baseline file, results file, baseline multiplier");
    print("   e.g. baseline.csv results.csv 0.97");
    exit(1)
}

var Paths = Java.type('java.nio.file.Paths');
var Files = Java.type('java.nio.file.Files');
var String = Java.type('java.lang.String');
var StandardCharsets = Java.type('java.nio.charset.StandardCharsets');

// var baselineContent = new String(Files.readAllBytes(Paths.get("baseline.csv")));

// var ccc = readFully('foo.js');
// print(ccc);

var resultsBaselineMap = new java.util.HashMap();

var lines = Files.readAllLines(Paths.get(baselineFile), StandardCharsets.UTF_8);
lines.forEach(
    function(line) {
        var cells = line.replaceAll("\"", "").split(";")
        var testName = cells[0]
        var testBaseline = cells[3];
        resultsBaselineMap.put(testName, testBaseline);
    }
);

function pass(testName, testDuration) {
    var report =  "<testsuite errors=\"0\" failures=\"0\" name=\"'jbossws.performance.compare'\" tests=\"1\" time=\"0\">\n"
    report = report +  "<testcase classname=\"" + testName + "\" name=\"" + testName + "\" time=\"" + testDuration + "\"/>\n"
    report= report + "</testsuite>"

    Files.write(Paths.get("./TEST-" + testName + ".xml"), report.getBytes());
}
function fail(testName, testDuration, details) {
    var report = "<testsuite errors=\"0\" failures=\"1\" name=\"'jbossws.performance.compare'\" tests=\"1\" time=\"0\">\n"
    report = report +  "<testcase classname=\"" + testName + "\" name=\"" + testName + "\" time=\"" + testDuration + "\">\n"
    report = report + "<error message=\"Performance was too low: " + details + " \" type=\"diff\">\n"
    report = report + "<![CDATA[performance was too low: " + details + "]]>\n"
    report = report + "</error></testcase>\n"
    report= report + "</testsuite>\n"

    Files.write(Paths.get("./TEST-" + testName + ".xml"), report.getBytes());
}

lines = Files.readAllLines(Paths.get(resultsFile), StandardCharsets.UTF_8);
lines.forEach(
    function(line) {
        var cells = line.replaceAll("\"", "").split(";")
        var testName = cells[0]
        if (testName != "Test") {
            var testResult = (cells[3] * 1).toFixed(2) // requests per second
            var testDuration =  ((cells[1] * cells[2]) / cells[3]).toFixed(2) // in seconds
            var testBaseline = resultsBaselineMap.get(testName)

            var testPassed = testResult > (testBaseline * baselineMultiplier)
            var details = testPassed +
                  " ... " + testName + " : " + testResult + " requests per second ... baseline is " + testBaseline +
                  " requests per second ... " + ((testResult / testBaseline) * 100).toFixed(2) + " %" +
                  " (limit is " + baselineMultiplier*100 + " %)" + " ... test duration: " + testDuration + " seconds";
            print(details);

            if (testPassed) {
                pass(testName, testDuration);
            } else {
                fail(testName, testDuration, details);
            }
        }
    }
);
