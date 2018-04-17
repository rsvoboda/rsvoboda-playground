# Revapi and WildFly

Revapi is an API analysis and change tracking tool written in Java hosted on https://revapi.org/.

## Generate API diff
I used standalone variant of Revapi described in https://revapi.org/getting-started.html#standalone_usage and https://revapi.org/modules/revapi-standalone/index.html

Bits generated in https://github.com/rsvoboda/rsvoboda-playground/tree/master/japicmp-and-wildfly were used for analysis.

```bash
wget -O revapi-standalone-0.7.0-standalone.zip http://search.maven.org/remotecontent?filepath=org/revapi/revapi-standalone/0.7.0/revapi-standalone-0.7.0-standalone.zip

unzip -q revapi-standalone-0.7.0-standalone.zip

time revapi-0.7.0/revapi.sh --extensions=org.revapi:revapi-java:0.16.0,org.revapi:revapi-reporter-text:0.9.0 \
   --old=wildfly-11.0.0.Final-all-in-one.zip --new=wildfly-12.0.0.Final-all-in-one.zip

real	25m29.665s
user	161m14.675s
sys	0m56.516s
```

## Notes
Pros:
 - Flexibility to define extensions and their versions
 - Documentation for Architecture and Extensions

Cons:
 - Slower comparing to japicmp (above example was finished in 1m12.106s)
 - No simple Copy&Paste example for standalone usage