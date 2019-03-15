#!/bin/bash
# 
# Parses output from https://www.mojohaus.org/versions-maven-plugin/display-dependency-updates-mojo.html
#   mvn versions:display-dependency-updates -Dversions.outputFile=/Users/rsvoboda/Downloads/dependency-updates.txt

function trimLeading {
    echo $1 | sed "s/^[ \t]*//"
}  
function processLine {
    ## echo "$1"
    GROUP=`echo "$1" | cut -d: -f1 | sed "s/^[ \t]*//"`
    ARTIFACT=`echo "$1" | cut -d: -f2 | cut -d" " -f1`
    FROM=`echo "$1" | cut -d: -f2 | cut -d" " -f3`
    TO=`echo "$1" | cut -d: -f2 | cut -d" " -f5`
    
    FROM_MAJOR=`echo $FROM | cut -d\. -f1`
    FROM_MINOR=`echo $FROM | cut -d\. -f2`
    FROM_MICRO=`echo $FROM | cut -d\. -f3`
    
    TO_MAJOR=`echo $TO | cut -d\. -f1`
    TO_MINOR=`echo $TO | cut -d\. -f2`
    TO_MICRO=`echo $TO | cut -d\. -f3`

    DIFF_PREFIX="MICRO"
    if [[ $FROM_MAJOR != $TO_MAJOR ]]; then
        DIFF_PREFIX="MAJOR"
    elif [[ $FROM_MINOR != $TO_MINOR ]]; then
        DIFF_PREFIX="MINOR"
    fi

    echo "$DIFF_PREFIX $GROUP:$ARTIFACT $FROM $TO"
}

file=${1:-"dependency-updates.txt"}
PREPEND=""
while IFS= read line
do
    if [[ $line == *":"*"."*"->"* ]]; then
        processLine "$line"
    elif [[ $line == *":"*"."* ]]; then
        PREPEND=$line
    elif [[ $line == *"->"* ]]; then
        processLine "$PREPEND `trimLeading "$line"`"
        PREPEND=""
    fi

done <"$file"
