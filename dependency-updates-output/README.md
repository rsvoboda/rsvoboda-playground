# Analysis of versions:display-dependency-updates output

https://www.mojohaus.org/versions-maven-plugin/display-dependency-updates-mojo.html prints output to console and file but it's not easy to use it by other tools as some records are on 2 lines, some lines are just headings etc

Included [parse-dependency-updates-output.sh](parse-dependency-updates-output.sh) groups record details to one line and performs analysis if the version update is major / minor / micro.

## Example

```bash
## in project directory
mvn versions:display-dependency-updates -Dversions.outputFile=/Users/rsvoboda/Downloads/dependency-updates.txt

## in this directory
sh parse-dependency-updates-output.sh /Users/rsvoboda/Downloads/dependency-updates.txt | grep MICRO | sort
sh parse-dependency-updates-output.sh /Users/rsvoboda/Downloads/dependency-updates.txt | grep MINOR | sort
sh parse-dependency-updates-output.sh /Users/rsvoboda/Downloads/dependency-updates.txt | grep MAJOR | sort
```