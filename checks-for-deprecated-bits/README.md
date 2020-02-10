# Checks for deprecated bits

## Deprecated bits in current codebase
The obvious way is running `git grep '@Deprecated'` command.

But with deprecated bits it's always better to have more context, e.g. when it was introduced aby who.
```bash
for i in `git grep -l '@Deprecated'`; do
  git blame $i | grep '@Deprecated' | gsed "s/\(^.*.java.*$\)/\n\1/g";
done
```
To get overview based on dates run the following command
```bash
for i in `git grep -l '@Deprecated'`; do
  git blame $i | grep '@Deprecated' |  \
    gsed "s/^.*\([0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]\).*$/\1/g";
done | sort | uniq -c
```
As I'm on macOS I use gsed (GNU sed) command instead of built in sed command.

## Deprecated bits in the build log
This assumes existence of `~/Downloads/build-log.txt` file, for example created using ` of `mvn -V clean install -DskipTests -DskipITs | tee ~/Downloads/build-log.txt` command

Maven build produces warnings about usage of deprecated classes and methods, to get to the core of the warning you can use following command:
```bash
grep "deprecated" ~/Downloads/build-log.txt | \
  cut -d: -f2- | cut -d" " -f2-
```
It will give you message similar to thi one: org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller in org.infinispan.client.hotrod.marshall has been deprecated

Run following command to see how often deprecation warnings were printed:
```bash
grep "deprecated" ~/Downloads/build-log.txt | \
  cut -d: -f2- | cut -d" " -f2- | sort | uniq -c | sort -n
```